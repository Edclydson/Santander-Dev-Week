package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.IReceita;
import org.springframework.stereotype.Component;

@Component
public class ReceitaImpl implements IReceita{

    private final CorrentistaRepository correntistaRepository;

    public ReceitaImpl(CorrentistaRepository correntistaRepository) {
        this.correntistaRepository = correntistaRepository;
    }

    @Override
    public void receita(NovaMovimentacao movimentacao) {
        Correntista correntista = correntistaRepository.findById(movimentacao.getIdConta()).orElse(null);
        if(correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + movimentacao.getValor());
            correntistaRepository.save(correntista);
        }
    }
}
