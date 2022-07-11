package choi.web.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemId;

    @Column(name = "item_name") // 생략해도 카멜케이스를 지원해준다.
    String itemName;

    Integer itemPrice;

    Integer itemQuantity;

}
