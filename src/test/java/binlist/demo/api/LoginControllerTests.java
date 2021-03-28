package binlist.demo.api;


import binlist.demo.api.security.controllers.LoginController;
import binlist.demo.api.security.entities.RegisteredUser;
import binlist.demo.api.security.models.UserLoginRequest;
import binlist.demo.api.security.sevices.SecurityService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@RunWith(SpringRunner.class)
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityService securityService;


    @Test
    public void unsupportedMethodAtLoginPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(405));
    }

    @Test
    public void badRequestToLoginPage() throws Exception{
        JSONObject badJSON = new JSONObject();
        badJSON.put("email", "some_email@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content(badJSON.toString().getBytes(StandardCharsets.UTF_8))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void successfulRequestToLoginPage() throws Exception{
        Mockito.when(securityService.getRegisteredUser("some_email@gmail.com", "SomePassword12#$")).thenReturn(new RegisteredUser("some_email@gmail.com", "SomePassword12#$"));

        UserLoginRequest goodRequest = new UserLoginRequest("some_email@gmail.com", "SomePassword12#$");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .content(new ObjectMapper().writeValueAsString(goodRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
