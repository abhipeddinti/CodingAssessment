public interface Database {
    void save(Entity entity) throws Exception;
    Entity get(String id) throws Exception;
    void remove(String id) throws Exception;
    void removeAll() throws Exception;
}
