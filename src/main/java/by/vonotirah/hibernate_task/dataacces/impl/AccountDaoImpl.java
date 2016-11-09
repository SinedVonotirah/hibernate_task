package by.vonotirah.hibernate_task.dataacces.impl;

import by.vonotirah.hibernate_task.dataacces.AccountDao;
import by.vonotirah.hibernate_task.datamodel.Account;

public class AccountDaoImpl extends AbstractDaoImpl<Account> implements AccountDao {

	public AccountDaoImpl() {
		super(Account.class);
	}

	public void persist(Account account) {
		openSessionWithTransaction();
		getSession().persist(account);
		closeSessionWithTransaction();
	}

	public void deleteById(Long id) {
		openSessionWithTransaction();
		Account account = getSession().get(Account.class, id);
		getSession().delete(account);
		closeSessionWithTransaction();
	}

	public void update(Account account) {
		openSessionWithTransaction();
		getSession().update(account);
		closeSessionWithTransaction();
	}

	public Account getById(Long id) {
		openSession();
		Account account = getSession().get(Account.class, id);
		closeSession();
		return account;
	}
}
