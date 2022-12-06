//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.TransactionDto;
//import com.appbackend.appbackend.model.Transaction;
//
//import java.util.function.Function;
//
//public class TransactionToDto implements Function<Transaction, TransactionDto> {
//
//    @Override
//    public TransactionDto apply(Transaction transaction) {
//        TransactionDto transactionDto = new TransactionDto();
//        transactionDto.setTransactionId(transaction.getTransactionId());
//        transactionDto.setQuantityTransacted(transaction.getQuantityTransacted());
//        transactionDto.setCryptoId(transaction.getCryptoId());
//        transactionDto.setExchangeRate(transaction.getExchangeRate());
//        transactionDto.setCryptoName(transaction.getCryptoName());
//        transactionDto.setAccount(transaction.getAccount().getId());
//        return new TransactionDto(transaction.getTransactionId(), transaction.getAccount().toString(),transaction.getCryptoId(), transaction.getCryptoName(), transaction.getExchangeRate(), transaction.getQuantityTransacted());
//    }
//}
