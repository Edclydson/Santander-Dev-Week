package com.dio.santander.banklineapi.services.interfaces.movimentacao;

import com.dio.santander.banklineapi.model.Conta;

public interface ISaldo{

    boolean verificaSaldo(Conta Conta, Double valor);
}
