package com.dio.santander.banklineapi.services.interfaces.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.exceptions.InsuficientFundsException;
import com.dio.santander.banklineapi.model.Movimentacao;

public interface IMovimentacao{

    Movimentacao createmovement(NovaMovimentacao movimentacao);

    void newMovement(NovaMovimentacao movimentacao) throws InsuficientFundsException;


}
