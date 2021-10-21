package coms309.vb6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class WelcomeController {
  @GetMapping("/")
  public String welcome() {
	  return "Welcome to CyChat";
  }
}
