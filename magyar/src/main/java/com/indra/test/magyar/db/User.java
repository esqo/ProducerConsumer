package com.indra.test.magyar.db;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "susers")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "user_id", nullable=false)
	private int id;

	@Column(name = "user_guid")
	private String guid;

	@Column(name = "user_name")
	private String name;

	public User() {
		super();
	}
	
	public User(String guid, String name) {
		super();
		this.guid = guid;
		this.name = name;
	}

	public User(int id, String guid, String name) {
		super();
		this.id = id;
		this.guid = guid;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [ID=" + id + ", GUID=" + guid + ", name=" + name + "]";
	}

}
