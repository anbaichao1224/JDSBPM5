package com.kzxd.JournalEditorial.action;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("usersservice")
public class JournalEditorial {
	@GET
	@Path("tetstrest")
	@Produces(MediaType.TEXT_HTML)
	public String testrest(){
		return "tongguotong";
	}
}
