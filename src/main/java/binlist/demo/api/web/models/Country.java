package binlist.demo.api.web.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Country {

    private String numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private String latitude;
    private String longitude;

    public String getNumeric() {
        return numeric;
    }

    public void setNumeric(String numeric) {
        this.numeric = numeric;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Country(String numeric, String alpha2, String name,
                   String emoji, String currency, String latitude,
                   String longitude) {

        this.numeric = numeric;
        this.alpha2 = alpha2;
        this.name = name;
        this.emoji = emoji;
        this.currency = currency;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Country() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (!alpha2.equals(country.alpha2)) return false;
        if (currency != null ? !currency.equals(country.currency) : country.currency != null) return false;
        if (latitude != null ? !latitude.equals(country.latitude) : country.latitude != null) return false;
        return longitude != null ? longitude.equals(country.longitude) : country.longitude == null;
    }

    @Override
    public int hashCode() {
        int result = alpha2.hashCode();
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "numeric='" + numeric + '\'' +
                ", alpha2='" + alpha2 + '\'' +
                ", name='" + name + '\'' +
                ", emoji='" + emoji + '\'' +
                ", currency='" + currency + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
