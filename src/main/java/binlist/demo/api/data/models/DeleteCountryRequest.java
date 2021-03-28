package binlist.demo.api.data.models;

import javax.validation.constraints.NotBlank;

public class DeleteCountryRequest {

    @NotBlank
    private String isoCode;

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public DeleteCountryRequest() {
		super();
	}

	public DeleteCountryRequest(@NotBlank String isoCode) {
		super();
		this.isoCode = isoCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isoCode == null) ? 0 : isoCode.hashCode());
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
		DeleteCountryRequest other = (DeleteCountryRequest) obj;
		if (isoCode == null) {
			if (other.isoCode != null)
				return false;
		} else if (!isoCode.equals(other.isoCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeleteCountryRequest {isoCode=" + isoCode + "}";
	}

	
    
    
}
