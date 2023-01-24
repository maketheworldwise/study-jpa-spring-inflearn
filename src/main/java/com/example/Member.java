package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID")
	private Long id;

	@Column(name = "USERNAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
	private Team team;

	// @ManyToOne
	// @JoinColumn(name = "TEAM_ID")
	// private Team team;

	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public Team getTeam() {
	// 	return team;
	// }
	//
	// public void chageTeam(Team team) {
	// 	this.team = team;
	// 	team.getMembers().add(this);
	// }
}
