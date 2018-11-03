package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.service.ServiceAbstrata;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ControllerAbstrata<S extends ServiceAbstrata<T, ID>, T, ID> {

    @Autowired
    protected S service;

    public ControllerAbstrata(S service) {
        service = service;
    }


}
