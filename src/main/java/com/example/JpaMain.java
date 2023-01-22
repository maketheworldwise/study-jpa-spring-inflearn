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
			// 저장
			Team teamA = new Team();
			teamA.setName("TeamA");
			entityManager.persist(teamA);

			Team teamB = new Team();
			teamB.setName("TeamB");
			entityManager.persist(teamB);

			Member member = new Member();
			member.setName("Member1");
			member.setTeam(teamA);
			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

			// 조회
			Member findMember = entityManager.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();

			// 수정
			Team newTeam = entityManager.find(Team.class, 2L);
			findMember.setTeam(newTeam);

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
