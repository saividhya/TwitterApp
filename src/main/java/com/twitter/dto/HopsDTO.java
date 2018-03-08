package com.twitter.dto;

public class HopsDTO {
	UserDTO currentUser;
	UserDTO targetUser;
	int noOfHops;
	public UserDTO getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(UserDTO currentUser) {
		this.currentUser = currentUser;
	}
	public UserDTO getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(UserDTO targetUser) {
		this.targetUser = targetUser;
	}
	public int getNoOfHops() {
		return noOfHops;
	}
	public void setNoOfHops(int noOfHops) {
		this.noOfHops = noOfHops;
	}
	
}
