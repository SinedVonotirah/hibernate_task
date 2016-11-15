package by.vonotirah.hibernate_task.tests.integration;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.hibernate_task.dataacces.ClientDao;
import by.vonotirah.hibernate_task.dataacces.impl.ClientDaoImpl;
import by.vonotirah.hibernate_task.datamodel.Client;
import by.vonotirah.hibernate_task.tests.AbstractTest;

public class ClientDaoIntegrationTest extends AbstractTest {

	private static ClientDao clientDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientDaoIntegrationTest.class);

	@BeforeClass
	public static void dependencies() {
		clientDao = new ClientDaoImpl();
	}

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

}
