/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Dog;
import java.util.Date;

import java.util.Objects;

/**
 *
 * @author PC
 */
public class DogDTO {
    private Long id;
    private String name;
    private String breed;
    private String image;
    private String gender;
    private Date birthDate;
    private Long OwnerId;
    private String OwnerName;

    public DogDTO(Long id, String name, String breed, String image, String gender, Date birthDate, Long OwnerId, String OwnerName) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.image = image;
        this.gender = gender;
        this.birthDate = birthDate;
        this.OwnerId = OwnerId;
        this.OwnerName = OwnerName;
    }
    
    public DogDTO(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.breed = dog.getBreed();
        this.image = dog.getImage();
        this.gender = dog.getGender();
        this.birthDate = dog.getBirthDate();
        if (dog.getOwner() != null) {
            this.OwnerId = dog.getOwner().getId();
            this.OwnerName = dog.getOwner().getName();
        }
    }   
    
    public Dog getDog(){
        return new Dog(this.getId(),this.getName(),this.getBreed(),this.getImage(),this.getGender(),this.getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Long OwnerId) {
        this.OwnerId = OwnerId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String OwnerName) {
        this.OwnerName = OwnerName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.breed);
        hash = 71 * hash + Objects.hashCode(this.image);
        hash = 71 * hash + Objects.hashCode(this.gender);
        hash = 71 * hash + Objects.hashCode(this.birthDate);
        hash = 71 * hash + Objects.hashCode(this.OwnerId);
        hash = 71 * hash + Objects.hashCode(this.OwnerName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DogDTO other = (DogDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DogDTO{" + "id=" + id + ", name=" + name + ", breed=" + breed + ", image=" + image + ", gender=" + gender + ", birthDate=" + birthDate + ", OwnerId=" + OwnerId + ", OwnerName=" + OwnerName + '}';
    }
    
    
}
