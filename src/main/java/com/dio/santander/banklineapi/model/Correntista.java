package com.dio.santander.banklineapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tab_correntista")
public class Correntista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Schema(description = "Id do correntista",name = "id",type = "Integer")
    private Integer id;
    //@Schema(description = "Nome do correntista",name = "nome",type = "String")
    private String nome;
    
    @Column(length = 11)
    //@Schema(description = "Numero do cpf do correntista", name = "cpf", type = "String")
    private String cpf;
    
    @Embedded
    //@Schema(description = "Conta vinculada ao correntista", name = "conta", type = "Conta")
    private Conta conta;
    
    
    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
