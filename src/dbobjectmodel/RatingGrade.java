package dbobjectmodel;

import java.time.LocalDateTime;

public class RatingGrade {

    private final int id;
    private final String grade;
    private final int gradeNumber;
    private final LocalDateTime date;

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
}
