package com.dio.santander.banklineapi.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name="tab_movimentacao")
public class Movimentacao {


    public Movimentacao(LocalDateTime dataHora,
                        String descricao,
                        Double valor,
                        MovimentacaoTipo tipo,
                        Integer idConta) {
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.idConta = idConta;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Numero de identificação da movimentação", name = "id",type = "Integer")
    private Integer id;
    @Schema(description = "Data e hora que a movimentação foi feita", name = "dataHora", type = "LocalDateTime")
    private LocalDateTime dataHora;
    @Schema(description = "Breve descrição sobre a movimentação", name = "descricao",type = "String")
    private String descricao;
    @Schema(description = "Valor da movimentação", name = "valor",type = "Double")
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo da movimentação (RECEITA ou DESPESA)", name = "tipo",type = "MovimentacaoTipo")
    private MovimentacaoTipo tipo;

    @Column(name="id_conta")
    @Schema(description = "Numero de identificação da conta que fez a movimentação", name = "idConta", type = "Integer")
    private Integer idConta;

}
