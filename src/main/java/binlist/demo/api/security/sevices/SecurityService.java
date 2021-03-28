package binlist.demo.api.security.sevices;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import binlist.demo.api.security.entities.RegisteredUser;
import binlist.demo.api.security.exceptions.MyCustomUnauthorizedException;
import binlist.demo.api.security.repositories.RegisteredUsersRepository;


@Service
public class SecurityService {
	
	@Autowired
	RegisteredUsersRepository registeredUsersRepository;
	
	public RegisteredUser getRegisteredUser(String userEmailString, String userUnhashedPassword) throws MyCustomUnauthorizedException{
		RegisteredUser foundUser = registeredUsersRepository.findById(userEmailString).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if(!BCrypt.checkpw(userUnhashedPassword, foundUser.getPassword())) throw new MyCustomUnauthorizedException("Incorrect Password");
		return foundUser;
	}

	@PostConstruct
	public void onPostCreation() {
		//enter an initial value to allow registration for demonstration purposes	
		registeredUsersRepository.save(new RegisteredUser("some_email@gmail.com", "SomeUserName", BCrypt.hashpw("SomePassword12#$", BCrypt.gensalt(10))));
	}
	
	
}
