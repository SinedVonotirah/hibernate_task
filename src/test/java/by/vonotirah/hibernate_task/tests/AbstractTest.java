package by.vonotirah.hibernate_task.tests;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import by.vonotirah.hibernate_task.datamodel.Account;
import by.vonotirah.hibernate_task.datamodel.Client;

public abstract class AbstractTest {

	private static final int RANDOM_STRING_SIZE = 6;

	public AbstractTest() {
		super();
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

	public long getRandomLong() {
		long LOWER_RANGE = 0;
		long UPPER_RANGE = 1000000;
		Random random = new Random();
		long randomValue = LOWER_RANGE + (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
		return randomValue;
	}
}
