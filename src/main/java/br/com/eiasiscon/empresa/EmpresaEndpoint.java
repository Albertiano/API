package br.com.eiasiscon.empresa;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.base.BaseEndpoint;
import br.com.eiasiscon.security.auth.JwtAuthenticationToken;
import br.com.eiasiscon.security.model.UserContext;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/empresa")
public class EmpresaEndpoint extends BaseEndpoint<Empresa, Long> {
	
	@Autowired
	private EmpresaService service;
	@Autowired
	private UserService userService;
		
	@GetMapping("/from-user")
	public Page<Empresa> procurar(JwtAuthenticationToken token, @RequestParam String filter, Pageable pageable) {
		UserContext principal = (UserContext) token.getPrincipal();
		User user = userService.getByUsername(principal.getUsername());
		
		Page<Empresa> registros =  service.find(filter, user.getId(), pageable);
		
		return registros;
	}
	
	@GetMapping("/crts")
	public List<CRT> getCRTs() {
		List<CRT> ufs = Arrays.asList(CRT.values());
		return ufs;
	}
}