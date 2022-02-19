package dbobjectmodel;

import java.time.LocalDateTime;

public class OutOfStock {

    private final int id;
    private final int shoeId;
    private final LocalDateTime date;
    private CompleteShoe shoe;

    public OutOfStock(int id, int shoeId, LocalDateTime date) {
        this.id = id;
        this.shoeId = shoeId;
        this.date = date;
    }

    public CompleteShoe getShoe() {
        return shoe;
    }

    public void setShoe(CompleteShoe shoe) {
        this.shoe = shoe;
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
