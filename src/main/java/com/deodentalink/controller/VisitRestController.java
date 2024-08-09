package com.deodentalink.controller;

import java.time.LocalDate;
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
  //@Path("/{id}")
  @Transactional
  public Response update(@Valid Visit visit) {
    
    if (visit.id == null) {
      throw new WebApplicationException("Invalid ID", Response.Status.BAD_REQUEST);
    }

    //System.out.println("id: " + visit.id);
    //System.out.println("Name: " + visit.visitor.name);

    Visit existingVisit = Visit.findById(visit.id);
    if (existingVisit == null) {
      return Response.status(Response.Status.NOT_FOUND)
                    .entity("Visit with ID " + visit.id + " not found.")
                    .build();
    }

    //System.out.println(existingVisit.toString());

    //System.out.println("Existing visit visitor name and id: " + existingVisit.visitor.name + ", " + existingVisit.id);
    existingVisit.visitDate = visit.visitDate;
    existingVisit.visitTime = visit.visitTime;
    existingVisit.visitor = visit.visitor;
    existingVisit.specialist = visit.specialist;

    if (visit.messages != null) {
      existingVisit.messages.clear();
      existingVisit.messages.addAll(visit.messages);
    }
    
    existingVisit.setMessageSendDate(visit.calculateMessageSendDate(DAYS_BEFORE));

    existingVisit.persist();

    //System.out.println(existingVisit.toString());

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
  public Response getAllVisits() {
    List<Visit> visits = Visit.findAllOrderByDateAndTime();
    return Response.ok(visits).build();
  }

  @GET
  @Path("/{id}")
  public Response getOneById(@PathParam("id") long id) {
    Visit visit = Visit.findById(id);
    if (visit == null) {
      return Response.status(Response.Status.NOT_FOUND)
        .entity("Visit with ID " + id + " not found.")
        .build();
    }
    return Response.ok(visit).build();      
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

  @GET
  @Path("/specialist/{specialistId}")
  public Response getAllBySpecialistId(@PathParam("specialistId") long specialistId) {
    List<Visit> visits = Visit.findAllBySpecialistId(specialistId);
    if (visits == null) {
        return Response.status(Response.Status.NOT_FOUND)
              .entity("Visits with specialist ID " + specialistId + " not found.")
              .build();
        //throw new WebApplicationException("Entity does not exist.", Response.Status.NOT_FOUND);
      }
      return Response.ok(visits).build();
  }

  @GET
  @Path("/visitDate/{visitDate}")
  public Response getAllByVisitDate(@PathParam("visitDate") LocalDate visitDate) {
    List<Visit> visits = Visit.findByDate(visitDate);
    if (visits == null) {
      return Response.status(Response.Status.NOT_FOUND)
            .entity("Visits with visit date " + visitDate.toString() + " not found.")
            .build();
      //throw new WebApplicationException("Entity does not exist.", Response.Status.NOT_FOUND);
    }
    return Response.ok(visits).build();
  }

}
