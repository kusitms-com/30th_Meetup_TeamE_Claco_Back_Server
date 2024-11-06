package com.curateme.claco.global.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProbeController {

	@RequestMapping("/probe")
	public String probe() {
		return "curate me!";
	}

}
