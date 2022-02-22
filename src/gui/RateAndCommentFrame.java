package gui;

import dbobjectmodel.BaseProduct;
import dbobjectmodel.Comment;
import dbobjectmodel.Rating;
import listeners.RateAndCommentSubmitListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RateAndCommentFrame extends JFrame implements ActionListener {

    private JLabel title;
    private JLabel averageScore;
    private JPanel bg;
    private JPanel ratingsBg;
    private JPanel commentsBg;
    private JPanel scrollablePanel;
    private String commentText;
    private CustomButton submit;
    private JTextField commentField;
    private JScrollPane scrollPane;
    private BaseProduct product;
    private int userId;
    private String average;
    private List<Rating> ratings;
    private List<Comment> comments;
    private RateAndCommentSubmitListener submitListener;

    public RateAndCommentFrame(BaseProduct product, String average, List<Rating> ratings, List<Comment> comments, int userId) {
        this.product = product;
        this.average = average;
        this.ratings = ratings;
        this.comments = comments;
        this.userId = userId;
        setVisible(true);
        setUp();
        addElements();
    }

    private void setUp() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(900,1000);
        setLocation(500,150);
        setResizable(false);
        title = new JLabel(product.getLabel().getName()+" "+product.getName());
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        title.setForeground(Colors.TEXT);
        averageScore = new JLabel();
        averageScore.setForeground(Colors.TEXT);
        averageScore.setFont(getFont().deriveFont(Font.BOLD,26f));
        submit = new CustomButton("Submit");
        submit.addActionListener(this);
        GridLayout ratingsGrid = new GridLayout(ratings.size(), 1);
        ratingsBg = new JPanel();
        ratingsBg.setBackground(Colors.BG_BRIGHT);
        ratingsBg.setLayout(ratingsGrid);
        ratingsGrid.setVgap(10);
        GridLayout commentGrid = new GridLayout(comments.size(), 1);
        commentsBg = new JPanel();
        commentsBg.setBackground(Colors.BG_BRIGHT);
        commentsBg.setLayout(commentGrid);
        scrollablePanel = new JPanel();
        scrollablePanel.setBackground(Colors.BG_BRIGHT);
        bg = new JPanel();
        bg.setBackground(Colors.BG_BRIGHT);
        GridBagLayout gb = new GridBagLayout();
        bg.setLayout(gb);
        setTotalText();
    }

    private void addElements() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.gridx = 1;
        bg.add(title,gc);
        gc.gridy = 1;
        addRatings();
        gc.gridy = 2;
        bg.add(ratingsBg,gc);
        gc.gridy = 3;
        bg.add(averageScore,gc);
        gc.gridy = 4;
        bg.add(submit,gc);
        add(bg);
    }

    private void setTotalText() {
        averageScore.setText("Average score: " + average);
    }

    private void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    private void addRatings() {
        for (Rating rating : ratings) {
            ratingsBg.add(new CustomJLabel(rating.getGrade().getGrade() +" "+rating.getGrade().getGradeNumber()+" "+rating.getCustomer().getName()+", "+rating.getCustomer().getArea().getName()));
        }
    }

    public void setSubmitListener(RateAndCommentSubmitListener submitListener) {
        this.submitListener = submitListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            submitListener.submissionOccurred(1, commentText, userId, product.getId());
        }
    }
}
