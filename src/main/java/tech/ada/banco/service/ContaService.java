package tech.ada.banco.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.dto.RetornoDto;
import tech.ada.banco.dto.TransferenciaDTO;
import tech.ada.banco.enums.TipoOperacaoBancaria;
import tech.ada.banco.exception.NaoEncontradoException;
import tech.ada.banco.exception.SaldoExistenteException;
import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.factory.ContaCorrenteFactory;
import tech.ada.banco.factory.OperacaoBancariaFactory;
import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.ClientePF;
import tech.ada.banco.model.Conta;
import tech.ada.banco.repository.ClienteRepository;
import tech.ada.banco.repository.ContaRepository;
import tech.ada.banco.service.operacoes.OperacaoBancaria;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService {

    //@Autowired
    private final ContaRepository contaRepository;
    private final ClienteRepository<Cliente> clienteRepository;
    private final ModelMapper modelMapper;
    private final OperacaoBancariaFactory operacaoBancariaFactory = OperacaoBancariaFactory.getInstance() ;

    private ContaDTO convertContaDto(Conta conta){
        return modelMapper.map(conta, ContaDTO.class);
    }

    private Conta convertContaFromDto(ContaDTO contaDTO){
        return modelMapper.map(contaDTO, Conta.class);
    }


    public List<ContaDTO> listarContas(UUID clienteUuid){
        var cliente = clienteRepository.findByUuid(clienteUuid).orElseThrow();
        return contaRepository.findByCliente(cliente).stream().map(this::convertContaDto).collect(Collectors.toList());
    }

    public RetornoDto salvar(ContaDTO contaDTO) throws NaoEncontradoException {
        var cliente = clienteRepository.findByUuid(contaDTO.getClienteUuid()).orElseThrow();

        ContaCorrenteFactory factory = new ContaCorrenteFactory();
        var conta = factory.nova(contaDTO.getSaldo(), cliente);
        var contaNova = convertContaDto(contaRepository.save(conta));
        return RetornoDto.builder().mensagem("Conta Corrente Criada.").conta(contaNova).build();
    }

    public Optional<ContaDTO> buscarPorUuid(UUID uuid){
        return contaRepository.findByUuid(uuid).map(this::convertContaDto);
    }

    public void excluir(UUID uuid) throws SaldoExistenteException {
        var conta = contaRepository.findByUuid(uuid).orElseThrow();
        if(conta.getSaldo().compareTo(BigDecimal.ZERO)==0){
            contaRepository.delete(conta);
        }else{
            throw new SaldoExistenteException();
        }
    }

    public ContaDTO sacar(ContaDTO contaDTO) throws SaldoInsuficienteException {
        Conta conta = contaRepository.findByUuid(contaDTO.getUuid()).orElseThrow();
         OperacaoBancaria op = operacaoBancariaFactory.getContaOperacoes(conta, TipoOperacaoBancaria.Saque);
         op.realizarOperacao(conta, contaDTO.getSaldo());
        return convertContaDto(contaRepository.save(conta));
    }

    public ContaDTO deposito(ContaDTO contaDTO) throws SaldoInsuficienteException {
        var conta = contaRepository.findByUuid(contaDTO.getUuid()).orElseThrow();
        operacaoBancariaFactory.getContaOperacoes(conta, TipoOperacaoBancaria.Deposito)
                .realizarOperacao(conta, contaDTO.getSaldo());
        return convertContaDto(contaRepository.save(conta));
    }

    public RetornoDto transferir(TransferenciaDTO contaDTO) throws SaldoInsuficienteException {
        var contaOrigem = contaRepository.findByUuid(contaDTO.getContaOrigemUuid()).orElseThrow();
        var contaDestimo = contaRepository.findByUuid(contaDTO.getContaDestinoUuid()).orElseThrow();

        operacaoBancariaFactory.getContaOperacoes(contaOrigem, TipoOperacaoBancaria.Saque)
                .realizarOperacao(contaOrigem, contaDTO.getValorOperacao());

        operacaoBancariaFactory.getContaOperacoes(contaDestimo, TipoOperacaoBancaria.Deposito)
                .realizarOperacao(contaOrigem, contaDTO.getValorOperacao());

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestimo);

        return RetornoDto.builder().mensagem("Transferência executada com sucesso.").build();
    }

    public void validarUsuario(String documento, UUID contaUuid) {
        var conta = contaRepository.findByUuid(contaUuid).orElseThrow();
        var cliente = (ClientePF) conta.getCliente();

        if(!cliente.getCpf().equals(documento)) {
            throw new AccessDeniedException("Apenas o dono da conta pode realizar esta operação.");
        }
    }

    public ContaDTO consutarSaldo(UUID uuid)  {
        var conta = contaRepository.findByUuid(uuid).orElseThrow();
        return convertContaDto(conta);
    }
}
