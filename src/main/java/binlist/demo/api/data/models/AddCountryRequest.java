package binlist.demo.api.data.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import binlist.demo.api.data.entities.CardIssuingCountry;

public class AddCountryRequest {
	
	@NotNull
	private CardIssuingCountry country;


	public CardIssuingCountry getCountry() {
		return country;
	}

	public void setCountry(CardIssuingCountry country) {
		this.country = country;
	}

	public AddCountryRequest() {
	}

	public AddCountryRequest(@NotEmpty CardIssuingCountry country) {
		this.country = country;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		AddCountryRequest other = (AddCountryRequest) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AddCountryRequest {country=" + country + "}";
	}
	
	
	
}
