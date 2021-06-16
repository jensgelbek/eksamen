/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.DogDTO;
import entities.Dog;
import entities.Owner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
    
    public DogDTO create(DogDTO dogDTO){
        System.out.println("dogdto to commit"+dogDTO);
        EntityManager em=emf.createEntityManager();
        Dog dog=dogDTO.getDog();
        System.out.println("dog to commit"+dog);
        try{
            em.getTransaction().begin();
            Owner owner=em.find(Owner.class, dogDTO.getOwnerId());
            System.out.println("found owner"+owner);
            owner.addDog(dog);
            em.getTransaction().commit();
                    
        }finally{
            em.close();
            return new DogDTO(dog);
        }
                
    }
    
    
    
}
/*
 public BookDTO create(BookDTO bookDTO){
        EntityManager em=emf.createEntityManager();
        Book book=bookDTO.getBook();
        
        try {
            em.getTransaction().begin();
            Library library=em.find(Library.class, bookDTO.getLibraryId());
            library.addBook(book);
            em.getTransaction().commit();
            
        } catch (Exception e) {
        }finally{
            em.close();
            return new BookDTO(book);
        }
    }
*/