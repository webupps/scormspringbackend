package com.webupps.custom.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "authorities", catalog = "scormspringbackend")
public class Authorities {
	@Id
	@Column(name="username",  nullable=false, length=256)
    private String username;
	@Column(name="authority",  nullable=false, length=256)
    private String authority;
}
