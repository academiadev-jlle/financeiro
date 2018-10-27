package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class LancamentoFinanceiroEndpoint {

    @Autowired
    private UsuarioService service;


    @GetMapping("/lancamentofinanceiro")
    public List<LancamentoFinanceiro> getLancamentosFinanceiros() {
        return StreamSupport.stream(service.getLancamentoFinanceiroRepository().findAll().spliterator(), false).collect(Collectors.toList());
    }

    @PostMapping("/lancamentofinanceiro")
    public LancamentoFinanceiro create(@RequestBody LancamentoFinanceiro lancamentoFinanceiro) {
        return service.getLancamentoFinanceiroRepository().save(lancamentoFinanceiro);
    }

    @DeleteMapping("/lancamentofinanceiro")
    public void delete(Long idLancamentoFinanceiro) {
        service.remove(idLancamentoFinanceiro);
    }


}
