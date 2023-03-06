package com.dio.santander.banklineapi.services.interfaces.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.exceptions.InsuficientFundsException;

public interface IDespesa{
    void despesa(NovaMovimentacao movimentacao) throws InsuficientFundsException;
}
