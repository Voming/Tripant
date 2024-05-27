package mclass.store.tripant.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mclass.store.tripant.apikeys.KeysJaewon;

//WebMvcConfigurer -> url 관련 설정
//@Configuration
//@EnableWebMvc
public class MyConfiguration implements WebMvcConfigurer {
	
//	@Bean
//	public KakaoKey kakaoKey() {
//		KakaoKey kakaoKey = new KakaoKey();
//		kakaoKey.set
//	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HandlerInterceptor() {
//			@Override
//			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//					throws Exception {
//				System.out.println("preHandler");
//				
//				HttpSession session = request.getSession();
//				
//				Object obj = session.getAttribute("sslogin");
//				
//				if(obj==null) {
//					response.sendRedirect(request.getContextPath()+"/login");
//					return false;
//				}else {
//					return true;
//				}
//			}
//		})
//		.addPathPatterns("/**")
//		.excludePathPatterns("/login")
//		.excludePathPatterns("/join")
//		.excludePathPatterns("/main/*", "/css/**", "/js/**", "/image/**");
//		
//	}
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/css/**", "/js/**", "/html/**", "/image/**");
//	}
}
