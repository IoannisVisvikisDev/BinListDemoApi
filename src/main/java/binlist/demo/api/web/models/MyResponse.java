package binlist.demo.api.web.models;

public class MyResponse {

    private String country;
    private double cost;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public MyResponse(String country, double cost) {
        this.country = country;
        this.cost = cost;
    }

    public MyResponse() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyResponse that = (MyResponse) o;

        if (that.cost != this.cost) return false;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return country.hashCode();
    }

    @Override
    public String toString() {
        return "MyResponse{" +
                "country='" + country + '\'' +
                ", cost=" + cost +
                '}';
    }
}
