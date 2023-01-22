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

		try {
			// 저장
			Team team = new Team();
			team.setName("TeamA");
			entityManager.persist(team);

			Member member = new Member();
			member.setName("Member1");
			member.chageTeam(team);
			entityManager.persist(member);

			// 주인이 아닌 곳에도 값 삽입
			// team.getMembers().add(member);

			// entityManager.flush();
			// entityManager.clear();

			// 조회
			Team findTeam = entityManager.find(Team.class, team.getId());
			List<Member> members = findTeam.getMembers();
			for(Member m : members) {
				System.out.println("member = " + m.getName());
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
