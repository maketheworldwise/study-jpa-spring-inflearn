package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		try {
			Member member1 = new Member("kevin1", 21, RoleType.USER, "description1");
			Member member2 = new Member("kevin2", 22, RoleType.ADMIN, "description2");

			System.out.println("=== BEFORE ===");
			entityManager.persist(member1);
			entityManager.persist(member2);

			for(int i = 0; i < 50; i++) {
				entityManager.persist(new Member("test", 23, RoleType.USER, "test"));
			}
			System.out.println("=== AFTER ===");

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
