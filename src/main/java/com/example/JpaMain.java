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
			member.setName("member");
			entityManager.persist(member);

		 	entityManager.flush();
			entityManager.clear();

			// Member findMember = entityManager.find(Member.class, member.getId());
			// System.out.println("id = " + findMember.getId());
			// System.out.println("name = " + findMember.getName());

			Member findMember = entityManager.getReference(Member.class, member.getId());
			System.out.println("findMember = " + findMember.getClass());
			System.out.println("id = " + findMember.getId()); // DB에서 안가져와도 되는 내용
			System.out.println("name = " + findMember.getName()); // DB에서 가져와야하는 내용

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
