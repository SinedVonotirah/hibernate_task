package by.vonotirah.hibernate_task.dataacces.impl;

import by.vonotirah.hibernate_task.dataacces.ClientDao;
import by.vonotirah.hibernate_task.datamodel.Client;

public class ClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {

	public ClientDaoImpl() {
		super(Client.class);
	}

	public void persist(Client client) {
		openSessionWithTransaction();
		getSession().persist(client);
		closeSessionWithTransaction();
	}

	public void deleteById(Long id) {
		openSessionWithTransaction();
		Client client = getSession().get(Client.class, id);
		getSession().delete(client);
		closeSessionWithTransaction();
	}

	public void update(Client client) {
		openSessionWithTransaction();
		getSession().update(client);
		closeSessionWithTransaction();
	}

	public Client getById(Long id) {
		openSession();
		Client client = getSession().get(Client.class, id);
		closeSession();
		return client;
	}
}
