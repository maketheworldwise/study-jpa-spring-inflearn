package com.example;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		try {
			Child child1 = new Child();
			child1.setName("child1");

			Child child2 = new Child();
			child2.setName("child2");

			Parent parent = new Parent();
			parent.addChild(child1);
			parent.addChild(child2);

			entityManager.persist(parent);
			// entityManager.persist(child1);
			// entityManager.persist(child2);

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
