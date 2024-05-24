package tech.ada.banco.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.dto.InvestimentoDTO;
import tech.ada.banco.dto.RetornoDto;
import tech.ada.banco.exception.NaoEncontradoException;
import tech.ada.banco.factory.InvestimentoFactory;
import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.Conta;
import tech.ada.banco.repository.ClienteRepository;
import tech.ada.banco.repository.ContaRepository;

@Service
@RequiredArgsConstructor
public class InvestimentoServico {

    private final ContaRepository contaRepository;
    private final ClienteRepository<Cliente> clienteRepository;
    private final ModelMapper modelMapper;

    private ContaDTO convertContaDto(Conta conta){
        return modelMapper.map(conta, ContaDTO.class);
    }

    private Conta convertContaFromDto(ContaDTO contaDTO){
        return modelMapper.map(contaDTO, Conta.class);
    }

    public RetornoDto investir(InvestimentoDTO contaDTO) throws NaoEncontradoException {
        var cliente = clienteRepository.findByUuid(contaDTO.getClienteUuid()).orElseThrow();
        InvestimentoFactory factory = new InvestimentoFactory();
        var conta = factory.nova(contaDTO.getValor(), cliente);
        var contaNova = convertContaDto(contaRepository.save(conta));
        return RetornoDto.builder().mensagem("Investimento executado com sucesso.").conta(contaNova).build();
    }


}
