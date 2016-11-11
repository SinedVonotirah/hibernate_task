package by.vonotirah.hibernate_task.dataacces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface AbstractDao<Entity> {

	Session openSession();

	void closeSession();

	Session openSessionWithTransaction();

	void closeSessionWithTransaction();

	SessionFactory getSessionFactory();

	void setSessionFactory(SessionFactory sessionFactory);

	Session getSession();

	void setSession(Session session);

}
