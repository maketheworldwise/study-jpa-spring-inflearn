package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
			member.setHomeAddress(new Address("homeCity", "street", "zipcode"));

			member.getFavoriteFoods().add("chicken");
			member.getFavoriteFoods().add("hamburger");
			member.getFavoriteFoods().add("pizza");

			member.getAddressHistory().add(new Address("old1", "street", "zipcode"));
			member.getAddressHistory().add(new Address("old2", "street", "zipcode"));

			entityManager.persist(member);

			entityManager.flush();
			entityManager.clear();

			System.out.println("=== READ MEMBER ===");
			Member findMember = entityManager.find(Member.class, member.getId());

			// System.out.println("=== UPDATE MEMBER ===");
			// // homeAddress : homeCity -> newCity
			// Address oldHomeAddress = findMember.getHomeAddress();
			// findMember.setHomeAddress(new Address("newCity", oldHomeAddress.getStreet(), oldHomeAddress.getZipcode()));

			// System.out.println("=== UPDATE FAVORITE FOOD ===");
			// // favoriteFood : chicken -> rice (기본 값 타입이기에 값만 변경해주면 됨)
			// findMember.getFavoriteFoods().remove("chicken");
			// findMember.getFavoriteFoods().add("rice");

			System.out.println("=== UPDATE ADDRESS HISTORY ===");
			findMember.getAddressHistory().remove(new Address("old1", "street", "zipcode"));
			findMember.getAddressHistory().add(new Address("young", "street", "zipcode"));

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
