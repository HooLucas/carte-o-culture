@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/img/cover/**")
          .addResourceLocations("file:src/main/resources/img/cover/");	
    }
}