package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.service.LancamentoFinanceiroService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamentofinanceiro")
@Api(description = "Lançamentos Financeiros")
public class LancamentoFinanceiroEndpoint extends CrudControllerAbstrato<LancamentoFinanceiroService, LancamentoFinanceiro, Long> {

    @Autowired
    public LancamentoFinanceiroEndpoint(LancamentoFinanceiroService service) {
        super(service);
    }
}
