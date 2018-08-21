package com.demo.domain;

import javax.persistence.Entity;

@Entity
public class MediumRoom extends Room{
	private static final long serialVersionUID = 1L;
	String git;

	public String getGit() {
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}	
	
}
