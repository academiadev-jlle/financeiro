package br.com.academiadev.financeiro.model;

import br.com.academiadev.financeiro.enums.Status;
import br.com.academiadev.financeiro.enums.TipoLancamento;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LancamentoFinanceiro extends EntidadeAuditavel<Long> {

    @Id
    @GeneratedValue
    @ApiModelProperty(example = "1", name = "Identificador")
    private Long id;

    @NotNull
    @ManyToOne(optional = false)
    @ApiModelProperty(name = "Usuário")
    private Usuario usuario;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(name = "Tipo de Lançamento", example = "PAGAR")
    private TipoLancamento tipolancamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(name = "Status", example = "PENDENTE")
    private Status status;

    @NotNull
    @ApiModelProperty(name = "Data de Emissão", example = "01/01/2018")
    private LocalDate dataEmissao;

    @NotNull
    @ApiModelProperty(name = "Data de Vencimento", example = "01/01/2018")
    private LocalDate dataVencimento;

    @NotNull
    @Size(min = 1, max = 120)
    @ApiModelProperty(name = "Nome do Recebedor/Pagador", example = "Osmar da Silva")
    private String recebedorPagador;

    @ApiModelProperty(name = "Valor", example = "500")
    private BigDecimal valor;


}
