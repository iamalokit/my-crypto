package com.iamalokit.mycrypto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iamalokit.mycrypto.service.CryptoRateService;

@RestController
public class BasicCryptoController {
	private final CryptoRateService cryptoRateService;

	public BasicCryptoController(CryptoRateService cryptoRateService) {
		this.cryptoRateService = cryptoRateService;
	}

	@GetMapping("/sink/")
	public ResponseEntity<String> launchSinking() {
		cryptoRateService.sinkAndFilter();
		return new ResponseEntity<>("Test", HttpStatus.OK);
	}
}
