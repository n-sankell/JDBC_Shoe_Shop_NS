package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class ShoeColor {

    private final int id;
    private final String name;
    private final List<CompleteShoe> shoes = new ArrayList<>();

    public ShoeColor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CompleteShoe> getShoes() {
        return shoes;
    }

    public void addShoe(CompleteShoe shoe) {
        shoes.add(shoe);
    }
}
