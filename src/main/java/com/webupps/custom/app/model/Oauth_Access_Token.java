package com.webupps.custom.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name = "oauth_access_token", catalog = "scormspringbackend")
public class Oauth_Access_Token {
	@Id
	@Column(name="authentication_id", length=256)
    private String authentication_id;
	
	@Column(name="token_id", length=256)
    private String token_id;
	
	@Column(name="token")
	@Lob
    private Blob token;
	
	@Column(name="users_name", length=256)
    private String users_name;
	
	@Column(name="client_id", length=256)
    private String client_id;
	
	@Column(name="authentication")
	@Lob
    private Blob authentication;
	
	@Column(name="refresh_token", length=256)
    private String refresh_token;

	public String getAuthentication_id() {
		return authentication_id;
	}

	public void setAuthentication_id(String authentication_id) {
		this.authentication_id = authentication_id;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public Blob getToken() {
		return token;
	}

	public void setToken(Blob token) {
		this.token = token;
	}

	public String getUsers_name() {
		return users_name;
	}

	public void setUsers_name(String users_name) {
		this.users_name = users_name;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public Blob getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Blob authentication) {
		this.authentication = authentication;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}


}
