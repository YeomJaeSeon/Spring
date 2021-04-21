package food.studentsBestt.item.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("아이템 등록")
    void save(){
        Item item = new Item("itemA", 10000, 10);

        itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());

        assertThat(item).isEqualTo(findItem);
    }

    @Test
    @DisplayName("모든 상품 찾기")
    void findAll(){
        Item item = new Item("itemA", 1000, 10);
        Item item2 = new Item("itemB", 2000, 20);
        itemRepository.save(item);
        itemRepository.save(item2);

        List items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item, item2);
    }

    @Test
    @DisplayName("상품 수정")
    void update(){
        Item item = new Item("itemA", 1000, 10);
        itemRepository.save(item);
        Long id = item.getId();
        Item updateParam = new Item("itemAA", 10000, 100);

        itemRepository.update(id, updateParam);

        assertThat(updateParam.getItemName()).isEqualTo(item.getItemName());
        assertThat(updateParam.getPrice()).isEqualTo(item.getPrice());
        assertThat(updateParam.getAmount()).isEqualTo(item.getAmount());
    }

    @Test
    @DisplayName("아이템 삭제")
    void delete(){
        Item item = new Item("itemA", 10, 10);
        Item item2 = new Item("itemB", 1, 1);
        itemRepository.save(item);
        itemRepository.save(item2);
        Long id = item.getId();

        itemRepository.delete(id);
        List items = itemRepository.findAll();

        assertThat(items).contains(item2);
        assertThat(items.size()).isEqualTo(1);

    }


}