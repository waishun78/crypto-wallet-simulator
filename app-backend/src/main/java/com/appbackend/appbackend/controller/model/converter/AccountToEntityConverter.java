//package com.appbackend.appbackend.controller.model.converter;
//
//import com.appbackend.appbackend.controller.model.AccountDto;
//import com.appbackend.appbackend.controller.model.AssetDto;
//import com.appbackend.appbackend.model.Account;
//import com.appbackend.appbackend.model.Asset;
//import org.springframework.stereotype.Component;
//
//import java.util.function.Function;
//
//@Component
//public class AccountToEntityConverter implements Function<AccountDto, Account> {
//
//    // instantiate accountservice using autowired
//    // accountservice.func(account) -> return assets
//
//    @Override
//    public Account apply(AccountDto accountDto) {
//        Account account = new Account();
//        account.setUsername(account.getUsername());
//        account.setNotes(account.getNotes());
//        account.setAccountBalance(accountDto.getAccountBalance());
//
//        account.setAssets(/** TODO: Collection of Assets**/);
//        account.setTransactions(/** TODO: Collection of Transactions **/);
//        account.setSnapshots(/** TODO: Collection of Snapshots **/);
//
//        return asset;
//    }
//}
