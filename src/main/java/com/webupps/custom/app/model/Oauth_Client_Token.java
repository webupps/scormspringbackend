package com.webupps.custom.app.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_token", catalog = "scormspringbackend")
public class Oauth_Client_Token {
	@Id
	@Column(name="token_id", length=256, nullable=true)
    private String token_id;
	
	@Column(name="token")
	@Lob
    private Blob token;
	
	@Column(name="authentication_id", length=256, nullable=true)
    private String authentication_id;
	
	@Column(name="user_name", nullable=true, length=256)
    private String user_name;
	
	@Column(name="client_id", nullable=true, length=256)
    private String client_id;
}
