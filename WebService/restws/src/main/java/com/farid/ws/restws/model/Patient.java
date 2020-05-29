package com.farid.ws.restws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Patient")//JAXB annotation
public class Patient {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
