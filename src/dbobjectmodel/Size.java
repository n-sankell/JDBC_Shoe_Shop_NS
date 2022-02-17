package dbobjectmodel;

public class Size {

    private final int id;
    private final double size;

    public Size(int id, double size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public double getSize() {
        return size;
    }
}
