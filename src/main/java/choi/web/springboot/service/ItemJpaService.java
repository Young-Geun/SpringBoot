package choi.web.springboot.service;

import choi.web.springboot.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemJpaService {

    private final EntityManager em;

    public Item findById(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Transactional
    public void update(Long itemId, Item updateItem) {
        /*
            별도의 UPDATE문 호출이 없어도
            트랜잭션이 끝나는 시점에 값에 대한 변화가 있으면 UPDATE를 실행해준다.
         */
        Item item = em.find(Item.class, itemId);
        item.setItemName(updateItem.getItemName());
        item.setItemPrice(updateItem.getItemPrice());
        item.setItemQuantity(updateItem.getItemQuantity());
    }

}
