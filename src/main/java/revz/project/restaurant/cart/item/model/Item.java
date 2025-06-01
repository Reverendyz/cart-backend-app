package revz.project.restaurant.cart.item.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_items")
public class Item {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column
  private String name;

  @Column
  private Double price;

  @Column
  private Integer quantity;

  @CreationTimestamp
  private Instant creationDatetime;

  @UpdateTimestamp
  private Instant updateDatetime;

  public Item(){}

  public Item(String name, Double price, Integer quantity, Instant creationDatetime, Instant updateDatetime) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.creationDatetime = creationDatetime;
    this.updateDatetime = updateDatetime;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Instant getCreationDatetime() {
    return creationDatetime;
  }

  public void setCreationDatetime(Instant creationDatetime) {
    this.creationDatetime = creationDatetime;
  }

  public Instant getUpdateDatetime() {
    return updateDatetime;
  }

  public void setUpdateDatetime(Instant updateDatetime) {
    this.updateDatetime = updateDatetime;
  }

  
}
