public class MyEntity implements Entity {
    private final String id;
    private final String data;

    public MyEntity(String id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}
