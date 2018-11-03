package br.com.academiadev.financeiro.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Usuário")
@Entity
public class Usuario extends EntidadeAuditavel<Long> implements UserDetails {

    @NotNull()
    @Size(min = 3, max = 120)
    @ApiModelProperty(example = "Bruno Muehlbauer de Souza", name = "Nome")
    private String nome;

    @NotNull
    @Size(min = 3, max = 60)
    @ApiModelProperty(example = "docsbruno@gmail.com", name = "E-mail")
    private String email;

    @NotNull
    @Size(min = 6, max = 16)
    @ApiModelProperty(example = "123456", name = "Senha")
    private String senha;

    public Usuario(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
