package com.review.payloads;



import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Authority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	
	public Authority(String authority) {
		this.authority =  authority;
	}

	


}
