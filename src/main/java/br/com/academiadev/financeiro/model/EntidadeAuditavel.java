package br.com.academiadev.financeiro.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class EntidadeAuditavel<ID> extends Entidade<ID> {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @ApiModelProperty(hidden = true)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @ApiModelProperty(hidden = true)
    protected LocalDateTime updatedAt;

}
