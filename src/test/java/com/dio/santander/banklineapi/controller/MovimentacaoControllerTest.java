package com.dio.santander.banklineapi.controller;

import com.dio.santander.banklineapi.ApplicationConfigTest;
import com.dio.santander.banklineapi.controllers.MovimentacaoController;
import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Movimentacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dio.santander.banklineapi.services.impl.movimentacao.DespesaImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.ListaMovimentacaoImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.ListaTodasMovimetacoesImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.MovimentacaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@DisplayName("MovimentacaoControllerTest")
public class MovimentacaoControllerTest extends ApplicationConfigTest{

    @MockBean
    ListaTodasMovimetacoesImpl serviceTodasMovimentacoes;

    @MockBean
    ListaMovimentacaoImpl serviceMovimentacao;

    @MockBean
    MovimentacaoImpl serviceMovimentacaoImpl;

    @MockBean
    DespesaImpl serviceDespesa;

    @Autowired
    MovimentacaoController mController;

    private Movimentacao movimentacao;

    private NovaMovimentacao novaMovimentacao;
    @BeforeEach
    void setup(){
        movimentacao = mock(Movimentacao.class);
        novaMovimentacao = mock(NovaMovimentacao.class);
        when(serviceTodasMovimentacoes.listaTodasMovimentacoes()).thenReturn(List.of(movimentacao));
        when(serviceMovimentacao.mostrarMovimentacao(1)).thenReturn(List.of(movimentacao));
    }

    @Test
    @DisplayName("Deve retornar todas as movimentações feitas")
    void DeveRetornarTodasAsMovimentacoes(){
        List<Movimentacao> resultado = mController.findAll();
        assertEquals(List.of(movimentacao),resultado);
        assertNotNull(resultado);
        verify(serviceTodasMovimentacoes).listaTodasMovimentacoes();
    }

    @Test
    @DisplayName("Deve retornar uma movimentação especifica")
    void DeveRetornarUmaMovimentacao(){
        List<Movimentacao> resultado = mController.findById(1);
        assertEquals(List.of(movimentacao),resultado);
        assertNotNull(resultado);
        verify(serviceMovimentacao).mostrarMovimentacao(ArgumentMatchers.anyInt());
    }

    @Test
    @DisplayName("Deve criar uma movimentação")
    void DeveCriarUmaMovimentacao(){
        mController.save(novaMovimentacao);
        verify(serviceMovimentacaoImpl).createmovement(ArgumentMatchers.any(NovaMovimentacao.class));
    }


}
