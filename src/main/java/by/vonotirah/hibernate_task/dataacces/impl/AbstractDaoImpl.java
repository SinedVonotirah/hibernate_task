package by.vonotirah.hibernate_task.dataacces.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.vonotirah.hibernate_task.dataacces.AbstractDao;

public abstract class AbstractDaoImpl<Entity> implements AbstractDao<Entity> {

	private static SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	private final Class<Entity> entityClass;

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDaoImpl.class);

	public AbstractDaoImpl(final Class<Entity> entityClass) {
		this.entityClass = entityClass;
		setSessionFactory();
	}

	private static void setSessionFactory() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	private SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private Class<Entity> getEntityClass() {
		return entityClass;
	}

	public Session openSession() {
		session = getSessionFactory().openSession();
		return session;
	}

	public void closeSession() {
		session.close();
	}

	public Session openSessionWithTransaction() {
		session = getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;
	}

	public void closeSessionWithTransaction() {
		transaction.commit();
		session.close();
	}

	public Session getSession() {
		return this.session;
	}
}
