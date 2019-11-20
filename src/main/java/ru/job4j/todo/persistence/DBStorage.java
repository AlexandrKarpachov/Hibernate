package ru.job4j.todo.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ru.job4j.todo.models.Item;

public class DBStorage implements IStore {
	private static final DBStorage INSTANCE = new DBStorage();
	private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

	public static DBStorage getInstance() {
		return INSTANCE;
	}

	@Override
	public void addItem(Item item) {
		this.tx(session -> session.save(item));
	}

	@Override
	public void updateItem(Item item) {
		this.tx(session -> {
			session.update(item);
			return true;
			}
		);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAll() {
		return this.tx(session -> session.createQuery("from Item").list());
	}

	@Override
	public Item getByID(Long id) {
		return this.tx(session -> session.get(Item.class, id));
	}
	
	private <T> T tx(final Function<Session, T> command) {
	    final Session session = factory.openSession();
	    final Transaction tx = session.beginTransaction();
	    try {
	        T rsl = command.apply(session);
	        tx.commit();
	        return rsl;
	    } catch (final Exception e) {
	        session.getTransaction().rollback();
	        throw e;
	    } finally {
	        session.close();
	    }
	}
	
}
