package choi.web.springboot.service;

import choi.web.springboot.domain.Item;
import choi.web.springboot.domain.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemQueryDslService {

    private final EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public ItemQueryDslService(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public Item findById(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public List<Item> findAll(Item item) {
        QItem qItem = new QItem("i"); // i는 Item의 Alias
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(item.getItemName())) {
            builder.and(qItem.itemName.like("%" + item.getItemName() + "%"));
        }
        if (item.getItemPrice() != null) {
            builder.and(qItem.itemPrice.loe(item.getItemPrice())); // this <= right
        }
        if (item.getItemQuantity() != null) {
            builder.and(qItem.itemQuantity.loe(item.getItemQuantity())); // this <= right
        }

        return jpaQueryFactory
                .select(qItem)
                .from(qItem)
                .where(builder)
                .fetch();
    }

    public List<Item> findAll_V2(Item item) {
        QItem qItem = new QItem("item"); // i는 Item의 Alias

        return jpaQueryFactory
                .select(qItem)
                .from(qItem)
                .where(makeName(item.getItemName()), makePrice(item.getItemPrice()), makeQuantity(item.getItemQuantity()))
                .fetch();
    }

    private BooleanExpression makeName (String itemName) {
        if (StringUtils.hasText(itemName)) {
            return QItem.item.itemName.like("%" + itemName + "%");
        }
        return null;
    }

    private BooleanExpression makePrice (Integer itemPrice) {
        if (itemPrice != null) {
            return QItem.item.itemPrice.loe(itemPrice);
        }
        return null;
    }

    private BooleanExpression makeQuantity (Integer itemQuantity) {
        if (itemQuantity != null) {
            return QItem.item.itemQuantity.loe(itemQuantity);
        }
        return null;
    }

}
