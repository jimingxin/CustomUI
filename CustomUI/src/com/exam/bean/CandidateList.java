package com.exam.bean;

import java.util.List;

public class CandidateList {
	int status;
	String message;
	List<Data> data;
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<Data> getData() {
		return data;
	}

	public static class Data{

	int Id;
	String UserName;
	String TrueName;
	String Gender;
	int Age;
	String Department;
	String Position;
	String Avatar;
	String Description;
	String Achievement;
	boolean HasSeleted;

	int Role;
	public void setHasSeleted(boolean hasSeleted) {
		HasSeleted = hasSeleted;
	}
	public boolean isHasSeleted() {
		return HasSeleted;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public void setTrueName(String trueName) {
		TrueName = trueName;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public void setAge(int age) {
		Age = age;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setAchievement(String achievement) {
		Achievement = achievement;
	}
	public void setRole(int role) {
		Role = role;
	}
	public int getId() {
		return Id;
	}
	public String getUserName() {
		return UserName;
	}
	public String getTrueName() {
		return TrueName;
	}
	public String getGender() {
		return Gender;
	}
	public int getAge() {
		return Age;
	}
	public String getDepartment() {
		return Department;
	}
	public String getPosition() {
		return Position;
	}
	public String getAvatar() {
		return Avatar;
	}
	public String getDescription() {
		return Description;
	}
	public String getAchievement() {
		return Achievement;
	}
	public int getRole() {
		return Role;
	}

 @Override
	public String toString() {
		
	 	return "Data[Id="+Id+","+"HasSeleted="+","+HasSeleted+"]";
 	}
 
	}
}
