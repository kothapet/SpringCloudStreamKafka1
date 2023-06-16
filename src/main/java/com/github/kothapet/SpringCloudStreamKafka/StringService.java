package com.github.kothapet.SpringCloudStreamKafka;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class StringService {

	//@Bean
	public String upperService(String instr) {
		System.out.println("upperService Before :" + instr);
		String rval = instr.toUpperCase();
		System.out.println("*upperService After : " + rval);
		return rval;
	}

}
