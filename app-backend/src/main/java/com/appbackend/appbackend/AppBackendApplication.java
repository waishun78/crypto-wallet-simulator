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


	@Override
	public void run(String... args) throws Exception {

		Account account = new Account("ben976");
		account.setAccountBalance(31414.134);
		account.setNotes("Minimal Risky");

		accountRepository.save(account);

		Transaction t = new Transaction();
		t.setCryptoId(Long.valueOf(12515));
		t.setExchangeRate(10000125.2141);
		t.setQuantityTransacted(757.24);
		transactionRepository.save(t);

		Snapshot snapshot = new Snapshot();
		snapshot.setAccount(account);
		snapshot.setAssetValue(10000024.42);
		snapshot.setAccountBalance(10000021515.24);

		snapshotRepository.save(snapshot);

		Asset asset = new Asset();
		asset.setAccount(account);
		asset.setQuantity(212.325);
		asset.setCryptoId(Long.valueOf(124214));
		assetRepository.save(asset);

	}
}
