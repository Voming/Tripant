package mclass.store.tripant.member.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
	OWNER("owner"), ADMIN("admin"), FONTUSER("vip"), USER("mem");
	
	private String value;
	
	MemberRole(String value){
		this.value = value;
	}
}
