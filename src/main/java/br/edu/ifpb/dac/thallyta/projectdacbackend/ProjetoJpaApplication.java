package br.edu.ifpb.dac.thallyta.projectdacbackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.SystemRoleServiceImpl;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.repository.SystemRoleRepository;

@SpringBootApplication
@EnableWebMvc
public class ProjetoJpaApplication implements WebMvcConfigurer, CommandLineRunner {
	
	@Autowired
	private SystemRoleServiceImpl systemRoleService;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
	.allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH");
		
	}

	@Override
	public void run(String... args) throws Exception {
		systemRoleService.createDefaultValues();
	}
}
