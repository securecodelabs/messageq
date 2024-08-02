package com.deodentalink.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import com.deodentalink.model.Visit;

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
@Path("/api/visit")
@Produces("application/json")
@Consumes("application/json")
public class VisitRestController {

  private AtomicLong sequenceId = new AtomicLong();
  //private List<Visit> visits = new ArrayList<>();

  private List<Visit> visits = Collections.synchronizedList(new ArrayList<>());

  private static final int HOURS_BEFORE = 24;

  /*public VisitRestController() {
    this.visitRecords = new ArrayList<>();
  }*/

  @POST
  public Response create(@Valid Visit visit) {
    if (visit.id != null) {
      throw new WebApplicationException("A new entity cannot already have an ID", Response.Status.BAD_REQUEST);
    }

    visit.id = sequenceId.get();
    sequenceId.incrementAndGet();

    visit.setMessageSendDateTime(HOURS_BEFORE);
      
    visits.add(visit);
        
    return Response.status(Response.Status.CREATED).entity(visit).build(); 
  }

    @GET
    public List<Visit> getAll() {
        return visits;
    }

    @GET
    @Path("/{id}")
    public Visit getOneById(@PathParam("id") long id) {
        
      Optional<Visit> entity = visits.stream()
                                    .filter(visit -> visit.id == id)
                                    .findFirst();

      return entity.orElseThrow(() -> 
        new WebApplicationException("Entity does not exist.", Response.Status.NOT_FOUND));

        /*Visit entity = null;
        for (int i = 0; i < tvShows.size(); i++) {
            TvShow tvShow = tvShows.get(i);
            if (tvShow.id == id) {
                entity = tvShow;
                break;
            }
        }
        if (entity == null) {
            throw new WebApplicationException("Entity does not exist. ", Response.Status.NOT_FOUND);
        }
        return entity;*/
    }

    @DELETE
    public Response deleteAll() {
        visits.clear();
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") long id) {
      
      OptionalInt indexOpt = IntStream.range(0, visits.size())
                                      .filter(i -> visits.get(i).id == id)
                                      .findFirst();

      /*indexOpt.ifPresent(visits::remove);
      return Response.ok().build();*/

      if (indexOpt.isPresent()) {
        visits.remove(indexOpt.getAsInt());
        return Response.ok().build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();
      }
      
        /*int index = 0;
        for (; index < visits.size(); index++) {
            Visit visit = visits.get(index);
            if (visit.id == id) {
                break;
            }
        }
        if (index < visits.size()) {
            visits.remove(index);
        }
        return Response.ok().build();*/
    }

}
