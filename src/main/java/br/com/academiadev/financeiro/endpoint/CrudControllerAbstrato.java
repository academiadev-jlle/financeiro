package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.service.ServiceAbstrata;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class CrudControllerAbstrato<S extends ServiceAbstrata<T, ID>, T, ID> extends ControllerAbstrata<S, T, ID> {

    public CrudControllerAbstrato(S service) {
        super(service);
    }

    @ApiOperation(value = "Cria uma entidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidade criada com sucesso")
    })
    @PostMapping
    public void criar(@RequestBody @Valid T usuario) {
        service.save(usuario);
    }

    @ApiOperation(value = "Retorna uma lista de entidades")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidades retornadas com sucesso")
    })
    @ApiImplicitParams({ //
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") //
    })
    @GetMapping
    public List<T> buscarTodos(
            @RequestParam(required = false) Optional<Long> id,
            @RequestParam(required = false) Optional<String> nome,
            @RequestParam(required = false) Optional<String> email) {
        return service.findAll();
    }

    @ApiOperation(value = "Retorna uma entidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidade encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public T buscarPor(@PathVariable ID id) {
        return service.findById(id).orElse(null);
    }


    @ApiOperation(value = "Deleta uma entidade")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Entidade deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable ID id) {
        service.deleteById(id);
    }
}
