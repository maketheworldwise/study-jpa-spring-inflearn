package com.example;

import java.time.LocalDateTime;

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
			Member member = new Member();
			member.setName("member1");
			entityManager.persist(member);

		 	entityManager.flush();
			entityManager.clear();

			Member findMember1 = entityManager.getReference(Member.class, member.getId());
			System.out.println("findMember1 : " + findMember1.getClass());

			System.out.println("Before isLoaded = " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember1));
			System.out.println(findMember1.getName()); // 프록시 초기화
			System.out.println("After isLoaded = " + entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember1));

			Hibernate.initialize(findMember1); // 강제 초기화

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
