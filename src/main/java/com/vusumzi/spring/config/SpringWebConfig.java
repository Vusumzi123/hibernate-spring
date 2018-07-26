package com.vusumzi.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({"com.vusumzi.spring.web"})
//@PropertySource("classpath:/main/resources/spring.properties")
@PropertySource("classpath:/com/properties/spring.properties")
public class SpringWebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler( env.getProperty("config.resources.handle") ).addResourceLocations( env.getProperty("config.resources.location") );
		registry.addResourceHandler( "/bower_components/**" ).addResourceLocations("/bower_components/");
		registry.addResourceHandler( "/dist/**" ).addResourceLocations("/dist/");
		registry.addResourceHandler( "/plugins/**" ).addResourceLocations("/plugins/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(env.getProperty("config.view.prefix"));
		viewResolver.setSuffix(env.getProperty("config.view.sufix"));
		return viewResolver;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
