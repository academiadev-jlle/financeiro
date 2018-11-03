package br.com.academiadev.financeiro.mapper;

import br.com.academiadev.financeiro.dto.UsuarioDTO;
import br.com.academiadev.financeiro.model.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = UsuarioMapperImpl.class)
public class UsuarioMapperTest {

    @Autowired
    private UsuarioMapper mapper;

    @Test
    public void teste01() {
        Usuario usuario = new Usuario();
        usuario.setNome("Bruno");
        UsuarioDTO usuarioDTO = mapper.toDTO(usuario);
        Assertions.assertThat(usuarioDTO.getNome()).isEqualTo("Bruno");
    }

}
