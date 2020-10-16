package fr.istic.taa.jaxrs.rest;

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

import fr.istic.taa.jaxrs.domain.Pet;
import io.swagger.v3.oas.annotations.Parameter;

@Path("/pet")
@Produces({ "application/json", "application/xml" })
public class PetResource {

	private Map<Long, Pet> pets = new HashMap<Long, Pet>();
	
	@GET
	public List<Pet> getPets(){
		return new ArrayList<Pet>(pets.values());
	}
	
	@GET
	@Path("/{petId}")
	public Pet getPetById(@PathParam("petId") Long petId) {
		try {
			return pets.get(petId);
		} catch(IndexOutOfBoundsException e) {
			return new Pet();
		}
	}

	@POST
	@Consumes("application/json")
	public Response addPet(
			@Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
		pets.put(pet.getId(), pet);
		return Response.ok().entity("SUCCESS").build();
	}
	
	@DELETE
	@Path("/{petId}")
	public Response delPet(@PathParam("petId") Long id) {
		pets.remove(id);
		return Response.ok().entity("SUCCESS").build();
	}
}