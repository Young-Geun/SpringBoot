package choi.web.springboot.controller;

import choi.web.springboot.domain.Item;
import choi.web.springboot.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(value = "mac")
class ItemControllerTest {

    @Autowired
    private ItemService itemService;

    @Test
    void save() {
        Item item = new Item();
        item.setItemName("Item_1");
        item.setItemPrice(1000);
        item.setItemQuantity(1);
        itemService.save(item);

        Item findItem = itemService.findById(item.getItemId());

        assertEquals(item, findItem);
    }

    @Test
    void update() {
        Item updateItem = new Item();
        updateItem.setItemName("MacBook");
        updateItem.setItemPrice(20000);
        updateItem.setItemQuantity(1);
        itemService.update(1L, updateItem);

        Item findItem = itemService.findById(1L);

        assertEquals(findItem.getItemName(), updateItem.getItemName());
        assertEquals(findItem.getItemPrice(), updateItem.getItemPrice());
        assertEquals(findItem.getItemQuantity(), updateItem.getItemQuantity());
    }
}