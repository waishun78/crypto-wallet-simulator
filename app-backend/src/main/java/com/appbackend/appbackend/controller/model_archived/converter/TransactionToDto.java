package com.appbackend.appbackend.controller.model_archived.converter;

import com.appbackend.appbackend.controller.model_archived.TransactionDto;
import com.appbackend.appbackend.model.Transaction;

import java.util.function.Function;

public class TransactionToDto implements Function<Transaction, TransactionDto> {

    @Override
    public TransactionDto apply(Transaction transaction) {
        return new TransactionDto(transaction.getTransactionId(), transaction.getAccount().toString(),transaction.getCryptoId(), transaction.getCryptoName(), transaction.getExchangeRate(), transaction.getQuantityTransacted());
    }
}
