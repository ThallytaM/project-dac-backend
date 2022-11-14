package br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.UserService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.ConverterService;
import br.edu.ifpb.dac.thallyta.projectdacbackend.business.service.impl.UserServiceImpl;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.Client;
import br.edu.ifpb.dac.thallyta.projectdacbackend.model.entity.User;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.ClientDTO;
import br.edu.ifpb.dac.thallyta.projectdacbackend.presentation.dto.UserDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private ConverterService converterService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity save(@RequestBody UserDTO dto) {
		try {
			User entity = converterService.dtoToUser(dto);
			entity = userService.save(entity);
			dto = converterService.userToDto(entity);
			
			return new ResponseEntity(dto,HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id")Integer id, @RequestBody UserDTO  dto){	
		try {
			dto.setId(id);
			
			Integer userId = dto.getId();
			User user = userService.findById(userId);
			
			if(user==null) {
				throw new IllegalStateException();
			}
			
			User entity = converterService.dtoToUser(dto);
			entity = userService.update(id, entity);
			dto = converterService.userToDto(entity);
			return ResponseEntity.ok(dto);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	

	@DeleteMapping("{id}")
	public ResponseEntity deleteId(@PathVariable("id") Integer id) {
		try {
			userService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());		}		
	}
	
	@GetMapping
	public ResponseEntity find(
			@RequestParam(value = "id",required = false) Integer id,
			@RequestParam(value = "name",required = false)String name) 
	{
		
		try {
			User filter = new User();
			filter.setId(id);
			filter.setName(name);		
			
			List<User> users = userService.find(filter);
			List<UserDTO> dtos = converterService.userToDto(users);
			
			return ResponseEntity.ok(dtos);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());		}		
		}		
	

}
