//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.AssetDto;
//import com.appbackend.appbackend.controller.model.TransactionDto;
//import com.appbackend.appbackend.model.Asset;
//import com.appbackend.appbackend.model.Transaction;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Function;
//
//@Component
//public class TransactionToEntityConverter implements Function<TransactionToDto, Transaction> {
//    @Override
//    public Transaction apply(TransactionDto transactionDto) {
//        Transaction transaction = new Transaction();
//        transaction.setTransactionId(transactionDto.getTransactionId());
//        transaction.setQuantityTransacted(transactionDto.getQuantityTransacted());
//        transaction.setCryptoId(transactionDto.getCryptoId());
//        transaction.setExchangeRate(transactionDto.getExchangeRate());
//        transaction.setCryptoName(transactionDto.getCryptoName());
//        transaction.setAccount(/** TODO: Instantiate accountRepository using autowired then search for account **/);
//        return transaction;
//    }
//}
