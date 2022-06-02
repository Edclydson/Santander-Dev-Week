package com.dio.santander.banklineapi.services;

import java.time.LocalDateTime;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.model.MovimentacaoTipo;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovaMovimentacaoService {
    

    @Autowired
    MovimentacaoRepository repository;

    @Autowired
    CorrentistaRepository correntistarepository;

    public void save(NovaMovimentacao novaMovimentacao){
        Movimentacao movimentacao = new Movimentacao();
        Double valor = novaMovimentacao.getTipo()==MovimentacaoTipo.RECEITA ? novaMovimentacao.getValor() : novaMovimentacao.getValor() * -1;
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(novaMovimentacao.getDescricao());
        movimentacao.setIdConta(novaMovimentacao.getIdConta());
        movimentacao.setTipo(novaMovimentacao.getTipo());
        movimentacao.setValor(valor);

        Correntista correntista = correntistarepository.findById(novaMovimentacao.getIdConta()).orElse(null);
        if(correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistarepository.save(correntista);
        }

        repository.save(movimentacao);
    }
}
