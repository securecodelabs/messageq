package com.deodentalink.controller;

import java.util.List;

import com.deodentalink.model.Visit;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author mir
 */
@Path("/api/visit")
@Produces("application/json")
@Consumes("application/json")
public class VisitRestController {

  //private AtomicLong sequenceId = new AtomicLong(0);

  //private List<Visit> visits = Collections.synchronizedList(new ArrayList<>());

  private static final int DAYS_BEFORE = 1;

  /*public VisitRestController() {
    this.visitRecords = new ArrayList<>();
  }*/

  @POST
  @Transactional
  public Response create(@Valid Visit visit) {
    if (visit.id != null) {
      throw new WebApplicationException("A new entity cannot already have an ID", Response.Status.BAD_REQUEST);
    }

    visit.setMessageSendDate(visit.calculateMessageSendDate(DAYS_BEFORE));
    visit.persist();

    return Response.status(Response.Status.CREATED).entity(visit).build(); 
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response update(@PathParam("id") Long id, @Valid Visit visit) {
      if (id == null) {
          throw new WebApplicationException("ID is null", Response.Status.BAD_REQUEST);
      }

      Visit existingVisit = Visit.findById(id);
      if (existingVisit == null) {
          return Response.status(Response.Status.NOT_FOUND)
                        .entity("Visit with ID " + id + " not found.")
                        .build();
      }

      existingVisit.visitDate = visit.visitDate;
      existingVisit.visitTime = visit.visitTime;
      existingVisit.visitor = visit.visitor;
      existingVisit.specialist = visit.specialist;
      existingVisit.setMessageSendDate(visit.calculateMessageSendDate(DAYS_BEFORE));

      existingVisit.persist();

      return Response.ok(existingVisit).build();
  }

  /*@PUT
  @Transactional
  public Response update(@Valid Visit visit) {
    if (visit.id == null) {
      throw new WebApplicationException("ID is null", Response.Status.BAD_REQUEST);
    }

    Visit updated = Visit.findById(visit.id);

    updated.id = visit.id;
    updated.visitDate = visit.visitDate;
    updated.visitTime = visit.visitTime;
    updated.visitor = visit.visitor;
    updated.specialist = visit.specialist;
    updated.setMessageSendDateTime(HOURS_BEFORE);

    return Response.ok(updated).build();
  }*/

  @GET
  public Response getAll() {
    List<Visit> visits = Visit.listAll();
    return Response.ok(visits).build();
  }

  @GET
  @Path("/{id}")
  public Response getOneById(@PathParam("id") long id) {
    Visit entity = Visit.findById(id);
    if (entity == null) {
      return Response.status(Response.Status.NOT_FOUND)
        .entity("Visit with ID " + id + " not found.")
        .build();
    }
    return Response.ok(entity).build();      
  }

  @DELETE
  @Transactional
  public Response deleteAll() {
    Visit.deleteAll();
    return Response.ok().build();
  }

    /*@DELETE
    @Path("/reset")
    public Response reset() {
        visits.clear();
        sequenceId.set(0);
        return Response.ok().build();
    }*/

  @DELETE
  @Path("/{id}")
  public Response deleteOne(@PathParam("id") long id) {
      
    boolean deleted = Visit.deleteById(id);
    if (!deleted) {
      return Response.status(Response.Status.NOT_FOUND)
              .entity("Visit with ID " + id + " not found.")
              .build();
    }
    return Response.ok().build();
  }

}
