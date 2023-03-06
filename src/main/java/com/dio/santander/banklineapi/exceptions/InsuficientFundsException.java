package com.dio.santander.banklineapi.exceptions;

public class InsuficientFundsException extends Exception{

    public InsuficientFundsException(){
        super("Saldo insuficiente");
    }
}
