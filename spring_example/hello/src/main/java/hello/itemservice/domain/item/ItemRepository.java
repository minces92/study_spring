package hello.itemservice.domain.item;

import jdk.dynalink.beans.StaticClass;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    // [아이템 저장소]
    // 실제는 해쉬맵 쓰면 안되며, static으로 개발한 부분 집중
    // 해쉬맵을 사용하면 안되는이유 => static or singletone은 동시에 같이 사용하기 때문에
    // 실무에서는 ConcurrentHashMap<> 사용해야함
    private static final Map<Long, Item> store = new HashMap<>();
    // 싱글톤으로 등록될거기 때문에 static 일반적으로 안해도 되지만
    // 아래처럼 직접 등록한 경우 필요함
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
        // store.values() 반환해도 되지만 감싸는게 외부 변경에 안전함
    }

    public void update(Long itemId, Item updataeParam){
        Item findItem = findById(itemId);

        findItem.setItemName(updataeParam.getItemName());
        findItem.setPrice(updataeParam.getPrice());
        findItem.setQuantity(updataeParam.getQuantity());
        findItem.setPrice(updataeParam.getPrice());

        store.put(itemId, findItem);
    }

    public void clearStore() {
        store.clear();
    }
}
