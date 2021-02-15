package com.iamalokit.mycrypto.service;

import java.util.List;

import com.iamalokit.mycrypto.entity.CryptoRate;

public interface ExternalApiCryptoRatesService {

	public List<CryptoRate> getCryptoRates();

}
