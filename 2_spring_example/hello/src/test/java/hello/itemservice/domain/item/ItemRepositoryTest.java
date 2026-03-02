package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertThat(findItem).isEqualTo(savedItem);

    }



    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        Item itemB = new Item("itemB", 20000, 20);

        //when
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        //then
        List<Item> items = itemRepository.findAll();


        assertThat(items).contains(itemA, itemB);
        assertThat(items).hasSize(2);

    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 10000, 10);
        itemRepository.save(itemA);
        //when
        Item updatedItem = new Item("updatedItem", 20000, 20);
        itemRepository.update(itemA.getId(), updatedItem);

        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(updatedItem).isNotEqualTo(findItem);
    }

}