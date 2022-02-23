package gui;

import dbobjectmodel.BaseProduct;
import dbobjectmodel.Comment;
import dbobjectmodel.Rating;
import listeners.RateAndCommentSubmitListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class RateAndCommentFrame extends JFrame implements ActionListener {

    private JLabel title;
    private JLabel averageScore;
    private JPanel bg;
    private JPanel ratingsBg;
    private JPanel commentsBg;
    private int gradeId = 0;
    private int counter = 0;
    private CustomButton submit;
    private JTextField commentField;
    private JPanel splitPane;
    private JMenuItem euforisk;
    private JMenuItem mycketnojd;
    private JMenuItem nojd;
    private JMenuItem ganskanojd;
    private JMenuItem missnojd;
    private final BaseProduct product;
    private final int userId;
    private final String average;
    private final List<Rating> ratings;
    private final List<Comment> comments;
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
        setSize(1100,800);
        setLocation(500,150);
        setResizable(false);
        title = new JLabel(product.getLabel().getName()+" "+product.getName());
        title.setFont(getFont().deriveFont(Font.BOLD,36f));
        title.setForeground(Colors.TEXT);
        averageScore = new JLabel();
        averageScore.setForeground(Colors.TEXT);
        averageScore.setFont(getFont().deriveFont(Font.BOLD,20f));
        averageScore.setHorizontalAlignment(SwingConstants.CENTER);
        submit = new CustomButton("Submit");
        submit.addActionListener(this);
        GridLayout ratingsGrid = new GridLayout(ratings.size(), 1);
        ratingsBg = new JPanel();
        ratingsBg.setBackground(Colors.BG_BRIGHT);
        ratingsBg.setLayout(ratingsGrid);
        ratingsBg.setPreferredSize(new Dimension(450,300));
        ratingsGrid.setVgap(2);
        GridLayout commentGrid = new GridLayout(comments.size(), 1);
        commentsBg = new JPanel();
        commentsBg.setBackground(Colors.BG_BRIGHT);
        commentsBg.setLayout(commentGrid);
        commentsBg.setPreferredSize(new Dimension(450,300));
        ratingsGrid.setVgap(2);
        splitPane = new JPanel();
        splitPane.setPreferredSize(new Dimension(1000,300));
        splitPane.setLayout(new GridLayout(1,2));
        splitPane.add(ratingsBg);
        splitPane.add(commentsBg);
        commentField = new JTextField();
        commentField.setPreferredSize(new Dimension(400,50));
        bg = new JPanel();
        bg.setBackground(Colors.BG_BRIGHT);
        GridBagLayout gb = new GridBagLayout();
        bg.setLayout(gb);
        setAverageText();
    }

    private JMenuBar addRatingOptions() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gradeMenu = new JMenu("Choose rating");
        euforisk = new JMenuItem("Euforisk!");
        mycketnojd = new JMenuItem("Mycket nöjd");
        nojd = new JMenuItem("Nöjd");
        ganskanojd = new JMenuItem("Ganska nöjd");
        missnojd = new JMenuItem("Missnöjd");
        for (JMenuItem item : Arrays.asList(euforisk, mycketnojd, nojd, ganskanojd, missnojd)) {
            item.addActionListener(this);
            gradeMenu.add(item);
        }
        menuBar.add(gradeMenu);
        return menuBar;
    }

    private void addElements() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,0,0,0);
        gc.gridy = 0;
        gc.gridx = 1;
        bg.add(title,gc);
        gc.gridy = 1;
        gc.gridx = 1;
        bg.add(averageScore,gc);
        addRatings();
        addComments();
        splitPane.add(ratingsBg);
        splitPane.add(commentsBg);
        gc.gridy = 2;
        gc.gridx = 1;
        gc.weightx = 50;
        gc.insets = new Insets(50,10,10,10);
        bg.add(splitPane,gc);
        gc.gridy = 3;
        gc.gridx = 1;
        gc.insets = new Insets(10,10,10,10);
        gc.weightx = 10;
        bg.add(commentField,gc);
        gc.gridy = 4;
        bg.add(addRatingOptions(),gc);
        gc.gridy = 5;
        bg.add(submit,gc);
        add(bg);
    }

    private void setAverageText() {
        averageScore.setText(average);
    }

    private void addRatings() {
        for (Rating rating : ratings) {
            ratingsBg.add(new CustomTextArea("From: "+rating.getCustomer().getName()+", "+rating.getCustomer().getArea().getName()+"\n"+rating.getGrade().getGrade()));
        }
    }
    private void addComments() {
        for (Comment comment : comments) {
            commentsBg.add(new CustomTextArea("From: "+comment.getCustomer().getName()+", "+comment.getCustomer().getArea().getName()+"\n"+comment.getText()));
        }
    }

    public void setSubmitListener(RateAndCommentSubmitListener submitListener) {
        this.submitListener = submitListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && counter == 0) {
            if (gradeId > 0 && commentField.getText().length() > 1) {
                counter++;
                submitListener.submissionOccurred(gradeId, commentField.getText(), userId, product.getId());
            } else {
                new CustomJop("Invalid input!","Try again");

            }
        }
        if (e.getSource() == euforisk) {
            gradeId = 1;
        }
        if (e.getSource() == mycketnojd) {
            gradeId = 2;
        }
        if (e.getSource() == nojd) {
            gradeId = 3;
        }
        if (e.getSource() == ganskanojd) {
            gradeId = 4;
        }
        if (e.getSource() == missnojd) {
            gradeId = 5;
        }
    }
}
