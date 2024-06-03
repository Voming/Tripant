package mclass.store.tripant.email.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import mclass.store.tripant.apikeys.KeysJaewon;

@Component
public class Gmail extends Authenticator {
	
	@Autowired
	private KeysJaewon keysJaewon;
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(keysJaewon.getGmail(), keysJaewon.getGmailPwd());
	}
}
