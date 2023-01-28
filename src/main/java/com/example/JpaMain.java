package com.example;

import java.time.LocalDateTime;
import java.util.List;

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

			// Member findMember = entityManager.find(Member.class, member.getId());

			List<Member> memberList = entityManager.createQuery("select m from Member m join fetch m.team", Member.class)
				.getResultList();

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
