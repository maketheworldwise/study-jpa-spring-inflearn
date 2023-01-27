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
			Member member = new Member();
			member.setName("member1");
			entityManager.persist(member);

		 	entityManager.flush();
			entityManager.clear();

			Member findMember1 = entityManager.find(Member.class, member.getId());
			System.out.println("findMember1 : " + findMember1.getClass()); // Member

			Member findMember2 = entityManager.getReference(Member.class, member.getId());
			System.out.println("findMember2 : " + findMember2.getClass()); // 프록시 객체가 아닌 Member 출력

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
