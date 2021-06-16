package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class CustomerParam extends DomainParam {
	
	private String pIdx;
	
	private String searchField;
	
	private String searchWord;
	
}
