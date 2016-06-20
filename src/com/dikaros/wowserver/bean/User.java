package com.dikaros.wowserver.bean;

import java.io.Serializable;

import com.google.gson.Gson;

public class User implements Serializable {


	public User(String phone, String password) {
		this.phone = phone;
		this.password = password;
		name = phone;
		rank=0;
	}

	public User() {

	}
	
	

	private long id;

	private String name;

	private String phone;

	private String password;
	
	private String gender;
	
	private String birthday;
	
	private String personalMessage;
	
	private int rank=0;


	/**
	 * @return id
	 */

	public long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone
				+ ", password=" + password + "]";
	}

	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this, getClass());
		
	}

	/**
	 * @return gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender 要设置的 gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday 要设置的 birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return personalMessage
	 */
	public String getPersonalMessage() {
		return personalMessage;
	}

	/**
	 * @param personalMessage 要设置的 personalMessage
	 */
	public void setPersonalMessage(String personalMessage) {
		this.personalMessage = personalMessage;
	}

	/**
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank 要设置的 rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

}
