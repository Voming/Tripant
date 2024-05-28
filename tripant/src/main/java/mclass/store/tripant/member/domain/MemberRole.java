package mclass.store.tripant.member.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
	OWNER("OWNER"), ADMIN("ADMIN"), FONTUSER("VIP"), USER("MEM");
	
	private String value;
	
	MemberRole(String value){
		this.value = value;
	}
}
