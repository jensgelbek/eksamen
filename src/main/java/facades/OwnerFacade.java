/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;
import dtos.OwnerDTO;
import entities.Owner;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author PC
 */
public class OwnerFacade {
     private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    public static OwnerFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();

        }
        return instance;
    }
    
    public List<OwnerDTO> getAll(){
        EntityManager em=emf.createEntityManager();
        List<OwnerDTO> ownerDtos=new ArrayList<>();
        List<Owner> owners=new ArrayList<>();
        try{
            em.getTransaction().begin();
            owners=em.createQuery("SELECT o FROM Owner o", Owner.class).getResultList();
            for(Owner owner:owners){
                ownerDtos.add(new OwnerDTO(owner));
            }
            
        }catch (Exception e) {
            throw new WebApplicationException("Problems with the database", 400);
        }
        finally{
            em.close();
        }
        return ownerDtos;
    }
}
