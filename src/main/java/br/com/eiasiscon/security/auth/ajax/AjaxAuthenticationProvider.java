package br.com.eiasiscon.security.auth.ajax;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.eiasiscon.security.model.UserContext;
import br.com.eiasiscon.security.user.entity.Role;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.service.UserService;

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private final BCryptPasswordEncoder encoder;
    private final UserService userService;

    @Autowired
    public AjaxAuthenticationProvider(final UserService userService, final BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "Nenhum dado de autenticão fornecido");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.getByUsername(username);
        if(user == null) {
        	throw new UsernameNotFoundException("Usuario não encontrado: " + username);
        }
        
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Falha na autenticão. Usuário ou senha inválidos.");
        }

        if (user.getRoles() == null) throw new InsufficientAuthenticationException("O usuário não tem papéis atribuídos");
        
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	for (Role role: user.getRoles()) {
    		// authorities.add(new SimpleGrantedAuthority(role.getName()));
    		authorities.addAll(role.getPrivileges()
    				.stream()
    				.map(p -> new SimpleGrantedAuthority(p.getName()))
    				.collect(Collectors.toList()));
    	}
        
        UserContext userContext = UserContext.create(user.getEmail(), authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
