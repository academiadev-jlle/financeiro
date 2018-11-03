package br.com.academiadev.financeiro.service;

import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceAbstrataImpl<UsuarioRepository, Usuario, Long> {

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    public Usuario findByEmail(String email) {
        return getRepository().findByEmail(email);
    }


}
