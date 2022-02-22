package gui;

import dbobjectmodel.BaseProduct;
import dbobjectmodel.Comment;
import dbobjectmodel.Rating;

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
    private CustomButton rate;
    private BaseProduct product;
    private String average;
    private List<Rating> ratings;
    private List<Comment> comments;

    public RateAndCommentFrame(BaseProduct product, String average, List<Rating> ratings, List<Comment> comments) {
        this.product = product;
        this.average = average;
        this.ratings = ratings;
        this.comments = comments;
        setVisible(true);
        setUp();
        addElements();
    }

    private void setUp() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(400,500);
        setLocation(500,150);
        setResizable(false);
        title = new JLabel(product.getLabel().getName()+" "+product.getName());
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        title.setForeground(Colors.TEXT);
        averageScore = new JLabel();
        averageScore.setForeground(Colors.TEXT);
        averageScore.setFont(getFont().deriveFont(Font.BOLD,26f));
        rate = new CustomButton("Rate");
        rate.addActionListener(this);
        GridLayout ratingsGrid = new GridLayout(ratings.size(), 1);
        ratingsBg = new JPanel();
        ratingsBg.setBackground(Colors.BG_BRIGHT);
        ratingsBg.setLayout(ratingsGrid);
        ratingsGrid.setVgap(10);
        bg = new JPanel();
        bg.setBackground(Colors.BG_BRIGHT);
        GridBagLayout gb = new GridBagLayout();
        bg.setLayout(gb);
        bg.setPreferredSize(new Dimension(400,500));
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
        bg.add(rate,gc);
        add(bg);
    }

    private void setTotalText() {
        averageScore.setText("Average score: " + average);
    }

    private void addRatings() {
        for (Rating rating : ratings) {
            ratingsBg.add(new CustomJLabel(rating.getGrade().getGrade() +" "+rating.getGrade().getGradeNumber()+" "+rating.getCustomer().getName()+", "+rating.getCustomer().getArea().getName()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
