package herman.test.model;

public enum EventType {
    RETRIEVE(1),
    STORE(2);

    private int id;
    EventType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
