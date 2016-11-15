package by.vonotirah.hibernate_task.tests.integration;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.hibernate_task.dataacces.AccountDao;
import by.vonotirah.hibernate_task.dataacces.ClientDao;
import by.vonotirah.hibernate_task.dataacces.impl.AccountDaoImpl;
import by.vonotirah.hibernate_task.dataacces.impl.ClientDaoImpl;
import by.vonotirah.hibernate_task.datamodel.Account;
import by.vonotirah.hibernate_task.datamodel.Client;
import by.vonotirah.hibernate_task.tests.AbstractTest;

public class AccountDaoIntegrationTest extends AbstractTest {

	private static AccountDao accountDao;
	private static ClientDao clientDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDaoIntegrationTest.class);

	@BeforeClass
	public static void dependencies() {
		accountDao = new AccountDaoImpl();
		clientDao = new ClientDaoImpl();
	}

	@Test
	public void accountCRUDTest() {
		LOGGER.info("----------------accountCRUDTest()----------------------");

		Client client = getRandomClientObject();
		clientDao.persist(client);

		LOGGER.info("----------------CREATE----------------------");
		Account account = getRandomAccountObject();
		account.setClient(client);
		accountDao.persist(account);
		Account accountFromDB = accountDao.getById(account.getId());
		Assert.assertEquals(account, accountFromDB);

		LOGGER.info("----------------READ/UPDATE----------------------");
		account.setLogin(randomString("updated login"));
		account.setPassword(randomString("updated password"));
		accountDao.update(account);
		Account updatedAccount = accountDao.getById(account.getId());
		Assert.assertNotEquals(accountFromDB.getLogin(), updatedAccount.getLogin());

		LOGGER.info("----------------DELETE----------------------");
		accountDao.deleteById(updatedAccount.getId());
		Assert.assertNull(accountDao.getById(updatedAccount.getId()));
	}
}
