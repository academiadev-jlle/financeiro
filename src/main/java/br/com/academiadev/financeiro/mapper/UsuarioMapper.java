package br.com.academiadev.financeiro.mapper;

import br.com.academiadev.financeiro.dto.UsuarioDTO;
import br.com.academiadev.financeiro.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<Usuario, UsuarioDTO> {

    @Mappings({

            @Mapping(source = "createdAt", target = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    UsuarioDTO toDTO(Usuario entity);

    @Mappings({
            @Mapping(target = "createdAt", source = "created_at", dateFormat = "dd/MM/yyyy HH:mm")
    })
    @Override
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
