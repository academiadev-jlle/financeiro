package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.dto.UsuarioDTO;
import br.com.academiadev.financeiro.mapper.UsuarioMapper;
import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.service.UsuarioService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
@Api(description = "Usuários")
public class UsuarioEndpoint {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper mapper;

    @ApiOperation(value = "Cria um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lançamento criado com sucesso")
    })
    @PostMapping
    public void criar(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
    }

    @ApiOperation(value = "Retorna uma lista de usuários")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuários retornados com sucesso")
    })
    @ApiImplicitParams({ //
            @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") //
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<UsuarioDTO> buscarTodos(
            @RequestParam(required = false) Optional<Long> id,
            @RequestParam(required = false) Optional<String> nome,
            @RequestParam(required = false) Optional<String> email) {

        Usuario usuarioExample = new Usuario();
        usuarioExample.setId(id.orElse(null));
        usuarioExample.setEmail(email.orElse(null));
        usuarioExample.setNome(nome.orElse(null));

        return mapper.toDTO(usuarioService.findAll(Example.of(usuarioExample)));
    }

    @ApiOperation(value = "Retorna um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário encontrado com sucesso")
    })
    @GetMapping("/{id}")
    public Usuario buscarPor(@PathVariable Long id) {
        return usuarioService.findById(id).orElse(null);
    }


    @ApiOperation(value = "Deleta um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário deletado com sucesso")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
