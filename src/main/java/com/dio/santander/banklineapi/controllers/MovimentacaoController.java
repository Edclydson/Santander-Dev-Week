package com.dio.santander.banklineapi.controllers;

import java.util.List;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Movimentacao;

import com.dio.santander.banklineapi.services.impl.movimentacao.ListaMovimentacaoImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.ListaTodasMovimetacoesImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.MovimentacaoImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movimentacoes")
@Tag(name = "Controlador das Movimentações")
public class MovimentacaoController {
    
    private final MovimentacaoImpl movimentacao;
    private final ListaTodasMovimetacoesImpl listaTodasMovimetacoes;
    private final ListaMovimentacaoImpl listaMovimentacao;

    public MovimentacaoController(MovimentacaoImpl movimentacao, ListaTodasMovimetacoesImpl listaTodasMovimetacoes, ListaMovimentacaoImpl listaMovimentacao) {
        this.movimentacao = movimentacao;
        this.listaTodasMovimetacoes = listaTodasMovimetacoes;
        this.listaMovimentacao = listaMovimentacao;
    }

    @GetMapping
    @Operation(summary = "Consulta todas as movimentações feitas")
    public List<Movimentacao> findAll(){
        return listaTodasMovimetacoes.listaTodasMovimentacoes();
    }

    @PostMapping
    @Operation(summary = "Realiza uma nova movimentação")
    public void save(@RequestBody NovaMovimentacao novaMovimentacao){
        movimentacao.createmovement(novaMovimentacao);
    }

    @GetMapping("/{idConta}")
    @Operation(summary = " Consulta as movimentações de uma conta especifica")
    public List<Movimentacao> findById(@PathVariable("idConta") Integer idConta){
        return listaMovimentacao.mostrarMovimentacao(idConta);
    }
}
