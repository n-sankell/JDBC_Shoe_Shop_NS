package dbobjectmodel;

public class Label {

    private final int id;
    private final String name;

    public Label(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
