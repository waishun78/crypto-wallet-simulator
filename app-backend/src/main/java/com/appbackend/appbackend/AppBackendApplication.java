package com.appbackend.appbackend;

import com.appbackend.appbackend.model.Account;
import com.appbackend.appbackend.model.Asset;
import com.appbackend.appbackend.model.Snapshot;
import com.appbackend.appbackend.model.Transaction;
import com.appbackend.appbackend.repository.AccountRepository;
import com.appbackend.appbackend.repository.AssetRepository;
import com.appbackend.appbackend.repository.SnapshotRepository;
import com.appbackend.appbackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppBackendApplication.class, args);
	}

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private SnapshotRepository snapshotRepository;

	@Autowired
	private AssetRepository assetRepository;

	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry.addMapping("api/v1/accounts").allowedOrigins("http://localhost:8080");
	// 		}
	// 	};
	// }

	@Override
	public void run(String... args) throws Exception {

//		Account account = new Account("68853");
//		account.setAccountBalance(12555.3);
//		account.setNotes("Minimal Risky");
//
//		accountRepository.save(account);
//
//		Transaction t = new Transaction();
//		t.setCryptoId(Long.valueOf(346));
//		t.setExchangeRate(53353.4324);
//		t.setQuantityTransacted(212323.31);
//		t.setAccount(account);
//		transactionRepository.save(t);
//
//		Snapshot snapshot = new Snapshot();
//		snapshot.setAccount(account);
//		snapshot.setAssetValue(2440024.42);
//		snapshot.setAccountBalance(42421515.24);
//
//		snapshotRepository.save(snapshot);
//
//		Asset asset = new Asset();
//		asset.setAccount(account);
//		asset.setQuantity(0.325);
//		asset.setCryptoId(Long.valueOf(313));
//		assetRepository.save(asset);
//
//		// Second account
//
//		Account account2 = new Account("35352");
//		account2.setAccountBalance(242423.2424);
//		account2.setNotes("Testing");
//
//		accountRepository.save(account2);
//
//		Transaction t2 = new Transaction();
//		t2.setCryptoId(Long.valueOf(12515));
//		t2.setExchangeRate(2442.33);
//		t2.setQuantityTransacted(2123.31);
//		t2.setAccount(account2);
//		transactionRepository.save(t2);
//
//		Snapshot snapshot2 = new Snapshot();
//		snapshot2.setAccount(account2);
//		snapshot2.setAssetValue(10000024.42);
//		snapshot2.setAccountBalance(10000021515.24);
//
//		snapshotRepository.save(snapshot2);
//
//		Asset asset2 = new Asset();
//		asset2.setAccount(account2);
//		asset2.setQuantity(0.325);
//		asset2.setCryptoId(Long.valueOf(313));
//		assetRepository.save(asset2);

	}
}
