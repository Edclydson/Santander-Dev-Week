package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.model.Conta;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.ISaldo;
import org.springframework.stereotype.Component;

@Component
public class SaldoImpl implements ISaldo{

    @Override
    public boolean verificaSaldo(Conta conta, Double valor) {
        return conta.getSaldo() >= valor;
    }
}
