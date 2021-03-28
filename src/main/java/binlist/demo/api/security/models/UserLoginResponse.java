package binlist.demo.api.security.models;

public class UserLoginResponse {
	
	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public UserLoginResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public UserLoginResponse() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jwtToken == null) ? 0 : jwtToken.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLoginResponse other = (UserLoginResponse) obj;
		if (jwtToken == null) {
			if (other.jwtToken != null)
				return false;
		} else if (!jwtToken.equals(other.jwtToken))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserLoginResponse{" +
				"jwtToken='" + jwtToken + '\'' +
				'}';
	}
}
