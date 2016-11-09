package by.vonotirah.hibernate_task.tests;

import org.apache.commons.lang3.RandomStringUtils;

import by.vonotirah.hibernate_task.dataacces.AccountDao;
import by.vonotirah.hibernate_task.dataacces.ClientDao;
import by.vonotirah.hibernate_task.dataacces.impl.AccountDaoImpl;
import by.vonotirah.hibernate_task.dataacces.impl.ClientDaoImpl;
import by.vonotirah.hibernate_task.datamodel.Account;
import by.vonotirah.hibernate_task.datamodel.Client;

public abstract class AbstractTest {

	private static final int RANDOM_STRING_SIZE = 6;

	public ClientDao clientDao;

	public AccountDao accountDao;

	public AbstractTest() {
		super();
		clientDao = new ClientDaoImpl();
		accountDao = new AccountDaoImpl();
	}

	private static String randomString() {
		return RandomStringUtils.randomAlphabetic(RANDOM_STRING_SIZE);
	}

	public static String randomString(final String prefix) {
		return String.format("%s-%s", new Object[] { prefix, randomString() });
	}

	public Client getRandomClientObject() {
		Client client = new Client();
		client.setFirstName(randomString("first_name"));
		client.setLastName(randomString("last_name"));
		return client;
	}

	public Account getRandomAccountObject() {
		Account account = new Account();
		account.setLogin(randomString("login"));
		account.setPassword(randomString("password"));
		return account;

	}
}
