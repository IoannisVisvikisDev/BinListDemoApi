package binlist.demo.api.web.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class MyRequestBody {

    @JsonProperty("card_number")
    @NotBlank
    private String cardNumber;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public MyRequestBody(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public MyRequestBody() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyRequestBody that = (MyRequestBody) o;

        return cardNumber.equals(that.cardNumber);
    }

    @Override
    public int hashCode() {
        return cardNumber.hashCode();
    }

    @Override
    public String toString() {
        return "MyRequestBody{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
