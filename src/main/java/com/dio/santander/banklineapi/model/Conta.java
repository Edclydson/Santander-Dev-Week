package com.dio.santander.banklineapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Conta {

    @Column(name = "conta_numero")
    //@Schema(description = "Numero de Identificação da conta, que é gerado usando Date.getTime()", name = "numero",type = "Long")
    private Long numero;
    @Column(name = "conta_saldo")
    //@Schema(description = "Saldo da conta", name = "saldo",type = "Double")
    private Double saldo;


    public Long getNumero() {
        return numero;
    }
    public void setNumero(Long numero) {
        this.numero = numero;
    }
    public Double getSaldo() {
        return saldo;
    }
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    
    
}
