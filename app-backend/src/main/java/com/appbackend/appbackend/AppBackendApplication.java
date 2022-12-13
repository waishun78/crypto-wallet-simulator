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
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AppBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
//		SpringApplication.run(AppBackendApplication.class, args);
		new SpringApplicationBuilder(AppBackendApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
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

	}
}
