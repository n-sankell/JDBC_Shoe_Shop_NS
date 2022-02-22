package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class BaseProduct {

    private final int id;
    private final int labelId;
    private final String name;
    private Label label;
    private final List<Category> categories = new ArrayList<>();
    private final List<CompleteShoe> shoes = new ArrayList<>();
    private final List<Rating> ratings = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();

    public BaseProduct(int id, int labelId, String name) {
        this.id = id;
        this.labelId = labelId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getLabelId() {
        return labelId;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addShoe(CompleteShoe category) {
        shoes.add(category);
    }

    public List<CompleteShoe> getShoes() {
        return shoes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public List<Comment> getComments(){
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
