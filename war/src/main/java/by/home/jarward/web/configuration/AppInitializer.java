package by.home.jarward.web.configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.File;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SecurityConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppContext.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new HiddenHttpMethodFilter()};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//
//        DelegatingFilterProxy rememberedUsersFilter = new DelegatingFilterProxy();
//        rememberedUsersFilter.setTargetBeanName("rememberedUsersFilter");
//
//        servletContext
//                .addFilter("rememberedUsersFilter", rememberedUsersFilter.getClass())
//                .addMappingForUrlPatterns(null, false, "/welcome", "/users/*", "/posts/*", "/topics/*");
//    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    private MultipartConfigElement getMultipartConfigElement() {
        int maxUploadFileSizeMb = 1024 * 1024;
        return new MultipartConfigElement(uploadDirectory.getAbsolutePath(), maxUploadFileSizeMb,
                maxUploadFileSizeMb * 2L, maxUploadFileSizeMb / 2);
    }
}
