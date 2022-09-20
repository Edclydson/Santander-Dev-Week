package com.dio.santander.banklineapi.controllers;

import java.util.List;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import com.dio.santander.banklineapi.services.NovaMovimentacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movimentacoes")
@Api(tags = {"Controlador das Movimentações"})
public class MovimentacaoController {
    
    @Autowired
    MovimentacaoRepository repository;

    @Autowired
    NovaMovimentacaoService service;

    @GetMapping
    @ApiOperation(value = "Consulta todas as movimentações feitas")
    public List<Movimentacao> findAll(){
        return repository.findAll();
    }

    @PostMapping
    @ApiOperation(value = "Realiza uma nova movimentação")
    public void save(@RequestBody NovaMovimentacao novaMovimentacao){
        service.save(novaMovimentacao);
    }

    @GetMapping("/{idConta}")
    @ApiOperation(value=" Consulta as movimentações de uma conta especifica")
    public List<Movimentacao> findAll(@PathVariable("idConta") Integer idConta){
        return repository.findByIdConta(idConta);
    }
}
