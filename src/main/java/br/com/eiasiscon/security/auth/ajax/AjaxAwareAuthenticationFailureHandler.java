package br.com.eiasiscon.security.auth.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eiasiscon.security.common.ErrorCode;
import br.com.eiasiscon.security.common.ErrorResponse;
import br.com.eiasiscon.security.exceptions.AuthMethodNotSupportedException;
import br.com.eiasiscon.security.exceptions.JwtExpiredTokenException;

@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;
    
    @Autowired
    public AjaxAwareAuthenticationFailureHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }	
    
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		if (e instanceof BadCredentialsException) {
			mapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		} else if (e instanceof JwtExpiredTokenException) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Token já expirou", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.FORBIDDEN));
		} else if (e instanceof AuthMethodNotSupportedException) {
		    mapper.writeValue(response.getWriter(), ErrorResponse.of("Método não suportado: " + e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
		}
		mapper.writeValue(response.getWriter(), ErrorResponse.of("Falha na authentição: "+e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
	}
}
