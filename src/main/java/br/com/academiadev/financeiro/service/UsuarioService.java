package br.com.academiadev.financeiro.service;

import br.com.academiadev.financeiro.repository.LancamentoFinanceiroRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

    public void remove(Long idLancamentoFinanceiro) {
        if (lancamentoFinanceiroRepository.existsById(idLancamentoFinanceiro)) {
            lancamentoFinanceiroRepository.deleteById(idLancamentoFinanceiro);
        } else {
            throw new IllegalArgumentException("O lançamento com o id " + idLancamentoFinanceiro + " não existe");
        }
    }

    public LancamentoFinanceiroRepository getLancamentoFinanceiroRepository() {
        return lancamentoFinanceiroRepository;
    }

}
