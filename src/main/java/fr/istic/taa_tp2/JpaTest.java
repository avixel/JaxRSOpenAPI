package fr.istic.taa_tp2;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//EntityManagerFactory factory = Persistence
				//.createEntityManagerFactory("dev");
		EntityManager manager = EntityManagerHelper.getEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			Kanban k = new Kanban("testKanban", "allan", "tours", "https://test.com/test", "note?", "test, test2",
					new Date(), 60);
			manager.persist(k);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		manager.close();
		//factory.close();
	}
}
