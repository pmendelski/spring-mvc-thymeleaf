package net.exacode.bootstrap.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	private static final String ENVIRONMENT_FILE = "classpath:/profile.properties";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		activateSpringProfile(rootContext.getEnvironment());
		rootContext.register(WebMvcConfiguration.class);
		registerServlets(servletContext, rootContext);
	}

	private void activateSpringProfile(
			ConfigurableEnvironment configurableEnvironment) {
		try {
			configurableEnvironment.getPropertySources().addLast(
					new ResourcePropertySource(ENVIRONMENT_FILE));
			logger.info("Spring active profiles: {}", Arrays
					.toString(configurableEnvironment.getActiveProfiles()));
		} catch (IOException e) {
			// Properties file cannot be found.
			// We cannot ignore it!
			throw new IllegalStateException(
					"Unable to initialize spring profiles: " + e.getMessage());
		}

	}

	private void registerServlets(ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootContext) {
		ServletRegistration.Dynamic springDispatcher = servletContext
				.addServlet("SpringDispatcherServlet", new DispatcherServlet(
						rootContext));
		springDispatcher.setLoadOnStartup(1);
		springDispatcher.addMapping("/");
	}

}
