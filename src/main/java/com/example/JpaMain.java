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
			Member member1 = new Member();
			member1.setName("member1");
			entityManager.persist(member1);

			Member member2 = new Member();
			member2.setName("member2");
			entityManager.persist(member2);

		 	entityManager.flush();
			entityManager.clear();

			Member findMember1 = entityManager.find(Member.class, member1.getId());
			Member findMember2 = entityManager.getReference(Member.class, member2.getId());

			logic(findMember1, findMember2);

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}

	private static void logic(Member m1, Member m2) {
		// 여기 메서드에 넘어오는 값이 프록시 객체인지 실제 엔티티 객체인지 모름
		System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));
		// 따라서 instanceof를 이용하여 비교 필요
		System.out.println("m1 instanceof Member : " + (m1 instanceof Member));
		System.out.println("m2 instanceof Member : " + (m2 instanceof Member));
	}
}
