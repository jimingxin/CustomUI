package com.exam.bean;

import java.io.Serializable;

import java.sql.Date;
import java.util.List;

import android.text.method.DateTimeKeyListener;

public class CurrentVote {
	int status;
	String message;
	List<Data> data;
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public List<Data> getData() {
		return data;
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

	

	public static class Data implements Serializable{
		int Id;
		String Subject;
		String Description;
		int MustSelect;
		int CreateUserId;
		String  CreateDate;
		String Deadline;
		boolean HasVoted;
		public int getId() {
			return Id;
		}
		public String getSubject() {
			return Subject;
		}
		public String getDescription() {
			return Description;
		}
		public int getMustSelect() {
			return MustSelect;
		}
		public int getCreateUserId() {
			return CreateUserId;
		}
		public String  getCreateDate() {
			return CreateDate;
		}
		public String getDeadline() {
			return Deadline;
		}
		public boolean isHasVoted() {
			return HasVoted;
		}
		public void setId(int id) {

			Id = id;
		}
		public void setSubject(String subject) {
			Subject = subject;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public void setMustSelect(int mustSelect) {
			MustSelect = mustSelect;
		}
		public void setCreateUserId(int createUserId) {
			CreateUserId = createUserId;
		}
		public void setCreateDate(String createDate) {
			CreateDate = createDate;
		}
		public void setDeadline(String deadline) {
			Deadline = deadline;
		}
		public void setHasVoted(boolean hasVoted) {
			HasVoted = hasVoted;
	
		}
		@Override
		public String toString() {
			
			return "Data[Id="+Id+","+"Description="+Description+",createtime="+Deadline+"]";
		}
	}


}
