package restudy.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// ctrl shift t로 쉽게 테스트코드작성

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("item1", 1000, 30);

        Item saveItem = itemRepository.save(item);

        assertThat(saveItem).isEqualTo(item);
    }

    @Test
    void findAll(){
        Item item = new Item("item1", 1000, 10);
        Item item2 = new Item("item2", 2000, 20);
        itemRepository.save(item);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item, item2);
    }

    @Test
    void findById(){
        Item item = new Item("item", 1000, 10);
        itemRepository.save(item);
        Long savedId = item.getId();

        Item savedItem = itemRepository.findById(savedId);

        assertThat(savedItem).isEqualTo(item);
    }

    @Test
    void update(){
        Item item = new Item("item", 1000, 10);
        Item updateParam = new Item("itemU", 2000, 30);
        itemRepository.save(item);
        Long updateId = item.getId();

        itemRepository.update(updateId, updateParam);

        assertThat(item.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(item.getQuantity()).isEqualTo(updateParam.getQuantity());
        assertThat(item.getPrice()).isEqualTo(updateParam.getPrice());
    }

    @Test
    void delete(){
        Item item = new Item("item", 100, 20);
        itemRepository.save(item);
        Long deleteId = item.getId();

        itemRepository.delete(deleteId);
        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(0);
    }
}