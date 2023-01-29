package com.example;

import java.time.LocalDateTime;

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
			Address address = new Address("city", "street", "zipcode");
			Member member1 = new Member();
			member1.setHomeAddress(address);
			entityManager.persist(member1);

			Address copyAddress = new Address("city", "street", "zipcode");
			Member member2 = new Member();
			member2.setHomeAddress(copyAddress);
			entityManager.persist(member2);

			// Setter 사용 불가!!
			// member1.getHomeAddress().setCity("newCity");

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
