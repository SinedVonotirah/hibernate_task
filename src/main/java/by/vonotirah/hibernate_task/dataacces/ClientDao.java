package by.vonotirah.hibernate_task.dataacces;

import by.vonotirah.hibernate_task.datamodel.Client;

public interface ClientDao extends AbstractDao<Client> {

	void persist(Client client);

	public void deleteById(Long id);

	void update(Client client);

	Client getById(Long id);
}
