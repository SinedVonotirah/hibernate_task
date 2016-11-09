package by.vonotirah.hibernate_task.tests;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.hibernate_task.datamodel.Account;
import by.vonotirah.hibernate_task.datamodel.Client;

public class DaoTests extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DaoTests.class);

	@Test
	public void clientCRUDTest() {
		LOGGER.info("----------------clientCRUDTests()----------------------");

		LOGGER.info("----------------CREATE----------------------");
		Client client = getRandomClientObject();
		clientDao.persist(client);
		Client clientFromDB = clientDao.getById(client.getId());
		Assert.assertEquals(client, clientFromDB);

		LOGGER.info("----------------READ/UPDATE----------------------");
		client.setFirstName(randomString("updated first_name"));
		client.setLastName(randomString("updated last_name"));
		clientDao.update(client);
		Client updatedClient = clientDao.getById(client.getId());
		Assert.assertNotEquals(clientFromDB.getFirstName(), updatedClient.getFirstName());
		Assert.assertNotEquals(clientFromDB.getLastName(), updatedClient.getLastName());

		LOGGER.info("----------------DELETE----------------------");
		clientDao.deleteById(updatedClient.getId());
		Assert.assertNull(clientDao.getById(updatedClient.getId()));
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
