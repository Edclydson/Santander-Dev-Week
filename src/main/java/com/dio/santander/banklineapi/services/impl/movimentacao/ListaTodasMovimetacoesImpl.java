package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.IListaTodasMovimentacoes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaTodasMovimetacoesImpl implements IListaTodasMovimentacoes{

    private final MovimentacaoRepository movimentacaoRepository;

    public ListaTodasMovimetacoesImpl(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Override
    public List<Movimentacao> listaTodasMovimentacoes() {
        return movimentacaoRepository.findAll();
    }
}
