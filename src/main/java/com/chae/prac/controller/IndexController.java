package com.chae.prac.controller;



import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chae.prac.model.User;
import com.chae.prac.repository.UserRepository;


@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;

	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	@GetMapping({ "", "/" })
	public @ResponseBody String index() {
		return "인덱스 페이지 입니다.";
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
		
		return "user";
	}
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		
		return "admin";
	}
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		
		return "manager";
	}
	
	//스프링시큐리티 해당주소를 낚아채버리네용 - SecurityConfig 파일 생성 후 작동 안함
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "loginForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {

		
		user.setRole("ROLE_USER"); //private Timestamp createDate; 은 자동으로 날짜가 들어감
		userRepository.save(user); //여기까지 하면 회원가입이 잘되지만 시큐리티로 로그인할 수가없다(비밀번호 :1234 <-원인) 이유는 패스워드가 암호화가 안되었기 때문이다.
		String rawPassword = user.getPassword(); //암호화 1
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); //암호화 2
		
		user.setPassword(encPassword);
		
		
		return "redirect:/loginForm";
	}

	
	@GetMapping("/joinForm")
	public String joinForm() {
		
		return "joinForm";
	}


}