public class Main {
    public static void main(String[] args) {
        try {
            Database database = new TemporaryDatabase(); // Use in-memory database
            int maxCacheSize = 5; // Configurable max size
            CachingService cacheService = new CachingServiceImpl(maxCacheSize, database);

            Entity e1 = new MyEntity("1", "E1 Data");
            Entity e2 = new MyEntity("2", "E2 Data");
            Entity e3 = new MyEntity("3", "E3 Data");
            Entity e4 = new MyEntity("4", "E4 Data");
            Entity e5 = new MyEntity("5", "E5 Data");
            Entity e6 = new MyEntity("6", "E6 Data");

            // Adding entities to cache
            cacheService.add(e1);
            cacheService.add(e2);
            cacheService.add(e3);
            cacheService.add(e4);
            cacheService.add(e5);


            // Accessing e1,e2,e3, and e4 to update LRU status
            cacheService.get(e1);
            cacheService.get(e2);
            cacheService.get(e3);
            cacheService.get(e4);


            // Adding e6 and evicting e5 which is least used entity
            cacheService.add(e6);

            // Trying to retrieve e5
            Entity retrievedE5 = cacheService.get(e5);
            if (retrievedE5 != null) {
                System.out.println("Retrieved e5: " + ((MyEntity) retrievedE5).getData());
            } else {
                System.out.println("e5 not found.");
            }

            // Removing e1 from cache and database
            cacheService.remove(e1);

            // Clearing the cache
            cacheService.clear();

            // Removing all entities from cache and database
            cacheService.removeAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
