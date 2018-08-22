package com.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MediumRoom extends Room{
	private static final long serialVersionUID = 1L;
	String git;
	String cloneStatus;
	String pmdStatus;
	String makeQuestionStatus;
    
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="room",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<PMDErrors> pmdErrors = new ArrayList<>();
	
	public String getGit(){
		return git;
	}

	public void setGit(String git) {
		this.git = git;
	}

	public String getCloneStatus() {
		return cloneStatus;
	}

	public void setCloneStatus(String cloneStatus) {
		this.cloneStatus = cloneStatus;
	}

	public String getPmdStatus() {
		return pmdStatus;
	}

	public void setPmdStatus(String pmdStatus) {
		this.pmdStatus = pmdStatus;
	}

	public String getMakeQuestionStatus() {
		return makeQuestionStatus;
	}

	public void setMakeQuestionStatus(String makeQuestionStatus) {
		this.makeQuestionStatus = makeQuestionStatus;
	}

	public List<PMDErrors> getPmdErrors() {
		return pmdErrors;
	}

	public void setPmdErrors(List<PMDErrors> pmdErrors) {
		this.pmdErrors = pmdErrors;
	}	
	
	
	
	
	
     
	
}
