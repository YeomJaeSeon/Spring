package restudy.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;
    
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    
    public Item findById(Long id){
        return store.get(id);
    }
    
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }
    public void update(Long id, Item updateParam){
        Item item = findById(id);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }
    public void delete(Long id){
        store.remove(id);
    }
    // Test를위한
    public void clearStore(){
        store.clear();
    }
}
