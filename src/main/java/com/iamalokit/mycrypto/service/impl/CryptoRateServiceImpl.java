package com.iamalokit.mycrypto.service.impl;

import java.util.concurrent.CompletionStage;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iamalokit.mycrypto.entity.CryptoRate;
import com.iamalokit.mycrypto.service.CryptoRateService;
import com.iamalokit.mycrypto.service.ExternalApiCryptoRatesService;
import com.mongodb.reactivestreams.client.MongoCollection;

import akka.Done;
import akka.stream.Materializer;
import akka.stream.alpakka.mongodb.javadsl.MongoSink;
import akka.stream.javadsl.Source;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CryptoRateServiceImpl implements CryptoRateService {

	private final ExternalApiCryptoRatesService externalApiCryptoRatesService;
	
	@Qualifier("cryptoRateCollection")
    private final MongoCollection<CryptoRate> cryptoRateCollection;

    @Qualifier("materializer")
    private final Materializer materializer;

	public CryptoRateServiceImpl(ExternalApiCryptoRatesService externalApiCryptoRatesService,
			MongoCollection<CryptoRate> cryptoRateCollection, Materializer materializer) {
		this.externalApiCryptoRatesService = externalApiCryptoRatesService;
		this.cryptoRateCollection = cryptoRateCollection;
		this.materializer = materializer;
	}

	@Override
	public void sinkAndFilter() {
		CompletionStage<Done> completionStage = Source.from(externalApiCryptoRatesService.getCryptoRates())
				.filter(cryptoRate -> !cryptoRate.getCryptoName().equals("BTC")) // skip bitcoins
				.runWith(MongoSink.insertOne(cryptoRateCollection), materializer);
		completionStage.thenRun(() -> {
			log.info("Sink to MongoDB is finished");
		});
	}

}
