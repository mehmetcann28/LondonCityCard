package com.mcann.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {
	@Value("$(city-card.jwt.secret-key)")
	private String SecretKey;
	@Value("$(city-card.jwt.issuer)")
	private String Issuer;
	private final Long ExDate = 1000L * 40; // 40sn sonra iptal olsun

	public String createToken(Long authId){
		Date createdDate = new Date(System.currentTimeMillis());
		Date expirationDate = new Date(System.currentTimeMillis() + ExDate);
		Algorithm algorithm = Algorithm.HMAC512(SecretKey);
		String token = JWT.create()
		                  .withAudience()
		                  .withIssuer(Issuer)
		                  .withIssuedAt(createdDate)
		                  .withExpiresAt(expirationDate)
		                  .withClaim("authId", authId)
		                  .withClaim("key", "JX_15_TJJJ")
		                  .sign(algorithm);
		return token;
	}

	public Optional<Long> validateToken(String token){
		try{
			Algorithm algorithm = Algorithm.HMAC512(SecretKey);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token); // Bu token bize mi ait
			if(Objects.isNull(decodedJWT)) // Eğer Token doğrulanamaz ise null döner bizde empty olarak return ederiz.
				return Optional.empty();
			Long authId =  decodedJWT.getClaim("authId").asLong();
			return Optional.of(authId);
		}catch (Exception exception){
			return Optional.empty();
		}
	}
}