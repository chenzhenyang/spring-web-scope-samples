package com.example.demo;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class TestConfiguration {

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public IAddress test1(@Value("#{request.parameterMap}") Map<String, String[]> parameters,
			@Value("#{request.getAttribute('value')}") String value) {
		System.out.println("creating test1.....");
		return new Address(parameters.get("value")[0]);
	}

	@Configuration
	protected static class OAuth2ClientContextConfiguration {

		@Resource
		@Qualifier("test1")
		private IAddress test1;

		@Bean
		@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
		public IAddress test2() {
			IAddress test2 = new Address("test2");
			test2.setPrevious(test1);
			return test2;
		}

	}

	@Bean
	@Lazy
	public IAddress test3(@Qualifier("test2") IAddress test2) {
		IAddress test3 = new Address("test3");
		test3.setPrevious(test2);
		return test3;
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
}
