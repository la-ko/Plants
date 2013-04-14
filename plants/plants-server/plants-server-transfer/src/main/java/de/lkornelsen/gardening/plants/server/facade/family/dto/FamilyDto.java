package de.lkornelsen.gardening.plants.server.facade.family.dto;

import java.io.Serializable;

public class FamilyDto implements Serializable {

	private static final long serialVersionUID = 20130402L;

	private long id;

	private String name;

	private long version;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}

	public long getVersion() {
		return version;
	}
}
