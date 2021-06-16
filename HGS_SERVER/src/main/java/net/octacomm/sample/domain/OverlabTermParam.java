package net.octacomm.sample.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class OverlabTermParam extends DomainParam {
	
	private String searchField;
	
	private String searchWord;
	
	private int pIdx;
	
}
