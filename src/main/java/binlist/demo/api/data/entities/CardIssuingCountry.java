package binlist.demo.api.data.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@Entity
@Table(name="card_issuing_country")
public class CardIssuingCountry {


    @Id
    @NotEmpty
    @Column(name = "iso_code")
    private String isoCode;

    @Column(name = "clearing_cost_usd")
    @NotEmpty
    private String clearingCostUSD;


    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getClearingCostUSD() {
        return clearingCostUSD;
    }

    public void setClearingCostUSD(String clearingCostUSD) {
        this.clearingCostUSD = clearingCostUSD;
    }

    public CardIssuingCountry(String isoCode, String clearingCostUSD) {
        this.isoCode = isoCode;
        this.clearingCostUSD = clearingCostUSD;
    }

    public CardIssuingCountry(){ }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardIssuingCountry)) return false;

        CardIssuingCountry that = (CardIssuingCountry) o;

        return isoCode.equals(that.isoCode);
    }

    @Override
    public int hashCode() {
        return isoCode.hashCode();
    }

    @Override
    public String toString() {
        return "CardIssuingCountry{" +
                "isoCode='" + isoCode + '\'' +
                ", clearingCostUSD=" + clearingCostUSD +
                '}';
    }
}
