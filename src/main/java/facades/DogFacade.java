/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.DogDTO;
import entities.Dog;
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
public class DogFacade {
    
    private static DogFacade instance;
    private static EntityManagerFactory emf;

    public static DogFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DogFacade();

        }
        return instance;
    }
    
    public DogDTO create(DogDTO dogDTO)throws WebApplicationException{
        EntityManager em=emf.createEntityManager();
        Dog dog=dogDTO.getDog();
        try{
            em.getTransaction().begin();
            Owner owner=em.find(Owner.class, dogDTO.getOwnerId());
            System.out.println("found owner"+owner);
            owner.addDog(dog);
            em.getTransaction().commit();
               return new DogDTO(dog);       
        }catch(Exception e){
            
            System.out.println("exception -----------------------------");
            throw new WebApplicationException("could not create dog",400);
        }
        finally{
            em.close();
            
        }
              
    }
    
    public List<DogDTO> getAll(){
        EntityManager em=emf.createEntityManager();
        List<DogDTO> dogDtos=new ArrayList<>();
        List<Dog> dogs=new ArrayList<>();
        try{
            em.getTransaction().begin();
            dogs=em.createQuery("SELECT d FROM Dog d", Dog.class).getResultList();
            for(Dog dog:dogs){
                dogDtos.add(new DogDTO(dog));
            }
            
        }catch (Exception e) {
            throw new WebApplicationException("Problems with the database", 400);
        }
        finally{
            em.close();
        }
        return dogDtos;
    }
    
 public void delete(Long dogId) throws WebApplicationException{
        EntityManager em = emf.createEntityManager();
        try {
           em.getTransaction().begin();
           Dog foundDog=em.find(Dog.class, dogId);
           em.remove(foundDog);
           em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("Book do not exist", 400);
        } finally {
            em.close();
        }
    }
    
}