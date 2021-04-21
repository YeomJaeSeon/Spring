package food.studentsBestt.item.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public Item findById(Long id){
        return store.get(id);
    }
    public List findAll(){
        return new ArrayList(store.values()); // values는 반환값이 Collection인터페이스임
    }
    public void update(Long id, Item updateParam){
        Item item = store.get(id);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setAmount(updateParam.getAmount());
    }
    public void delete(Long id){
        store.remove(id);
    }
    public void clearStore(){
        store.clear();
    }
}
