package com.webupps.custom.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_client_details", catalog = "scormspringbackend")
public class Oauth_Client_Details {
	@Id
	@Column(name="client_id", length=256, nullable=false)
    private String client_id;
	
	@Column(name="resource_ids", length=256, nullable=true)
    private String resource_ids;
	
	@Column(name="client_secret", length=256, nullable=false)
    private String client_secret;
	
	@Column(name="scope", length=256, nullable=true)
    private String scope;
	
	@Column(name="authorized_grant_types", length=256, nullable=true)
    private String authorized_grant_types;
	
	@Column(name="web_server_redirect_uri", length=256, nullable=true)
    private String web_server_redirect_uri;
	
	@Column(name="authorities", length=256, nullable=true)
    private String authorities;
	
	@Column(name="access_token_validity", length=11, nullable=true)
	private Integer access_token_validity;
	
	@Column(name="refresh_token_validity", length=11, nullable=true)
	private Integer refresh_token_validity;
	
	@Column(name="additional_information", length=4000, nullable=true)
	private String additional_information;
	
	@Column(name="autoapprove", length=256, nullable=true)
	private String autoapprove;
}
