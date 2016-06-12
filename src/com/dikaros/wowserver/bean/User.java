package com.dikaros.wowserver.bean;

import java.io.Serializable;

import com.google.gson.Gson;

public class User implements Serializable {


	public User(String phone, String password) {
		this.phone = phone;
		this.password = password;
		name = phone;
	}

	public User() {

	}
	
	

	private long id;

	private String name;

	private String phone;

	private String password;


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

}
