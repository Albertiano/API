package br.com.eiasiscon.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eiasiscon.empresa.CRT;
import br.com.eiasiscon.empresa.Empresa;
import br.com.eiasiscon.empresa.EmpresaRepository;
import br.com.eiasiscon.municipio.Municipio;
import br.com.eiasiscon.municipio.MunicipioRepository;
import br.com.eiasiscon.municipio.UF;
import br.com.eiasiscon.pais.Pais;
import br.com.eiasiscon.pais.PaisRepository;
import br.com.eiasiscon.security.user.entity.Privilege;
import br.com.eiasiscon.security.user.entity.Role;
import br.com.eiasiscon.security.user.entity.User;
import br.com.eiasiscon.security.user.repository.PrivilegeRepository;
import br.com.eiasiscon.security.user.repository.RoleRepository;
import br.com.eiasiscon.security.user.repository.UserRepository;
import br.com.eiasiscon.util.MunicipioXML;
import br.com.eiasiscon.util.PaisXML;

@CrossOrigin
@RestController
@RequestMapping("/init")
public class PopularDadosEndpoint {
	
	@Autowired
	private EmpresaRepository empresaRepo;
	@Autowired
	private PaisRepository paisRepo;
	@Autowired
	private MunicipioRepository municipioRepo;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private PrivilegeRepository privilegeRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<String> atualizar() {
		StringBuilder msg = new StringBuilder();
		
		boolean gerarMun = municipioRepo.count() == 0;
		boolean gerarPais = paisRepo.count() == 0;
		boolean gerarUser = userRepository.count() == 0;
		
		if(gerarPais) {
			List<Pais> paises = new PaisXML().realizaLeituraXML();
			for(Pais p : paises){
				this.paisRepo.save(p);
				//System.out.println(p.getxPais());
			}
			if(paises.size() > 0) {
				msg.append(paises.size() + " paises adicionados");
				msg.append("\n");
			}
		}else {
			msg.append("Já inicializado anteriormente");
		}
		
		if(gerarMun) {
			List<Municipio> municipios = new MunicipioXML().realizaLeituraXML();
			for(Municipio m : municipios){
				this.municipioRepo.save(m);
				//System.out.println(m.getxMun());
			}
			if(municipios.size() > 0) {
				msg.append(municipios.size() + " municipios adicionados");
			}
		}
		
		if(gerarUser) {
			Privilege pADMIN = new Privilege();
	    	pADMIN.setActive(true);
	    	pADMIN.setName("ADMIN");
	    	//privilegeRepository.save(pADMIN);
	    	
	    	Privilege pUSER = new Privilege();
	    	pUSER.setActive(true);
	    	pUSER.setName("USER");
	    	//privilegeRepository.save(pUSER);
	    	
	    	Privilege pPROD = new Privilege();
	    	pPROD.setActive(true);
	    	pPROD.setName("PROD");
	    	//privilegeRepository.save(pPROD);
	    	
	    	Privilege pDESEN = new Privilege();
	    	pDESEN.setActive(true);
	    	pDESEN.setName("DES");
	    	//privilegeRepository.save(pDESEN);
	    	
	    	Role r = new Role();
	    	r.setActive(true);
	    	r.setName("ADMIN");
	    	

	    	List<Privilege> lP = new ArrayList<Privilege>();
	    	r.setPrivileges(lP);
	    	r.getPrivileges().add(pADMIN);
	    	r.getPrivileges().add(pUSER);
	    	r.getPrivileges().add(pPROD); 
	    	r.getPrivileges().add(pDESEN);
	    	
	    	//roleRepository.save(r);
	    	
	    	List<Role> lR = new ArrayList<Role>();
	    	User user = new User();
	    	user.setRoles(lR);   	
	    	user.getRoles().add(r);
	    	user.setActive(true);
	    	user.setEmail("admin@admin.com");
	    	user.setLastName("Admin");
	    	user.setName("Administrador");    	
	        user.setPassword(passwordEncoder.encode("2010"));
	        
	        userRepository.save(user);
	        msg.append("\nUsuario adicionado: " + user.getEmail());
	        msg.append("Senha: 2010");
	        
	        List<Municipio> municipios = (List<Municipio>) municipioRepo.findByUf(UF.PB);
	        
	        Empresa empresa = new Empresa();
	        empresa.setUsers(new ArrayList<User>());
	        empresa.setNome("Empresa Padrão");
	        empresa.getUsers().add(user);	        
	        empresa.setMunicipio(municipios.get(13));	        
	        empresa.setPais(paisRepo.findAll().get(26));
	        empresa.setCrt(CRT.SIMPLES_NACIONAL);
	        empresaRepo.save(empresa);
	        
		}
		

		return ResponseEntity.ok(msg.toString());
	}
}
