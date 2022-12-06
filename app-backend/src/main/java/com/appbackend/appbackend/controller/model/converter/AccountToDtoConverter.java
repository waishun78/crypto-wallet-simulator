//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.AccountDto;
//import com.appbackend.appbackend.model.Account;
//
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//public class AccountToDtoConverter implements Function<Account, AccountDto> {
//    @Override
//    public AccountDto apply(Account account) {
//        return new AccountDto(
//                account.getUsername(),
//                account.getNotes(),
//                account.getAccountBalance(),
//                account.getAssets().stream().map(asset -> asset.getAssetId()).collect(Collectors.toList()),
//                account.getSnapshots().stream().map(snapshot -> snapshot.getSnapshotId()).collect(Collectors.toList()),
//                account.getTransactions().stream().map(transaction -> transaction.getTransactionId()).collect(Collectors.toList())
//                );
//    }
//}
