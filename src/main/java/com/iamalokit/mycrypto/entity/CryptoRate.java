package com.iamalokit.mycrypto.entity;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CryptoRate {
	private UUID _id;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date createdAt;
	
	private String cryptoName;
	
	private Double rateToUSD;
	
}
