package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.exception.NaoEncontradoException;
import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
@Api(description = "Usuários")
public class UsuarioEndpoint extends CrudControllerAbstrato<UsuarioService, Usuario, Long> {

    @Autowired
    public UsuarioEndpoint(UsuarioService service) {
        super(service);
    }

    @GetMapping("/teste")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Usuario> getTodos() throws NaoEncontradoException {
        if (true) {
            throw new NaoEncontradoException("Minha mensagem");
        }
        return new ArrayList<>();
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(UNPROCESSABLE_ENTITY)
//    @ResponseBody
//    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
//        Map<String, String> fieldErrors = ex.getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .collect(Collectors.toMap(p -> p.getField(), FieldError::getDefaultMessage));
//
//        return ValidationErrorDTO.builder().message("Não foi possível processar esta requisição.")
//                .status(UNPROCESSABLE_ENTITY.value())
//                .timestamp(System.currentTimeMillis())
//                .erros(fieldErrors).build();
//
//    }

}
