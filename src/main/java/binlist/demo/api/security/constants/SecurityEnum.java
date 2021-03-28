package binlist.demo.api.security.constants;

public enum SecurityEnum {
	
	API_SECRET_KEY("BinlistAppSecretKey"),
	TOKEN_VALIDITY_PERIOD_MILLISECONDS_STRING("900000"),
	CLAIM_EMAIL("user_email"),
	CLAIM_USERNAME("user_name"); //Fifteen minutes
	
	private String enumValue;
	
	
	public String getEnumValue() {
		return enumValue;
	}

	private SecurityEnum(String value) {
		this.enumValue = value;
	}
	
}
