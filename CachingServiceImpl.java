import java.util.LinkedHashMap;
import java.util.Map;

public class CachingServiceImpl implements CachingService {
    private final int maxSize;
    private final Map<String, Entity> cache;
    private final Database database;

    public CachingServiceImpl(int maxSize, Database database) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be greater than 0");
        }
        this.maxSize = maxSize;
        this.database = database;

        // Initialize LinkedHashMap with initialCapacity = maxSize and accessOrder = true (LRU cache)
        this.cache = new LinkedHashMap<String, Entity>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Entity> eldest) {
                if (size() > CachingServiceImpl.this.maxSize) {
                    // Evict the least recently used entry to the database
                    try {
                        database.save(eldest.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public void add(Entity e) throws Exception {
        cache.put(e.getId(), e);
    }

    @Override
    public void remove(Entity e) throws Exception {
        cache.remove(e.getId());
        database.remove(e.getId());
    }

    @Override
    public void removeAll() throws Exception {
        cache.clear();
        database.removeAll();
    }

    @Override
    public Entity get(Entity e) throws Exception {
        Entity entity = cache.get(e.getId());
        if (entity != null) {
            return entity;
        }
        // Try to get from database if not in cache
        entity = database.get(e.getId());
        if (entity != null) {
            cache.put(e.getId(), entity);
        }
        return entity;
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
