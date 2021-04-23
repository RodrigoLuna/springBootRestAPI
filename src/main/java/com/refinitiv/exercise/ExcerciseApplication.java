package com.refinitiv.exercise;

import java.util.Arrays;
import java.util.List;

import com.refinitiv.exercise.model.Account;
import com.refinitiv.exercise.model.User;
import com.refinitiv.exercise.repository.AccountRepository;
import com.refinitiv.exercise.repository.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2	
public class ExcerciseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = 
		SpringApplication.run(ExcerciseApplication.class, args);

		AccountRepository accountRepository = configurableApplicationContext.getBean(AccountRepository.class);
		UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);
		
		Account mxnAccount = new Account("MEXICO", Account.Currency.MXN);
		Account usdAccount = new Account("UNITED STATES OF AMERICA", Account.Currency.USD);
		Account engAccount = new Account("ENGLAND", Account.Currency.GBP);
		Account jpnAccount = new Account("JAPAN", Account.Currency.JPY);
		Account chnAccount = new Account("CHINA", Account.Currency.CNY);
		List<Account> baseAccounts = Arrays.asList(mxnAccount, usdAccount, engAccount, jpnAccount, chnAccount);
		accountRepository.saveAll(baseAccounts);

		User firstUser = new User("Fernando Perez");
		User secondUser = new User("Margarita Lopez");
		User thirdUser = new User("William Scaffold");
		List<User> baseUsers = Arrays.asList(firstUser, secondUser, thirdUser);

		firstUser.addAccount(mxnAccount);
		secondUser.addAccount(usdAccount);
		thirdUser.addAccount(mxnAccount);
		thirdUser.addAccount(engAccount);
		
		userRepository.saveAll(baseUsers);
	}

}
