package com.hankisul.storage.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class StoreageParam extends DomainParam {
	
	private String pIdx;
	
	private String searchField;
	
	private String searchWord;
	
}
