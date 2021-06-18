package com.hankisul.sync;

import lombok.Data;

@Data
public class ServiceOperationCount {

	private static int num = 0;

	public static ServiceOperationCount getInstance() {
		 num += 1;
		return Holder.singleton;
	}

	private static class Holder {
		
		static final ServiceOperationCount singleton = new ServiceOperationCount();
	}

	private ServiceOperationCount() {

	}
	
	public static int getNum() {
		return num;
	}
}
