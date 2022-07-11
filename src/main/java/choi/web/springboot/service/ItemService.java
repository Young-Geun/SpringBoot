package choi.web.springboot.service;

import choi.web.springboot.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final EntityManager em;

    public Item findById(Long itemId) {
        return em.find(Item.class, itemId);
    }

    public Item save(Item item) {
        em.persist(item);
        return item;
    }

}
