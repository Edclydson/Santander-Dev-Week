package com.dio.santander.banklineapi.services.impl.movimentacao;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.exceptions.InsuficientFundsException;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.services.interfaces.movimentacao.IDespesa;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DespesaImpl implements IDespesa{

    private final CorrentistaRepository correntistaRepository;
    private final SaldoImpl saldoImpl;
    private final MovimentacaoImpl movimentacaoImpl;

    public DespesaImpl(CorrentistaRepository correntistaRepository, SaldoImpl saldoImpl, MovimentacaoImpl movimentacaoImpl) {
        this.correntistaRepository = correntistaRepository;
        this.saldoImpl = saldoImpl;
        this.movimentacaoImpl = movimentacaoImpl;
    }

    @Override
    public void despesa(NovaMovimentacao movimentacao) throws InsuficientFundsException {
        Optional<Correntista> optionalCorrentista = correntistaRepository.findById(movimentacao.getIdConta());
        if(optionalCorrentista.isPresent() && saldoImpl.verificaSaldo(optionalCorrentista.get().getConta(), movimentacao.getValor())){
            Correntista correntista = optionalCorrentista.get();
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + (movimentacao.getValor() * -1));
            correntistaRepository.save(correntista);
            movimentacaoImpl.createmovement(movimentacao);
        } else {
            throw new InsuficientFundsException();
        }
    }
}
