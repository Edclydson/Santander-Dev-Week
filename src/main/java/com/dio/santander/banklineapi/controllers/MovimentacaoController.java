package com.dio.santander.banklineapi.controllers;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.exceptions.InsuficientFundsException;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.services.impl.movimentacao.ListaMovimentacaoImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.ListaTodasMovimetacoesImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.MovimentacaoImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public void save(@RequestBody NovaMovimentacao novaMovimentacao) throws InsuficientFundsException {
        movimentacao.newMovement(novaMovimentacao);
    }

    @GetMapping("/{idConta}")
    @Operation(summary = " Consulta as movimentações de uma conta especifica")
    public List<Movimentacao> findById(@PathVariable("idConta") Integer idConta){
        return listaMovimentacao.mostrarMovimentacao(idConta);
    }
}
