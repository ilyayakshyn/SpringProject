package com.dtno.coockingmachine.CoockingMachine.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.logging.Logger;

public class Initializer implements WebApplicationInitializer {

    private static Logger log = Logger.getLogger(WebApplicationInitializer.class.getName());

    // этот класс тоже хз нахуя нужен, и без него пиздато работает =)))0000000
    // ну ваще тут грузят эти диспатчеры, которые у нас и так в спринг буте грузятся сами по себе
    // но лучше почитать чтоб не наделать говна
    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        
        log.info("Spring context configuration registration;");

        ctx.register(SecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));

        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
