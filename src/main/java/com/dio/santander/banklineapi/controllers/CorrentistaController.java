package com.dio.santander.banklineapi.controllers;

import java.util.List;
import java.util.Optional;

import com.dio.santander.banklineapi.dto.NovoCorrentista;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.services.CorrentistaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/correntistas")
@Api(tags = {"Controlador de Correntistas"})
public class CorrentistaController {
    
    @Autowired
    CorrentistaRepository repository;

    @Autowired
    CorrentistaService service;

    @GetMapping
    @ApiOperation(value="Lista todos os correntistas cadastrados")
    public List<Correntista> findAll(){
        return service.mostraCorrentistas();
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um novo correntista")
    public void save(@RequestBody NovoCorrentista correntista){
        service.save(correntista);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um correntista pelo Id")
    public Optional<Correntista> findAll(@PathVariable("id") Integer idCorrentista ){
        return service.consultaCorrentista(idCorrentista);
    }

}
