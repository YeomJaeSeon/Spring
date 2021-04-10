package restudy.itemservice.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    // 테스트마다 store 데이터 clear
    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("item1", 10000, 30);

        Item save = itemRepository.save(item);

        assertThat(item).isEqualTo(save);
    }

    @Test
    void findAll(){
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void findById(){
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        Item findItem = itemRepository.findById(item1.getId());

        assertThat(findItem).isEqualTo(item1);
    }

    @Test
    void update(){
        Item item = new Item("item1", 10000, 10);
        itemRepository.save(item);

        Item updateParam = new Item("파브르곤충기", 10001, 300);
        itemRepository.update(item.getId(), updateParam);

        assertThat(item.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(item.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(item.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
