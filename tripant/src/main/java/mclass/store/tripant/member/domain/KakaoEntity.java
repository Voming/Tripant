package mclass.store.tripant.member.domain;

import java.util.Map;

public class KakaoEntity {

	private Map<String, Object> attributes;
	
	public KakaoEntity(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public String getMemEmail() {
		return (String) attributes.get("email");
	}
}
