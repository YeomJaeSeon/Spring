package food.studentsBestt.food.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FoodRepositoryTest {
    FoodRepository foodRepository = new FoodRepository();

    @AfterEach
    void afterEach(){
        foodRepository.clearStore();
    }

    @Test
    @DisplayName("음식 등록")
    void save(){
        Food food = new Food("햄버거");

        foodRepository.save(food);
        Food findFood = foodRepository.findById(food.getId());

        assertThat(findFood).isEqualTo(food);

    }

    @Test
    @DisplayName("음식 삭제")
    void delete(){
        Food food = new Food("피자");
        Food food1 = new Food("햄버거");
        foodRepository.save(food);
        foodRepository.save(food1);

        foodRepository.delete(food.getId());
        List foods = foodRepository.findAll();

        assertThat(foods.size()).isEqualTo(1);
        assertThat(foods).contains(food1);
    }

    @Test
    @DisplayName("좋아요")
    void like(){
        Food food = new Food("햄벅");

        foodRepository.save(food);

        foodRepository.like(food.getId());
        int like = foodRepository.like(food.getId());

        assertThat(like).isEqualTo(2);

    }

    @Test
    @DisplayName("싫어요")
    void hate(){
        Food food = new Food("시금치");
        foodRepository.save(food);

        int hate = foodRepository.hate(food.getId());

        assertThat(hate).isEqualTo(1);

    }


}