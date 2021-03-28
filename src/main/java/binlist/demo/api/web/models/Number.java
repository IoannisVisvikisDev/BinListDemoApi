package binlist.demo.api.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Number {

    @Override
    public String toString() {
        return "Number{}";
    }
}
