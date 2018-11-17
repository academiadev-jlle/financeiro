package br.com.academiadev.financeiro.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Usuário")
@Entity
public class Usuario extends EntidadeAuditavel<Long> {

    @NotNull()
    @Size(min = 3, max = 120)
    @ApiModelProperty(example = "Bruno Muehlbauer de Souza", name = "Nome")
    private String nome;

    @Email
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
}
