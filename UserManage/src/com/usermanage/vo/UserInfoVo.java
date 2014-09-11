package com.usermanage.vo;

import java.sql.Date;


public class UserInfoVo {
	private int id;
	private String userName;
	private String serialnum;
	private String pass;
	private String Agent;
	private String address;
	private String phone;
	private Date start_date;
	private Date end_date;
	private Date new_date;
	private int res_date;
	private String Access;
	/**
	 * @return the res_date
	 */
	public int getRes_date() {
		return res_date;
	}
	/**
	 * @param res_date the res_date to set
	 */
	public void setRes_date(int res_date) {
		this.res_date = res_date;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the agent
	 */
	public String getAgent() {
		return Agent;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setAccess(String Access) {
		this.Access = Access;
	}
	/**
	 * @return the agent
	 */
	public String getAccess() {
		return Access;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setAgent(String Agent) {
		this.Agent = Agent;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the start_date
	 */
	public Date getStart_date() {
		return start_date;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	/**
	 * @return the start_date
	 */
	public Date getNew_date() {
		return new_date;
	}
	/**
	 * @param start_date the start_date to set
	 */
	public void setNew_date(Date new_date) {
		this.new_date = new_date;
	}
	/**
	 * @return the end_date
	 */
	public Date getEnd_date() {
		return end_date;
	}
	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	/**
	 * @return the serialnum
	 */
	public String getSerialnum() {
		return serialnum;
	}
	/**
	 * @param serialnum the serialnum to set
	 */
	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}


}
