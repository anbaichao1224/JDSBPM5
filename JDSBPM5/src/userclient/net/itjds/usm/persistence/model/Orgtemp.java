package net.itjds.usm.persistence.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OT")
public class Orgtemp extends Baserole {

}
