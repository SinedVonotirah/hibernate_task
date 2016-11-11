package by.vonotirah.hibernate_task.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import by.vonotirah.hibernate_task.dataacces.ClientDao;
import by.vonotirah.hibernate_task.dataacces.impl.ClientDaoImpl;
import by.vonotirah.hibernate_task.datamodel.Client;

public class ClientDaoModuleTest extends AbstractTest {

	private Client mockedClient;
	private ClientDao clientDao;
	private Session mockedSession;
	private SessionFactory mockedSessionFactory;
	private Transaction mockedTransaction;

	@Before
	public void dependencies() {
		clientDao = new ClientDaoImpl();
		mockedClient = mock(Client.class);
		mockedSessionFactory = mock(SessionFactory.class);
		mockedSession = mock(Session.class);
		mockedTransaction = mock(Transaction.class);

		when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);

		clientDao.setSessionFactory(mockedSessionFactory);
	}

	@Test
	public void persistCallTest() {
		clientDao.persist(mockedClient);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).beginTransaction();
		verify(mockedSession).persist(mockedClient);
		verify(mockedTransaction).commit();
		verify(mockedSession).close();
	}

	@Test
	public void deleteByIdCallTest() {
		Long randomLong = getRandomLong();
		when(mockedSession.get(Client.class, randomLong)).thenReturn(mockedClient);
		clientDao.deleteById(randomLong);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).beginTransaction();
		verify(mockedSession).get(Client.class, randomLong);
		verify(mockedSession).delete(mockedClient);
		verify(mockedTransaction).commit();
		verify(mockedSession).close();
	}

	@Test
	public void updateCallTest() {
		clientDao.update(mockedClient);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).beginTransaction();
		verify(mockedSession).update(mockedClient);
		verify(mockedTransaction).commit();
		verify(mockedSession).close();
	}

	@Test
	public void getByIdCallTest() {
		Long randomLong = getRandomLong();
		clientDao.getById(randomLong);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).get(Client.class, randomLong);
		verify(mockedSession).close();
	}
}
