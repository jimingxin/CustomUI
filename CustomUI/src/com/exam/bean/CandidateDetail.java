package com.exam.bean;

import com.exam.bean.CandidateDetail.Data;

public class CandidateDetail {
	int status;
	String Message;
	Data data;
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return Message;
	}
	public Data getData() {
		return data;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public static class Data{
		int Id;
		String UserName;
		String Password;
		String TrueName;
		int Gender;
		int Age;
		String Department;
		String Position;
		String Avatar;
		String Description;
		String Achievement;
		int Role;

		public void setId(int id) {
			Id = id;
		}
		public void setUserName(String userName) {
			UserName = userName;
		}
		public void setPassword(String password) {
			Password = password;
		}
		public void setTrueName(String trueName) {
			TrueName = trueName;
		}
		public void setGender(int gender) {
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
		public int  getId() {
			return Id;
		}
		public String getUserName() {
			return UserName;
		}
		public String getPassword() {
			return Password;
		}
		public String getTrueName() {
			return TrueName;
		}
		public int getGender() {
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
			// TODO Auto-generated method stub
			return "Data[Gender="+Gender+",Password="+Password+"]";
		}
	}
}