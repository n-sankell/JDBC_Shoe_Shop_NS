package dbobjectmodel;

import java.time.LocalDateTime;

public class OutOfStock {

    private final int id;
    private final int shoeId;
    private final LocalDateTime date;

    public OutOfStock(int id, int shoeId, LocalDateTime date) {
        this.id = id;
        this.shoeId = shoeId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getShoeId() {
        return shoeId;
    }

    public LocalDateTime getDate() {
        return date;
    }

}
