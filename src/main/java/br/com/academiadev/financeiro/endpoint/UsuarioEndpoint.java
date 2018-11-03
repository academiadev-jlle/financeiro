package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
@Api(description = "Usuários")
public class UsuarioEndpoint extends CrudControllerAbstrato<UsuarioService, Usuario, Long> {

    @Autowired
    public UsuarioEndpoint(UsuarioService service) {
        super(service);
    }


}
