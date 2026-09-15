package com.qeats.order;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="food_orders")
public class FoodOrder {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private Long restaurantId;
 @Column(nullable=false) private String customerName;
 @Column(nullable=false) private String item;
 @Column(nullable=false) private int quantity;
 @Column(nullable=false) private BigDecimal totalAmount;
 @Column(nullable=false) private String status;
 protected FoodOrder(){}
 public FoodOrder(Long restaurantId,String customerName,String item,int quantity,BigDecimal totalAmount,String status){this.restaurantId=restaurantId;this.customerName=customerName;this.item=item;this.quantity=quantity;this.totalAmount=totalAmount;this.status=status;}
 public Long getId(){return id;} public Long getRestaurantId(){return restaurantId;} public String getCustomerName(){return customerName;} public String getItem(){return item;} public int getQuantity(){return quantity;} public BigDecimal getTotalAmount(){return totalAmount;} public String getStatus(){return status;}
}
