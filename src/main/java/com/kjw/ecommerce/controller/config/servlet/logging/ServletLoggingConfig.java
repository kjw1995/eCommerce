package com.kjw.ecommerce.controller.config.servlet.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletLoggingConfig {

	@Bean
	public CustomRequestLoggingFilter requestLoggingFilter() {

		CustomRequestLoggingFilter filter = new CustomRequestLoggingFilter();
		filter.setIncludeClientInfo(false);
		filter.setIncludeQueryString(true);
		filter.setIncludeHeaders(false);
		filter.setIncludePayload(false);
		filter.setMaxPayloadLength(10000);
		return filter;

	}

}
