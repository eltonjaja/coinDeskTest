package com.project.MyProject.util;


public class parseCoinCnameUtil {

	public enum coinCode {
		GBP("英鎊"), 
		EUR("歐元"), 
		USD("美元"),
		TWD("台幣");

		private final String labelText;

		private coinCode(String code) {
			this.labelText = code;
		}

		public String Cname() {
			return labelText;
		}
	}
	
	public static String getStatusText(String code) {
		for (coinCode coin : coinCode.values()) {
			if (coin.toString().equals(code))
				return coin.Cname();
		}
		
//		throw new RuntimeException("Can't not find : " + code);
		
		return "";
	}
}
