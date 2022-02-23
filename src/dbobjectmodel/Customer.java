package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final int id;
    private final int areaId;
    private final String name;
    private final String password;
    private Area area;
    private final List<Rating> ratings = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

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

    public void setArea(Area area) {
        this.area = area;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Area getArea() {
        return area;
    }
}
