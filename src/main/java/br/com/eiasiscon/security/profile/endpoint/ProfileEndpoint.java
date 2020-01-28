package br.com.eiasiscon.security.profile.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.security.auth.JwtAuthenticationToken;
import br.com.eiasiscon.security.model.UserContext;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.repository.UserRepository;

@CrossOrigin
@RestController
public class ProfileEndpoint {
	@Autowired
	UserRepository userRepository;
	
    @RequestMapping(value="profile", method=RequestMethod.GET)
    public Optional<User> get(JwtAuthenticationToken token) {    
    	UserContext user = (UserContext) token.getPrincipal();
        return userRepository.findByEmail(user.getUsername());
    }
}
