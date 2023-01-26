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
			// ITEM, MOVIE 테이블에 데이터 저장
			Movie movie = new Movie();
			movie.setDirector("A");
			movie.setActor("B");
			movie.setName("바람과함께사라지다");
			movie.setPrice(10000);

			entityManager.persist(movie);

			entityManager.flush();
			entityManager.clear();

			// 부모 클래스 타입으로 조회
			Item findItem = entityManager.find(Item.class, movie.getId());
			System.out.println("findItem = " + findItem);

			entityTransaction.commit();
		} catch (Exception e) {
			entityTransaction.rollback();
		} finally {
			entityManager.close();
		}

		entityManagerFactory.close();
	}
}
