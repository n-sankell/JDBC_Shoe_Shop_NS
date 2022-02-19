package dbobjectmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RatingGrade {

    private final int id;
    private final String grade;
    private final int gradeNumber;
    private final LocalDateTime date;
    private final List<Rating> ratings = new ArrayList<>();

    public RatingGrade(int id, String grade, int gradeNumber, LocalDateTime date) {
        this.id = id;
        this.grade = grade;
        this.gradeNumber = gradeNumber;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public int getGradeNumber() {
        return gradeNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }
}
