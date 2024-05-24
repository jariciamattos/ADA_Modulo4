package tech.ada.banco.usuario;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<UsuarioDTO> listarTodos() {
        return this.service.listarUsuarios().stream()
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{documento}")
    public ResponseEntity excluir (@PathVariable("documento") String documento) {
        service.excluir(documento);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{documento}")
    public ResponseEntity<UsuarioDTO> atualizar (@PathVariable("documento") String documento, @RequestBody UsuarioDTO dto) {
        dto.setDocumento(documento);
        return new ResponseEntity<>(this.service.atualizar(dto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> inserir (@Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto), HttpStatus.CREATED);
    }

}
