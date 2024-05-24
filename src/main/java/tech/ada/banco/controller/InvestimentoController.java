package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.ada.banco.dto.ContaDTO;

import tech.ada.banco.dto.InvestimentoDTO;
import tech.ada.banco.dto.RetornoDto;
import tech.ada.banco.exception.NaoEncontradoException;
import tech.ada.banco.login.JwtService;
import tech.ada.banco.service.InvestimentoServico;

import javax.validation.Valid;


@RestController
@RequestMapping("/investir")
@RequiredArgsConstructor
public class InvestimentoController {

    private final InvestimentoServico service;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @PostMapping
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<RetornoDto> investir(@Valid @RequestBody InvestimentoDTO dto){
        try {
            return  new ResponseEntity<>(service.investir(dto), HttpStatus.CREATED);
        }catch (NaoEncontradoException e){
            return ResponseEntity.badRequest().build();
        }
    }


}
