package ru.job4j.todo.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Session session = factory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public void updateItem(Item item) {
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAll() {
		List<Item> result = new ArrayList<>();
		Session session = factory.openSession();
		session.beginTransaction();
		result = session.createQuery("from Item").list();
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@Override
	public Item getByID(Long id) {
		Session session = factory.openSession();
		session.beginTransaction();
		Item result = session.get(Item.class, id);
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
}
