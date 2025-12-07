package dev.bruno.PersonRegistry.model;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Table(name = "person")
@Entity
public class PersonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "adress_id")
    private AdressModel adress;

    public PersonModel() {}

    public PersonModel(Long id, String name, String email, AdressModel adress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adress = adress;
    }

    public PersonModel(String name, String email, AdressModel adress) {
        this.name = name;
        this.email = email;
        this.adress = adress;
    }

    public PersonModel(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public PersonModel(String name, AdressModel adress) {
        this.name = name;
        this.adress = adress;
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

   public String getEmail() {
        return email;
   }

   public AdressModel getAdress() {
        return adress;
   }

   public void setAdress(AdressModel adress) {
        this.adress = adress;
   }

   public void setEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
   }

   public AdressModel getAdressModel() {
        return adress;
   }

   public void setAdressModel(AdressModel adress) {
        this.adress = adress;
   }

}
