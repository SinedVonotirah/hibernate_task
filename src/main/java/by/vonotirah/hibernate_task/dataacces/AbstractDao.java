package by.vonotirah.hibernate_task.dataacces;

import org.hibernate.Session;

public interface AbstractDao<Entity> {

	Session openSession();

	void closeSession();

	Session openSessionWithTransaction();

	void closeSessionWithTransaction();

	Session getSession();
}
