package com.micro.serivce.apigateway.response;

import java.util.Collection;

import lombok.Data;

@Data
public class AuthResponse {

	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expiredAt;
	private Collection<String> authorities;
}
