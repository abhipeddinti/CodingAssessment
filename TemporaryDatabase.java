import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TemporaryDatabase implements Database {
    private final Map<String, Entity> storage = new ConcurrentHashMap<>();

    @Override
    public void save(Entity entity) throws Exception {
        storage.put(entity.getId(), entity);
    }

    @Override
    public Entity get(String id) throws Exception {
        return storage.get(id);
    }

    @Override
    public void remove(String id) throws Exception {
        storage.remove(id);
    }

    @Override
    public void removeAll() throws Exception {
        storage.clear();
    }
}
