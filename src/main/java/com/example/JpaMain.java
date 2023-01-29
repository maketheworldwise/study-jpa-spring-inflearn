package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
			Member member = new Member();
			member.setName("member1");
			member.setHomeAddress(new Address("homeCity", "street", "zipcode"));

			member.getFavoriteFoods().add("chicken");
			member.getFavoriteFoods().add("hamburger");
			member.getFavoriteFoods().add("pizza");

			member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
			member.getAddressHistory().add(new Address("old2", "street", "zipcode"));

			entityManager.persist(member);
			
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
