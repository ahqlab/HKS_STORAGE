package com.hankisul.storage.domain;

import lombok.Data;

@Data
public class OverlabTerm implements Domain{
	
	
	public OverlabTerm() {
	}
	
	public OverlabTerm(int info, int warning, int error, int pIdx) {
		this.info = info;
		this.warning = warning;
		this.error = error;
		this.pIdx = pIdx;
	}

	private int id;
	
	private int info;
	
	private int warning;
	
	private int error;
	
	private int pIdx;
	
	
}
