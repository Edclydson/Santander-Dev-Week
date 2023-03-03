package com.dio.santander.banklineapi.services.interfaces.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;

public interface IMovimentacao{

    void createmovement(NovaMovimentacao movimentacao);
}
