/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@Configuration
public class WebClientConfig {

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public RestTemplate setConf(){
		RestTemplate  restTemplate = new RestTemplate();

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	@Bean
	WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
				new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		return WebClient.builder()
				.apply(oauth2Client.oauth2Configuration())
				.build();
	}

	@Bean
	OAuth2AuthorizedClientManager authorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository authorizedClientRepository) {

		OAuth2AuthorizedClientProvider authorizedClientProvider =
				OAuth2AuthorizedClientProviderBuilder.builder()
						.authorizationCode()
						.refreshToken()
						.clientCredentials()
						.build();
		DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, authorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		return authorizedClientManager;
	}
}
