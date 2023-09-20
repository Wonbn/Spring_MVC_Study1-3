package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

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
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 5);
        Item item3 = new Item("itemC", 30000, 1);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //when
        List<Item> findAllResult = itemRepository.findAll();

        //then
        assertThat(findAllResult).contains(item1);
        assertThat(findAllResult).contains(item2);
        assertThat(findAllResult).contains(item3);
        assertThat(findAllResult.size()).isEqualTo(3);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("itemA", 10000, 10);

        itemRepository.save(item);
        Long itemId = item.getId();

        //when
        Item updateItem = new Item("updateItem", 15000, 50);

        itemRepository.update(itemId, updateItem);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}