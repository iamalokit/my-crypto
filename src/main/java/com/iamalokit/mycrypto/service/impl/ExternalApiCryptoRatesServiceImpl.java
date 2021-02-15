package com.iamalokit.mycrypto.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.iamalokit.mycrypto.entity.CryptoRate;
import com.iamalokit.mycrypto.service.ExternalApiCryptoRatesService;

@Service
public class ExternalApiCryptoRatesServiceImpl implements ExternalApiCryptoRatesService {
	
	private final Random randomGenerator = new Random();
	private Supplier<CryptoRate> cryptoRateSupplier;

	public ExternalApiCryptoRatesServiceImpl() {
		this.cryptoRateSupplier = this::generateRandomCryptoRate;
	}

	private CryptoRate generateRandomCryptoRate() {
		return CryptoRate.builder()._id(UUID.randomUUID()).rateToUSD(randomGenerator.nextDouble())
				.createdAt(Date.from(Instant.now())).cryptoName(randomCryptoName()).build();
	}

	private String randomCryptoName() {
		switch (randomGenerator.nextInt(4)) {
		case 1:
			return "ETH";
		case 2:
			return "USDT";
		case 3:
			return "DeFi";
		default:
			return "BTC";
		}
	}

	@Override
	public List<CryptoRate> getCryptoRates() {
		return Stream.generate(this.cryptoRateSupplier).limit(10).collect(Collectors.toList());
	}

}
