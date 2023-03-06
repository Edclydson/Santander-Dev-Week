package com.dio.santander.banklineapi.services;

import com.dio.santander.banklineapi.ApplicationConfigTest;
import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.exceptions.InsuficientFundsException;
import com.dio.santander.banklineapi.model.Conta;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.services.impl.movimentacao.DespesaImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.MovimentacaoImpl;
import com.dio.santander.banklineapi.services.impl.movimentacao.SaldoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.mock;

@DisplayName("DespesaImplTest")
public class DespesaTest extends ApplicationConfigTest{

    @MockBean
    CorrentistaRepository correntistaRepository;

    @MockBean
    SaldoImpl saldoImpl;

    @MockBean
    MovimentacaoImpl movimentacaoImpl;

    @Autowired
    DespesaImpl despesaImpl;

    private Correntista correntista;
    private Conta conta;
    private NovaMovimentacao novaMovimentacao;
    private Movimentacao movimentacao;

    @BeforeEach
    void setup(){
        correntista = mock(Correntista.class);
        conta = mock(Conta.class);
        novaMovimentacao = mock(NovaMovimentacao.class);
        movimentacao = mock(Movimentacao.class);
    }


    @Test
    @DisplayName("Deve realizar o desconto na conta do correntista com sucesso")
    void DeveRealizarDescontoNaContaDoCorrentista_ComSucesso() throws InsuficientFundsException {
        NovaMovimentacao novaMovimentacao = new NovaMovimentacao();
        novaMovimentacao.setIdConta(1);
        novaMovimentacao.setValor(100.0);
        Mockito.when(correntistaRepository.findById(1)).thenReturn(Optional.of(correntista));
        Mockito.when(correntista.getConta()).thenReturn(conta);
        Mockito.when(saldoImpl.verificaSaldo(conta, novaMovimentacao.getValor())).thenReturn(true);
        Mockito.when(movimentacaoImpl.createmovement(novaMovimentacao)).thenReturn(movimentacao);

        despesaImpl.despesa(novaMovimentacao);

        Mockito.verify(correntistaRepository).save(ArgumentMatchers.any(Correntista.class));
    }

    @Test
    @DisplayName("Deve lançar a exceção InsuficientFundsException")
    void DeveDispararException_AoRealizarDescontoNaContaDoCorrentista() {
        NovaMovimentacao novaMovimentacao = new NovaMovimentacao();
        novaMovimentacao.setIdConta(1);
        novaMovimentacao.setValor(100.0);

        Mockito.when(correntistaRepository.findById(1)).thenReturn(Optional.of(correntista));
        Mockito.when(correntista.getConta()).thenReturn(conta);
        Mockito.when(saldoImpl.verificaSaldo(conta, novaMovimentacao.getValor())).thenReturn(false);

        Assertions.assertThrows(InsuficientFundsException.class, () ->  despesaImpl.despesa(novaMovimentacao),"Saldo insuficiente");;
    }
}
