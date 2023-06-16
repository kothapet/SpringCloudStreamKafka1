package com.github.kothapet.SpringCloudStreamKafka;

//import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {

	@Autowired
	private StringService strService;
	
	@Bean
	public Function<String, String> upperController() {
		return message -> {
			System.out.println("Before :" + message);
			String rval = strService.upperService(message);
			System.out.println("*After : " + rval);
			return rval;
		};
	}

}
