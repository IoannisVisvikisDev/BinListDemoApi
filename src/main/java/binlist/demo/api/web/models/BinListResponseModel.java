package binlist.demo.api.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class BinListResponseModel {

    private Number number;
    private String scheme;
    private String type;
    private String brand;
    private Country country;
    private Bank bank;


    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }


    public BinListResponseModel(Number number, String scheme, String type, String brand, Country country, Bank bank) {
        this.number = number;
        this.scheme = scheme;
        this.type = type;
        this.brand = brand;
        this.country = country;
        this.bank = bank;
    }

    public BinListResponseModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinListResponseModel that = (BinListResponseModel) o;

        if (!number.equals(that.number)) return false;
        if (!scheme.equals(that.scheme)) return false;
        if (!type.equals(that.type)) return false;
        if (!brand.equals(that.brand)) return false;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        int result = scheme.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + brand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BinListResponseModel{" +
                "number=" + number +
                ", scheme='" + scheme + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", country=" + country +
                ", bank=" + bank +
                '}';
    }
}
