package com.deodentalink;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deodentalink.model.Specialist;
import com.deodentalink.model.Visit;
import com.deodentalink.model.Visitor;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author mir
 */
@QuarkusTest
public class VisitRestControllerTest {

    @BeforeEach
    public void beforeEach() {
        given()
                .when()
                .delete("/api/visit/reset")
                .then()
                .statusCode(200);
    }

    @Test
    public void createVisit() {
      given()
            .when()
            .get("/api/visit")
            .then()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("$.size()", is(0));

      Visit visit = new Visit(LocalDate.now().plusDays(3), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialsit"));

      given()
            .body(visit)
            .log().body()
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .when()
            .post("/api/visit")
            .then()
            .statusCode(201)
            .contentType(APPLICATION_JSON)
            .log().body()
            .body("visitTime", is(visit.getVisitTimeString()));

      given()
            .when()
            .get("/api/visit")
            .then()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("$.size()", is(1));

        Visit visitWithId = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialsit"));
        visitWithId.id = 1L;

        given()
              .body(visitWithId)
              .contentType(APPLICATION_JSON)
              .accept(APPLICATION_JSON)
              .when()
              .post("/api/visit")
              .then()
              .statusCode(400);
    }

    @Test
    public void checkVisitTimeisNotNull() {
      given()
            .when()
            .get("/api/visit")
            .then()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("$.size()", is(0));

      Visit visit = new Visit(LocalDate.now().plusDays(1), null, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialsit"));

      given()
            .body(visit)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .when()
            .post("/api/visit")
            .then()
            .statusCode(400);

      given()
            .when()
            .get("/api/visit")
            .then()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("$.size()", is(0));
    }

    @Test
    public void checkVisitDateisFuture() {
      given()
            .when()
            .get("/api/visit")
            .then()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("$.size()", is(0));

      Visit visit = new Visit(LocalDate.now().minusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialsit"));

      given()
            .body(visit)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .when()
            .post("/api/visit")
            .then()
            .statusCode(400);

      given()
            .when()
            .get("/api/visit")
            .then()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("$.size()", is(0));
    }
    
    @Test
    public void getAllVisits() {
        given()
                .when()
                .get("/api/visit")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));

        Visit visit = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialist"));
        
        given()
                .body(visit)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .when()
                .post("/api/visit")
                .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("id", is(0));

        Visit visit1 = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialist"));

        given()
                .body(visit1)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .when()
                .post("/api/visit")
                .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("id", is(1));

        given()
                .when()
                .get("/api/visit")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(2));
      }

  @Test
  public void getOneVisit() {
    given()
          .when()
          .get("/api/visit")
          .then()
          .statusCode(200)
          .contentType(APPLICATION_JSON)
          .body("$.size()", is(0));

        Visit visit = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialist"));

        given()
                .body(visit)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .when()
                .post("/api/visit")
                .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("id", is(0));


        given()
                .when()
                .get("/api/visit/{id}", 0)
                .then()
                .statusCode(200);
    }

    @Test
    public void getNonExistingVisit() {
        given()
                .when()
                .get("/api/visit/1")
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteAllVisits() {
        Visit visit = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialist"));
        Visit visit1 = new Visit(LocalDate.now().plusDays(2), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialist"));

        given()
                .body(visit)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .when()
                .post("/api/visit")
                .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("id", is(0));

        given()
                .log().body()
                .body(visit1)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .when()
                .post("/api/visit")
                .then()
                .statusCode(201)
                .contentType(APPLICATION_JSON)
                .body("visitDate", is(visit1.getVisitDate().toString()));

        given()
                .when()
                .get("/api/visit")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(2));

        given()
                .when()
                .delete("/api/visit")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/api/visit")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));
    }

    @Test
    public void deleteOneVisit() {
        Visit visit = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialist"));

        given()
            .body(visit)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .when()
            .post("/api/visit")
            .then()
            .statusCode(201)
            .contentType(APPLICATION_JSON)
            .body("visitTime", is(visit.getVisitTimeString()));

        given()
                .when()
                .get("/api/visit")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(1));

        given()
                .when()
                .delete("/api/visit/0")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/api/visit")
                .then()
                .statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("$.size()", is(0));
    }
}
