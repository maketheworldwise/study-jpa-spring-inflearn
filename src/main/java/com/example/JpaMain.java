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
			Team team = new Team();
			team.setName("team1");
			entityManager.persist(team);

			Member member = new Member();
			member.setName("member1");
			member.setTeam(team);
			entityManager.persist(member);

		 	entityManager.flush();
			entityManager.clear();

			Member findMember = entityManager.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();

			System.out.println("findMember class = " + findMember.getClass());
			System.out.println("findTeam class = " + findTeam.getClass());

			// 사용하는 시점에 초기화
			System.out.println("=== BEFORE ===");
			System.out.println(findTeam.getName());
			System.out.println("=== AFTER ===");

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
