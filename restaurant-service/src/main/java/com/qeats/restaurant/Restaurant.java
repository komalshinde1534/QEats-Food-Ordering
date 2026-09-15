package com.qeats.restaurant;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="restaurants")
public class Restaurant {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String name;
    @Column(nullable=false) private String cuisine;
    @Column(nullable=false) private String city;
    @Column(nullable=false) private boolean available;
    @ElementCollection(fetch=FetchType.EAGER) @CollectionTable(name="restaurant_menu", joinColumns=@JoinColumn(name="restaurant_id"))
    @Column(name="item") private List<String> menu;
    protected Restaurant() {}
    public Restaurant(String name,String cuisine,String city,boolean available,List<String> menu){this.name=name;this.cuisine=cuisine;this.city=city;this.available=available;this.menu=menu;}
    public Long getId(){return id;} public String getName(){return name;} public String getCuisine(){return cuisine;} public String getCity(){return city;} public boolean isAvailable(){return available;} public List<String> getMenu(){return menu;}
}
