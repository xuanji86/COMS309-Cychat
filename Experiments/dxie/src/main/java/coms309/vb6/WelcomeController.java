package coms309.vb6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	@GetMapping("/")
	public String welcome(){
	return "Welcome to CyChat";
}
}
