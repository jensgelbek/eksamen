/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Owner;
import java.util.Objects;

/**
 *
 * @author PC
 */
public class OwnerDTO {
    private Long id;
    private String name;

    public OwnerDTO() {
    }

    public OwnerDTO(String name) {
        this.name = name;
    }
    
    public OwnerDTO(Owner owner){
        this.id=owner.getId();
        this.name=owner.getName();
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

    @Override
    public int hashCode() {
        int hash = 7;
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
        final OwnerDTO other = (OwnerDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" + "id=" + id + ", name=" + name + '}';
    }
 
    
}
