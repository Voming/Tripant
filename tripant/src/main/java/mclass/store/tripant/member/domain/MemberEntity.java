package mclass.store.tripant.member.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MemberEntity {
	private String memEmail;
	private String memNick;
	private String memPassword;
	private String memRole;
	private Integer memEnabled;
	private String memType;
	private String memJoinDate;
	private String memQuitDate;
	private String memBirth;
}
