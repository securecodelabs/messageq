package com.deodentalink.controller;

import java.util.List;

import com.deodentalink.model.Specialist;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author mir
 */
@Path("/api/specialist")
@Produces("application/json")
@Consumes("application/json")
public class SpecialistRestController {

  @GET
  public List<Specialist> getAll() {
    return Specialist.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    Specialist specialist = Specialist.findById(id);
    if (specialist == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(specialist).build();
  }

  @POST
  @Transactional
  public Response create(@Valid Specialist specialist) {
    if (specialist.id != null) {
      throw new WebApplicationException("A new entity cannot already have an ID", Response.Status.BAD_REQUEST);
    }
    specialist.persist();
    return Response.status(Response.Status.CREATED).entity(specialist).build();
  }

  @DELETE
  @Transactional
  public Response deleteAll() {
    Specialist.deleteAll();
    return Response.ok().build();
  }

  /*@PUT
  @Path("/{id}")
  @Transactional
  public Response update(@PathParam("id") Long id, @Valid Specialist updatedSpecialist) {
      Specialist existingSpecialist = Specialist.findById(id);
      if (existingSpecialist == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      existingSpecialist.firstName = updatedSpecialist.firstName;
      existingSpecialist.lastName = updatedSpecialist.lastName;
      existingSpecialist.persist();
      return Response.ok(existingSpecialist).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response delete(@PathParam("id") Long id) {
      Specialist specialist = Specialist.findById(id);
      if (specialist == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      specialist.delete();
      return Response.noContent().build();
  }*/

  /*@GET
  @Path("/{id}")
  public Specialist getById1(@PathParam("id") Long id) {

    Specialist entity = Specialist.findById(id);
    if (entity == null) {
      throw new WebApplicationException("Entity does not exist. ", Response.Status.NOT_FOUND);
    }
    return entity;
  }*/

}
