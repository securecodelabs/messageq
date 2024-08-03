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

  private static final int HOURS_BEFORE = 24;

  /*public VisitRestController() {
    this.visitRecords = new ArrayList<>();
  }*/

  @POST
  @Transactional
  public Response create(@Valid Visit visit) {
    if (visit.id != null) {
      throw new WebApplicationException("A new entity cannot already have an ID", Response.Status.BAD_REQUEST);
    }

    /*visit.id = sequenceId.get();
    sequenceId.incrementAndGet();
      
    visits.add(visit);*/

    //visit.visitor.persist();

    visit.setMessageSendDateTime(HOURS_BEFORE);
    visit.persist();
        
    return Response.status(Response.Status.CREATED).entity(visit).build(); 
  }

  @PUT
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
  }

  @GET
  public List<Visit> getAll() {
    return Visit.listAll();
  }

  @GET
  @Path("/{id}")
  public Visit getOneById(@PathParam("id") long id) {
        
    /*Optional<Visit> entity = visits.stream()
                                  .filter(visit -> visit.id == id)
                                  .findFirst();

    return entity.orElseThrow(() -> 
      new WebApplicationException("Entity does not exist.", Response.Status.NOT_FOUND));*/
      
    Visit entity = Visit.findById(id);
    if (entity == null) {
      throw new WebApplicationException("Entity does not exist. ", Response.Status.NOT_FOUND);
    }
    return entity;
       
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
      
    Visit.deleteById(id);
    return Response.ok().build();

    /*OptionalInt indexOpt = IntStream.range(0, visits.size())
                                    .filter(i -> visits.get(i).id == id)
                                    .findFirst();

    if (indexOpt.isPresent()) {
      visits.remove(indexOpt.getAsInt());
      return Response.ok().build();
    } else {
      return Response.status(Response.Status.NOT_FOUND).build();
    }*/
  }

}
