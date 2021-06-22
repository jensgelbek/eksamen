/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.DogDTO;
import dtos.RenameMeDTO;
import entities.Dog;
import entities.Owner;
import entities.Role;
import entities.User;
import entities.renameme.RenameMe;
import facades.DogFacade;
import io.restassured.http.ContentType;
import java.util.List;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DogResourceTest {
    
    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static EntityManagerFactory EMF=EMF_Creator.createEntityManagerFactoryForTest();
    private static final DogFacade Facade = DogFacade.getInstance(EMF);
    private static Dog d1,d2,d3;
    private static Owner o1,o2;
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
   

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        EMF = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }
    private String login(String role, String password) {
        String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
        return given()
                .contentType("application/json")
                .body(json)
                .when().post("/login")
                .then()
                .extract().path("token");
    }
    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {
           EntityManager em = EMF.createEntityManager();
        o1 = new Owner("Jens Gelbek", "Lyngby Hovedgade 22, 1tv","2800 Lyngby","28534556");
        o2 = new Owner("Peter Rambeck", "Lundtoftegade 102, 1tv","2800 Lyngby","65347123");
       
        d1=new Dog("en hund", "en slags", "billede1", "male", new Date());
        d2=new Dog("en anden hund", "en slags", "billede2", "male", new Date());
        d3=new Dog("en tredje hund", "en ny slags", "billede3", "male", new Date());

       
        try {
            em.getTransaction().begin();
             em.createQuery("DELETE FROM Dog").executeUpdate();  
             em.createQuery("DELETE FROM Owner").executeUpdate();  
            em.createQuery("DELETE FROM Role").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.persist(o1);
            em.persist(o2);
            em.persist(d1);
            em.persist(d2);
                    em.persist(d3);
            o1.addDog(d1);
            o1.addDog(d2);
            o2.addDog(d3);
            User admin = new User("admin", "1234");
            Role roleAdmin = new Role("admin");
            em.persist(admin);
            em.persist(roleAdmin);
            admin.addRole(roleAdmin);   
            User user = new User("user", "1234");
            Role roleUser = new Role("user");
            em.persist(user);
            em.persist(roleUser);
            user.addRole(roleUser);   
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
//This test assumes the database contains two rows
    @Test
    public void testGetById() throws Exception {
       String token = login("admin", "1234");
        System.out.println(token);
        given()
                .contentType("application/json")
                .header("x-access-token", token)
                .pathParam("id", d1.getId())
                .get("/dog/{id}").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
    }
    
    @Test
    public void testGetAll() throws Exception {
        List<DogDTO> foundDogs;
        String token = login("admin", "1234");
        foundDogs = given()
                .contentType(ContentType.JSON)
                .header("x-access-token", token)
                .get("/dog/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .extract().body().jsonPath().getList("", DogDTO.class);

        assertThat(foundDogs, hasItems(
                new DogDTO(d1),
                new DogDTO(d2)
        ));
    }
}