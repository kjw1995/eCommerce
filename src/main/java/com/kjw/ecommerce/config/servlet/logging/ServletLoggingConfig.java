package com.kjw.ecommerce.config.servlet.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletLoggingConfig {

	@Bean
	public CustomRequestLoggingFilter requestLoggingFilter() {

		CustomRequestLoggingFilter filter = new CustomRequestLoggingFilter();
		filter.setIncludeClientInfo(true);
		filter.setIncludeQueryString(true);
		filter.setIncludeHeaders(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		return filter;

	}

}
