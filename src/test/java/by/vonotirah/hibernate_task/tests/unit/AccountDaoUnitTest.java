package by.vonotirah.hibernate_task.tests.unit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import by.vonotirah.hibernate_task.dataacces.AccountDao;
import by.vonotirah.hibernate_task.dataacces.impl.AccountDaoImpl;
import by.vonotirah.hibernate_task.datamodel.Account;
import by.vonotirah.hibernate_task.tests.AbstractTest;

public class AccountDaoUnitTest extends AbstractTest {

	private Account mockedAccount;
	private AccountDao accountDao;
	private Session mockedSession;
	private SessionFactory mockedSessionFactory;
	private Transaction mockedTransaction;

	@Before
	public void dependencies() {
		accountDao = new AccountDaoImpl();
		mockedAccount = mock(Account.class);
		mockedSessionFactory = mock(SessionFactory.class);
		mockedSession = mock(Session.class);
		mockedTransaction = mock(Transaction.class);

		when(mockedSessionFactory.openSession()).thenReturn(mockedSession);
		when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);

		accountDao.setSessionFactory(mockedSessionFactory);
	}

	@Test
	public void persistCallTest() {
		accountDao.persist(mockedAccount);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).beginTransaction();
		verify(mockedSession).persist(mockedAccount);
		verify(mockedTransaction).commit();
		verify(mockedSession).close();
	}

	@Test
	public void deleteByIdCallTest() {
		Long randomLong = getRandomLong();
		when(mockedSession.get(Account.class, randomLong)).thenReturn(mockedAccount);
		accountDao.deleteById(randomLong);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).beginTransaction();
		verify(mockedSession).get(Account.class, randomLong);
		verify(mockedSession).delete(mockedAccount);
		verify(mockedTransaction).commit();
		verify(mockedSession).close();
	}

	@Test
	public void updateCallTest() {
		accountDao.update(mockedAccount);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).beginTransaction();
		verify(mockedSession).update(mockedAccount);
		verify(mockedTransaction).commit();
		verify(mockedSession).close();
	}

	@Test
	public void getByIdCallTest() {
		Long randomLong = getRandomLong();
		accountDao.getById(randomLong);

		verify(mockedSessionFactory).openSession();
		verify(mockedSession).get(Account.class, randomLong);
		verify(mockedSession).close();
	}
}
