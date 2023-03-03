package com.dio.santander.banklineapi.services.interfaces.movimentacao;

import com.dio.santander.banklineapi.model.Movimentacao;

import java.util.List;

public interface IListaMovimentacao{
    List<Movimentacao> mostrarMovimentacao(Integer idConta);
}
