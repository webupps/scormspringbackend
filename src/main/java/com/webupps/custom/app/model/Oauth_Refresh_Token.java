package com.webupps.custom.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



//import com.mysql.cj.jdbc.Blob;
import java.sql.Blob;


@Entity
@Table(name = "oauth_refresh_token", catalog = "scormspringbackend")
public class Oauth_Refresh_Token {
	
	@Id
	@Column(name="token_id", length=256)
    private String token_id;
	
	@Column(name="token")
	@Lob
    private Blob token;
	
	@Column(name="authentication")
	@Lob
    private Blob authentication;

	public String getToken_id() {
		return token_id;
	}

	public Blob getToken() {
		return token;
	}

	public Blob getAuthentication() {
		return authentication;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public void setToken(Blob token) {
		this.token = token;
	}

	public void setAuthentication(Blob authentication) {
		this.authentication = authentication;
	}
}
