package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.dto.UsuarioDTO;
import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
@Api(description = "Usuários")
public class UsuarioEndpoint {

    @Autowired
    private UsuarioRepository repository;

    @ApiOperation(value = "Cria um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lançamento criado com sucesso")
    })
    @PostMapping
    public void criar(@RequestBody Usuario usuario) {
        repository.save(usuario);
    }

    @ApiOperation(value = "Retorna uma lista de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuários retornados com sucesso")
    })
    @GetMapping
    public List<UsuarioDTO> buscarTodos(
            @RequestParam(required = false) Optional<Long> id,
            @RequestParam(required = false) Optional<String> nome,
            @RequestParam(required = false) Optional<String> email) {

        Usuario usuarioExample = new Usuario();
        usuarioExample.setId(id.orElse(null));
        usuarioExample.setEmail(email.orElse(null));
        usuarioExample.setNome(nome.orElse(null));

        return null;
    }

    @ApiOperation(value = "Retorna um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Usuario buscarPor(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }


    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
