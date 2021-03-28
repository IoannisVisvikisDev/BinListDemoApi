package binlist.demo.api;


import binlist.demo.api.data.controllers.CardIssuingCountryController;
import binlist.demo.api.data.entities.CardIssuingCountry;
import binlist.demo.api.data.services.IssuingCountriesService;
import binlist.demo.api.security.constants.SecurityEnum;
import binlist.demo.api.security.entities.RegisteredUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardIssuingCountryController.class)
@RunWith(SpringRunner.class)
public class CardIssuingCountryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssuingCountriesService issuingCountriesService;


    private String jwtToken;

    @Before
    public void setJwtToken(){
        jwtToken = produceJwtToken(new RegisteredUser("some_email@gmail.com", "SomePassword12#$"));
    }


    @Test
    public void testGetAllCountries() throws Exception {
        List<CardIssuingCountry> listCountries = Arrays.asList(new CardIssuingCountry[]{new CardIssuingCountry("GR", "15.00"),
                new CardIssuingCountry("US", "5.00")});

        Mockito.when(issuingCountriesService.getCardIssuingCountries()).thenReturn(listCountries);

        mockMvc.perform(MockMvcRequestBuilders.get("/countries").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUS() throws Exception {

        Mockito.when(issuingCountriesService.getCardIssuingCountry("US")).thenReturn(new CardIssuingCountry("US", "5.00"));

        mockMvc.perform(MockMvcRequestBuilders.get("/countries/US").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isoCode").value("US"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clearingCostUSD").value("5.00"));
    }


    @Test
    public void testAddCanada() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/countries").header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .content(new ObjectMapper().writeValueAsString(new CardIssuingCountry("CA", "10.00")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }



    private String produceJwtToken(RegisteredUser user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SecurityEnum.API_SECRET_KEY.getEnumValue())
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Long.parseLong(SecurityEnum.TOKEN_VALIDITY_PERIOD_MILLISECONDS_STRING.getEnumValue())))
                .claim(SecurityEnum.CLAIM_EMAIL.getEnumValue(), user.getEmail())
                .claim(SecurityEnum.CLAIM_USERNAME.getEnumValue(), user.getUsername())
                .compact();
        return token;
    }

}


