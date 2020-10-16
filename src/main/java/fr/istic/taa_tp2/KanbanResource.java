package fr.istic.taa_tp2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Parameter;

@Path("/pet")
@Produces({ "application/json", "application/xml" })
public class KanbanResource {

	private Map<Long, Kanban> kb = new HashMap<Long, Kanban>();
	
	@GET
	public List<Kanban> getKanbans(){
		return new ArrayList<Kanban>(kb.values());
	}
	
	@GET
	@Path("/{kbId}")
	public Kanban getKBById(@PathParam("kbId") Long kbId) {
		try {
			return kb.get(kbId);
		} catch(IndexOutOfBoundsException e) {
			return new Kanban();
		}
	}

	@POST
	@Consumes("application/json")
	public Response addKanban(
			@Parameter(description = "Kanban object that needs to be added to the store", required = true) Kanban kanban) {
		kb.put(kanban.getId(), kanban);
		return Response.ok().entity("SUCCESS").build();
	}
	
	@DELETE
	@Path("/{kbId}")
	public Response delPet(@PathParam("kbId") Long id) {
		kb.remove(id);
		return Response.ok().entity("SUCCESS").build();
	}
}