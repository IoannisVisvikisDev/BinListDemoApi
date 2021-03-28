package binlist.demo.api.security.repositories;

import org.springframework.data.repository.CrudRepository;

import binlist.demo.api.security.entities.RegisteredUser;


public interface RegisteredUsersRepository extends CrudRepository<RegisteredUser, String>{

}
