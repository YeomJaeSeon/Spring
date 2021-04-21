package food.studentsBestt.food.domain;

import org.springframework.stereotype.Repository;

import java.security.KeyStore;
import java.util.*;

@Repository
public class FoodRepository {
    private static final Map<Long, Food> store = new HashMap<>();
    private static Long sequence = 0L;

    public Food save(Food food){
        food.setId(++sequence);
        store.put(food.getId(), food);
        return food;
    }

    public Food findById(Long id){
        return store.get(id);
    }

    public List findAll(){
        return new ArrayList(store.values());
    }

    public void delete(Long id){
        store.remove(id);
    }

    public int like(Long id){
        Food food = findById(id);
        int likesNum = food.getLikesNum();
        food.setLikesNum(++likesNum);
        return food.getLikesNum();
    }
    public int hate(Long id){
        Food food = findById(id);
        int hatesNum = food.getHatesNum();
        food.setHatesNum(++hatesNum);
        return food.getHatesNum();
    }

    public void clearStore(){
        store.clear();
    }

}
