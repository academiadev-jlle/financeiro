package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.repository.LancamentoFinanceiroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentofinanceiro")
@Api(description = "Lançamentos Financeiros")
public class LancamentoFinanceiroEndpoint {

	@Autowired
	private LancamentoFinanceiroRepository repository;

	@ApiOperation(value = "Retorna uma lista de lançamentos")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Lançamentos retornados com sucesso")})
	@GetMapping
	public List<LancamentoFinanceiro> buscarTodos() {
		return repository.findAll();
	}

	@ApiOperation(value = "Retorna um lançamento financeiro")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Lançamento financeiro encontrado com sucesso")})
	@GetMapping("/{id}")
	public List<LancamentoFinanceiro> buscarPor(@PathVariable Long id) {

		LancamentoFinanceiro lancamento = new LancamentoFinanceiro();
		lancamento.setId(id);

		return repository.findAll(Example.of(lancamento));
	}

	@ApiOperation(value = "Cria um lançamento financeiro")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Lançamento criado com sucesso")})
	@PostMapping
	public LancamentoFinanceiro criar(@RequestBody LancamentoFinanceiro lancamento) {
		return repository.save(lancamento);
	}

	@ApiOperation(value = "Deleta um lançamento financeiro")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Lançamento deletado com sucesso")})
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
