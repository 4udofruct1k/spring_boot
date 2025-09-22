package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses()  {
        return new Class[] { AppConfig.class }; // для сервисов, JPA и т.д.
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
            return new Class[] { WebConfig.class }; // для Spring MVC
        }


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}