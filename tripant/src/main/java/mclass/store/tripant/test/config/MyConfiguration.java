package mclass.store.tripant.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//WebMvcConfigurer -> url 관련 설정
@Configuration
@EnableWebMvc
public class MyConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				System.out.println("preHandler");
				
				HttpSession session = request.getSession();
				
				Object obj = session.getAttribute("sslogin");
				
//				if(obj==null) {
//					response.sendRedirect(request.getContextPath()+"/login");
//					return false;
//				}else {
					return true;
//				}
			}
		})
		.addPathPatterns("/**")
		.excludePathPatterns("/login")
		.excludePathPatterns("/join")
		.excludePathPatterns("/main/*", "/css/**", "/js/**", "/images/**", "/static/**");
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	}
}
