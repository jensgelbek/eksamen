/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import entities.Owner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author PC
 */
public class SetupData{
       
     public static boolean  SetupData() {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        //Owners
         Owner o1 = new Owner("Jens Gelbek", "Lyngby Hovedgade 22, 1tv","2800 Lyngby","28534556");
         Owner o2 = new Owner("Peter Rambeck", "Lundtoftegade 102, 1tv","2800 Lyngby","65347123");
         Owner o3 = new Owner("Tobias Zimmerman", "Lyngby Hovedgade 5, 3tv","2800 Lyngby","2854235");
         Owner o4 = new Owner("Rasmus Ditlev", "Engelsborgvej 23, 4tv","2800 Lyngby","67534556");
         
        try {
            em.getTransaction().begin();
            em.persist(o1);
            em.persist(o2);
            em.persist(o3);
            em.persist(o4);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }

    }
}
/*
 this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.phone = phone;
        this.dogs = dogs;
*/