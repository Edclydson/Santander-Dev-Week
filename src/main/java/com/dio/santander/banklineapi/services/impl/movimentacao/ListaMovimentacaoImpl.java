package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.IListaMovimentacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaMovimentacaoImpl implements IListaMovimentacao{

    private final MovimentacaoRepository movimentacaoRepository;

    public ListaMovimentacaoImpl(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Override
    public List<Movimentacao> mostrarMovimentacao(Integer idConta) {
        return movimentacaoRepository.findByIdConta(idConta);
    }
}
