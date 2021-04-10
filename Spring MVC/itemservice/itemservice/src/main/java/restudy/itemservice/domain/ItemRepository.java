package restudy.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    // 저장
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public void update(Long id, Item updateParam){
        Item targetItem = findById(id);
        targetItem.setItemName(updateParam.getItemName());
        targetItem.setPrice(updateParam.getPrice());
        targetItem.setQuantity(updateParam.getQuantity());
    }

    public void delete(Long id){
        store.remove(id);
    }

    public void clearStore(){
        store.clear();
    }

}
