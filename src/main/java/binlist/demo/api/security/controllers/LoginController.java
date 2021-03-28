package binlist.demo.api.security.controllers;


import java.util.Date;

import javax.validation.Valid;

import binlist.demo.api.security.sevices.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import binlist.demo.api.security.constants.SecurityEnum;
import binlist.demo.api.security.entities.RegisteredUser;
import binlist.demo.api.security.models.UserLoginRequest;
import binlist.demo.api.security.models.UserLoginResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping
	public UserLoginResponse loginUser(@Valid @RequestBody UserLoginRequest loginRequest) {
		String userEmail = loginRequest.getEmail();
		RegisteredUser registeredUser = securityService.getRegisteredUser(userEmail, loginRequest.getPassword());
		return new UserLoginResponse(produceJwtToken(registeredUser));
	}

	
	public String produceJwtToken(RegisteredUser user) {
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
