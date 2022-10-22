package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
public class HomeController {

		final Logger logger = LoggerFactory.getLogger(getClass());
		
		
		// 2번 생성자 방식
//		public HomeController(HomeService homeService) {
//			this.homeService = homeService;
//		}
		
		@GetMapping("/home")
		public void home() {
			logger.info("this is home.");
		}
		
		
}
