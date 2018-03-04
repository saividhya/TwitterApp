package com.twitter.dto;

import com.twitter.entity.User;

public class HopsDTO {
	User currentUser;
	User targetUser;
	int noOfHops;
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	public User getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(User targetUser) {
		this.targetUser = targetUser;
	}
	public int getNoOfHops() {
		return noOfHops;
	}
	public void setNoOfHops(int noOfHops) {
		this.noOfHops = noOfHops;
	}
	
}
