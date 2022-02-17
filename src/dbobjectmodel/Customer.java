package dbobjectmodel;

public class Customer {
    private final int id;
    private final int areaId;
    private final String name;
    private final String password;

    public Customer(int id, int areaId, String name, String password) {
        this.id = id;
        this.areaId = areaId;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public int getAreaId() {
        return areaId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
    
}
