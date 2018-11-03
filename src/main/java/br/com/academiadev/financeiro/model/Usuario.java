package br.com.academiadev.financeiro.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "Usuário")
@Entity
public class Usuario {

	@Id
	@GeneratedValue
	@ApiModelProperty(example = "1", name = "Identificador")
	private Long id;

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

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	@ApiModelProperty(hidden = true)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@ApiModelProperty(hidden = true)
	private LocalDateTime updatedAt;

	public Usuario(Long id) {
		this.id = id;
	}

}
