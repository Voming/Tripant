package mclass.store.tripant.admin.domain;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data 
@Component
public class AdminMemEntity {
	private String memNick;
	private String memEmail;
	private Date memJoinDate;
	private Date memQuitDate;
	private String memRole;
}
