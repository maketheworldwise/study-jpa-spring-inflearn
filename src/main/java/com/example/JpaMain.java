package com.example;

import java.util.List;

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

		// DB 데이터 CRUD 로직
		try {
			// [INSERT]
			//
			// Member member = new Member();
			// member.setId(1L);
			// member.setName("Hello");
			//
			// entityManager.persist(member);

			// [SELECT]
			//
			Member findMember = entityManager.find(Member.class, 1L);
			System.out.println("id: " + findMember.getId());
			System.out.println("name: " + findMember.getName()); // Hello

			// [DELETE]
			//
			// entityManager.remove(findMember);

			// [UPDATE]
			//
			findMember.setName("World");
			System.out.println("id: " + findMember.getId());
			System.out.println("name: " + findMember.getName()); // World

			// [JPQL]
			// 테이블을 대상이 아닌 엔티티 객체를 대상으로 쿼리를 구성
			List<Member> result = entityManager.createQuery("SELECT m from Member as m", Member.class)
				.setFirstResult(1) // Paging 1 ~ 10 (limit, offset)
				.setMaxResults(10)
				.getResultList();

			for(Member member : result) {
				System.out.println("result: " + member.getName());
			}

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
