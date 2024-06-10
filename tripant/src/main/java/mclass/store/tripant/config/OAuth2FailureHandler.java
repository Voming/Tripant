package mclass.store.tripant.config;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.shaded.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		if(exception instanceof UsernameNotFoundException) {
			String msg = exception.getMessage();
			Map<String, Object> map = new Gson().fromJson(msg, Map.class);
			request.getSession().setAttribute("memEmail", map.get("memEmail"));
			request.getSession().setAttribute("memType", map.get("memType"));
			response.setContentType("text/html; charset=utf-8");
			response.getWriter()
			.append("<script type=\"text/javascript\">")
			.append("alert('회원가입을 위해 회원정보 입력 페이지로 이동합니다.');")
			.append("location.href = '/join/sns'")
			.append("</script>")
			.close();
		}
	}
}
