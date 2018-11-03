package br.com.academiadev.financeiro.service;

import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.repository.LancamentoFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoFinanceiroService extends ServiceAbstrataImpl<LancamentoFinanceiroRepository, LancamentoFinanceiro, Long> {

    @Autowired
    public LancamentoFinanceiroService(LancamentoFinanceiroRepository repository) {
        super(repository);
    }
}
