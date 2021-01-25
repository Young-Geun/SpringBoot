package com.choi.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.choi.web.interceptor.WebInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private WebInterceptor webInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webInterceptor)
				.addPathPatterns("/**") // 적용할 URL 패턴
				.excludePathPatterns("/guestbook/**"); // 제외할 URL 패턴		
	}
}
