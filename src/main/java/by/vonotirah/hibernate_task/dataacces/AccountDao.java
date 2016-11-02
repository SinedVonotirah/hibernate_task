package by.vonotirah.hibernate_task.dataacces;

import by.vonotirah.hibernate_task.datamodel.Account;

public interface AccountDao extends AbstractDao<Account> {

	void persist(Account account);

	void deleteById(Long id);

	void update(Account account);
}
