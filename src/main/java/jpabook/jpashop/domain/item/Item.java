package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "dType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직
    // stock 재고 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    
    // stock 감소
    public void removeStock(int quantity) {
        int reststock = this.stockQuantity - quantity;
        if (reststock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = reststock;
    }

    public void change(String name, int price, int stockQuantity) {
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.price = price;
    }
}
