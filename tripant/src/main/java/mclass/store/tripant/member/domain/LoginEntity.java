package mclass.store.tripant.member.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data @Component
public class LoginEntity {
	private String memEmail;
	private String memNick;
	private String memRole;
	private Integer memEnabled;
}
