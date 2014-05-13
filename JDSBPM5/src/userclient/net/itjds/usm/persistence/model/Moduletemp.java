package net.itjds.usm.persistence.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MT")
public class Moduletemp extends Baserole {
	private String isdelperson;

	public String getIsdelperson() {
		return isdelperson;
	}

	public void setIsdelperson(String isdelperson) {
		this.isdelperson = isdelperson;
	}

}
