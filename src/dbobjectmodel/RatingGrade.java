package dbobjectmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RatingGrade {

    private final int id;
    private final String grade;
    private final int gradeNumber;
    private final Date date;
    private final List<Rating> ratings = new ArrayList<>();

    public RatingGrade(int id, String grade, int gradeNumber, Date date) {
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

    public Date getDate() {
        return date;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }
}
