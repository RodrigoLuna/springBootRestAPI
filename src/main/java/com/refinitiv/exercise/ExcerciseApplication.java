package com.refinitiv.exercise;

import java.util.Arrays;
import java.util.List;

import com.refinitiv.exercise.model.Account;
import com.refinitiv.exercise.model.User;
import com.refinitiv.exercise.repository.IAccountRepository;
import com.refinitiv.exercise.repository.IUserRepository;

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

		IAccountRepository accountRepository = configurableApplicationContext.getBean(IAccountRepository.class);
		IUserRepository userRepository = configurableApplicationContext.getBean(IUserRepository.class);

		Account mxnAccount = new Account("MEXICO", "MXN");
		Account usdAccount = new Account("UNITED STATES", "USD");
		Account engAccount = new Account("ENGLAND", "GBP");
		List<Account> baseAccounts = Arrays.asList(mxnAccount, usdAccount, engAccount);
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
