public interface CachingService {
    void add(Entity e) throws Exception;      // Adds e1 to internal cache.
    void remove(Entity e) throws Exception;   // Removes e1 from the internal cache and database.
    void removeAll() throws Exception;         // Removes all the Entities from internal cache and database.
    Entity get(Entity e) throws Exception;    // Returns an object from the internal cache or from database.
    void clear();                              // Clears the internal cache. No impact on entries in database.
}
