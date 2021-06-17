/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import dtos.DogDTO;
import dtos.RenameMeDTO;
import entities.Dog;
import entities.Owner;
import entities.renameme.RenameMe;
import entities.renameme.RenameMeRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.EMF_Creator;

/**
 *
 * @author PC
 */
public class DogFacadeTest {
   
    
    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();
    private static final DogFacade Facade = DogFacade.getInstance(EMF);
    private static Dog d1,d2,d3;
    private static Owner o1,o2;
    
    @BeforeAll
    public static void setUpClass() {
    }

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

            em.persist(o1);
            em.persist(o2);
            o1.addDog(d1);
            o1.addDog(d2);
            o2.addDog(d3);
                    
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public DogFacadeTest() {
    }

    

    @Test
    public void testCreate() {
        DogDTO expected = new DogDTO(null, "ny", "", "","",new Date(), o1.getId(), o1.getName());
        DogDTO actual = Facade.create(expected);
        assertEquals(expected.getName(), actual.getName());
        assertNotNull(actual.getId());
    }

    @Test
    public void testEdit() {
        EntityManager em = EMF.createEntityManager();
        DogDTO expected = new DogDTO(d1);expected.setName("evil");
        Facade.edit(expected);
        Dog actual = em.find(Dog.class,d1.getId());
        
        assertEquals(expected.getName(), actual.getName());
        em.close();
    }

    @Test
    public void testGet() {
        Long expected = d1.getId();
        DogDTO actual = Facade.get(expected);
        assertEquals(expected, actual.getId());
        assertEquals(d1.getName(), actual.getName());

    }

    @Test
    public void testGetAll() {
        

        List<DogDTO> actual = Facade.getAll();
        assertEquals(3, actual.size());
    }

    @Test
    public void testDelete() {
        Facade.delete(d1.getId());
        List<DogDTO> actual = Facade.getAll();
        assertEquals(2, actual.size());
        assertThrows(WebApplicationException.class, ()->{Facade.get(d1.getId());});
    }
}
