package com.pangsoramdepo.rbac.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationProperties {

    @Autowired
	private Environment env;
    
    public String getJwtHeader() {
		return getProperty("jwt.header.string");
	}

	public String getJwtTokenPrefix() {
		return getProperty("jwt.token.prefix");
	}

	public String getJwtAuthorizeKey() {
		return getProperty("jwt.authorities.key");
	}

	public String getJwtSigningKey() {
		return getProperty("jwt.signing.key");
	}

	public Long getJwtTokenValidity() {
		return Long.parseLong(getProperty("jwt.token.validity"));
	}

    private String getProperty(String key) {
        return env.getProperty(key);
    }
}
