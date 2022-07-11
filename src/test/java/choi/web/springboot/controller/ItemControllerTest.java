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
}