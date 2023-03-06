package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.exceptions.InsuficientFundsException;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.model.MovimentacaoTipo;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.IMovimentacao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoImpl implements IMovimentacao{

    private final ReceitaImpl receita;
    private final DespesaImpl despesa;
    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoImpl(ReceitaImpl receita, DespesaImpl despesa, MovimentacaoRepository movimentacaoRepository) {
        this.receita = receita;
        this.despesa = despesa;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Override
    public Movimentacao createmovement(NovaMovimentacao movimentacao) {
        return movimentacaoRepository.save(new Movimentacao(
                LocalDateTime.now(),
                movimentacao.getDescricao(),
                movimentacao.getValor(),
                movimentacao.getTipo(),
                movimentacao.getIdConta()
        ));
    }

    @Override
    public void newMovement(NovaMovimentacao movimentacao) throws InsuficientFundsException {
        if (movimentacao.getTipo() == MovimentacaoTipo.RECEITA){
            receita.receita(movimentacao);
        } else {
            despesa.despesa(movimentacao);
        }
    }
}
