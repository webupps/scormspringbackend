package com.webupps.custom.app.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_code", catalog = "scormspringbackend")
public class Oauth_Code {
	@Id
	@Column(name="code", length=256, nullable=true)
    private String code;
	
	@Column(name="authentication")
	@Lob
    private Blob authentication;
}
