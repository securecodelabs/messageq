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
                .delete("/api/visit")
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

      Visit visit = new Visit(LocalDate.now().plusDays(1), LocalTime.NOON, new Visitor("Visitor", "Surname", "+37012345678"), new Specialist("Ieva", "Specialsit"));
      given()
            .body(visit)
            .contentType(APPLICATION_JSON)
            .accept(APPLICATION_JSON)
            .when()
            .post("/api/visit")
            .then()
            .statusCode(201)
            .contentType(APPLICATION_JSON)
            .body("visitTime", is(visit.getVisitTime()));

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

}
