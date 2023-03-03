package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.IDespesa;
import org.springframework.stereotype.Component;

@Component
public class DespesaImpl implements IDespesa{

    private final CorrentistaRepository correntistaRepository;

    public DespesaImpl(CorrentistaRepository correntistaRepository) {
        this.correntistaRepository = correntistaRepository;
    }

    @Override
    public void despesa(NovaMovimentacao movimentacao) {
        Correntista correntista = correntistaRepository.findById(movimentacao.getIdConta()).orElse(null);
        if(correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + (movimentacao.getValor() * -1));
            correntistaRepository.save(correntista);
        }
    }

}
