package br.com.eiasiscon.security.auth.jwt.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.security.auth.jwt.extractor.TokenExtractor;
import br.com.eiasiscon.security.auth.jwt.verifier.TokenVerifier;
import br.com.eiasiscon.security.config.JwtSettings;
import br.com.eiasiscon.security.config.WebSecurityConfig;
import br.com.eiasiscon.security.exceptions.InvalidJwtToken;
import br.com.eiasiscon.security.model.UserContext;
import br.com.eiasiscon.security.model.token.JwtToken;
import br.com.eiasiscon.security.model.token.JwtTokenFactory;
import br.com.eiasiscon.security.model.token.RawAccessJwtToken;
import br.com.eiasiscon.security.model.token.RefreshToken;
import br.com.eiasiscon.security.user.entity.Role;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.service.UserService;

@CrossOrigin
@RestController
public class RefreshTokenEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;
    
    @RequestMapping(value="/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getByUsername(subject);
        if(user == null) {
        	throw new UsernameNotFoundException("Usuario não encontrado: " + subject);
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

        return tokenFactory.createAccessJwtToken(userContext);
    }
}