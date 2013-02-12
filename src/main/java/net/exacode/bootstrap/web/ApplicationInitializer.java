package net.exacode.bootstrap.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebMvcConfiguration.class);
		rootContext.setDisplayName("SpringApp");

		// FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
		// "CharacterEncodingFilter", new CharacterEncodingFilter());
		// encodingFilter.setInitParameter("encoding", "UTF-8");
		// encodingFilter.setInitParameter("forceEncoding", "true");
		// encodingFilter.addMappingForUrlPatterns(null, false, "/*");

		registerServlets(servletContext, rootContext);
	}

	private void registerServlets(ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootContext) {

		ServletRegistration.Dynamic springDispatcher = servletContext
				.addServlet("springDispatcher", new DispatcherServlet(
						rootContext));
		springDispatcher.setLoadOnStartup(1);
		springDispatcher.addMapping("/");
	}

}
