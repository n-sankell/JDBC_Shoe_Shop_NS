package dbconnection;

import dbobjectmodel.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class RepositoryFIllObjects {

    private final Properties properties;
    private String name;
    private String password;

    public RepositoryFIllObjects(Properties properties) {
        this.properties = properties;
        setConnectionStrings();
    }

    private void setConnectionStrings() {
        name = properties.getProperty("name");
        password = properties.getProperty("password");
    }

    private Price getPriceByShoeId(int shoeId) {
        Price price = null;
        String query = "select * from shoe_shop_db_new.price inner join shoe_shop_db_new.shoe " +
                "on price.id = shoe.priceId where shoe.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, shoeId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                price = new Price(rs.getInt("id"),rs.getDouble("shoePrice"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    private Size getSizeByShoeId(int shoeId) {
        Size size = null;
        String query = "select * from shoe_shop_db_new.size inner join shoe_shop_db_new.shoe " +
                "on size.id = shoe.sizeId where shoe.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, shoeId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                size = new Size(rs.getInt("id"),rs.getDouble("shoeSize"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    private List<ShoeColor> getColorsByShoeId(int shoeId) {
        List<ShoeColor> colors = new ArrayList<>();
        String query = "select * from shoe_shop_db_new.color "+
                "inner join shoe_shop_db_new.map_shoe_color on map_shoe_color.colorId = color.id " +
                "inner join shoe_shop_db_new.shoe on map_shoe_color.shoeId = shoe.id where shoe.id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, shoeId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                colors.add(new ShoeColor(rs.getInt("id"),rs.getString("colorName")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return colors;
    }

    private List<OutOfStock> getOutOfStockByShoeId(int shoeId) {
        List<OutOfStock> outOfStockList = new ArrayList<>();

        String query = "select * from shoe_shop_db_new.outofstock "+
                "inner join shoe_shop_db_new.shoe on outofstock.shoeId = shoe.id where shoe.id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, shoeId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                outOfStockList.add(new OutOfStock(rs.getInt("id"),rs.getInt("shoeId"),rs.getDate("date")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return outOfStockList;
    }

    private List<Order> getOrdersFromShoeId(int shoeId) {
        List<Order> orders = new ArrayList<>();
        Order order;
        String query = "select * from shoe_shop_db_new.shoe_order "+
                "inner join shoe_shop_db_new.map_shoe_order_shoe on shoe_order.id = map_shoe_order_shoe.shoeOrderId " +
                "inner join shoe_shop_db_new.shoe on map_shoe_order_shoe.shoeId = shoe.id where shoe.id = ?";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, shoeId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                order = new Order(rs.getInt("id"),rs.getInt("customerId"),rs.getDate("date"));
                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return orders;
    }

    private List<CompleteShoe> getCompleteShoByProdId(int id, BaseProduct product) {
        CompleteShoe shoe;
        List<CompleteShoe> shoes = new ArrayList<>();
        String query = "select * from shoe_shop_db_new.shoe inner join shoe_shop_db_new.base_product on " +
                "shoe.baseProductId = base_product.id where base_product.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                shoe = new CompleteShoe(rs.getInt("id"),rs.getInt("baseProductId"),
                        rs.getInt("sizeId"), rs.getInt("priceId"),rs.getInt("inStock"));
                shoe.setSize(getSizeByShoeId(shoe.getId()));
                shoe.setPrice(getPriceByShoeId(shoe.getId()));
                List<ShoeColor> colors = getColorsByShoeId(shoe.getId());
                colors.forEach(shoe::addColor);
                List<OutOfStock> outOfStockList = getOutOfStockByShoeId(shoe.getId());
                outOfStockList.forEach(shoe::addOutOfStock);
                List<Order> orders = getOrdersFromShoeId(shoe.getId());
                orders.forEach(shoe::addOrder);
                shoe.setProduct(product);
                shoes.add(shoe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shoes;
    }

    private List<Category> getCategoriesByBaseProductId(int baseProductId) {
        List<Category> products = new ArrayList<>();
        String query = "select * from shoe_shop_db_new.category "+
                "inner join shoe_shop_db_new.map_base_product_category on map_base_product_category.categoryId = category.id " +
                "inner join shoe_shop_db_new.base_product on map_base_product_category.productId = base_product.id where base_product.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, baseProductId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(new Category(rs.getInt("id"),rs.getString("categoryName")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    private Label getLabelByProductId(int productId) {
        Label label = null;
        String query = "select * from shoe_shop_db_new.label inner join shoe_shop_db_new.base_product " +
                "on base_product.labelId = label.id where base_product.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, productId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                label = new Label(rs.getInt("id"),rs.getString("labelName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    private List<Comment> getCommentsByBaseProductId(int baseProductId, BaseProduct product) {
        List<Comment> comments = new ArrayList<>();
        String query = "select * from shoe_shop_db_new.customer_comment "+
                "inner join shoe_shop_db_new.base_product on customer_comment.productId = base_product.id where base_product.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, baseProductId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id"); String text = rs.getString("commentText"); int customerId = rs.getInt("customerId");
                int productId = rs.getInt("productId"); Date date = rs.getDate("date");
                Comment comment = new Comment(id,text,customerId,productId,date);
                comment.setBaseProduct(product);
                comments.add(comment);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return comments;
    }

    private RatingGrade getRatingGradeByRatingId(int ratingId) {
        RatingGrade grade = null;
        String query = "select * from shoe_shop_db_new.rating_grade "+
                "inner join shoe_shop_db_new.customer_rating on customer_rating.gradeId = rating_grade.id where customer_rating.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, ratingId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                grade = new RatingGrade(rs.getInt("id"),rs.getString("grade"),
                        rs.getInt("gradeNumber"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return grade;
    }

    private List<Rating> getRatingByBaseProductId(int baseProductId, BaseProduct product) {
        List<Rating> ratings = new ArrayList<>();
        RatingGrade grade;
        String query = "select * from shoe_shop_db_new.customer_rating "+
                "inner join shoe_shop_db_new.base_product on customer_rating.productId = base_product.id where base_product.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, baseProductId+"");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id"); int gradeId = rs.getInt("gradeId"); int customerId = rs.getInt("customerId");
                int productId = rs.getInt("productId"); Date date = rs.getDate("date");
                Rating rating = new Rating(id,gradeId,customerId,productId,date);
                grade = getRatingGradeByRatingId(id);
                rating.setGrade(grade);
                rating.setProduct(product);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ratings;
    }

    private BaseProduct getBaseProductById(int id) {
        BaseProduct product = null;
        Label label = getLabelByProductId(id);

        List<Category> categories = getCategoriesByBaseProductId(id);
                String query = "select * from shoe_shop_db_new.base_product where base_product.id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                product = new BaseProduct(rs.getInt("id"),rs.getInt("labelId"),rs.getString("productName"));
                List<CompleteShoe> shoes = getCompleteShoByProdId(id, product);
                product.setLabel(label);
                categories.forEach(product::addCategory);
                shoes.forEach(product::addShoe);
                List<Rating> ratings = getRatingByBaseProductId(id, product);
                List<Comment> comments = getCommentsByBaseProductId(id, product);
                ratings.forEach(product::addRating);
                comments.forEach(product::addComment);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<BaseProduct> getBaseProducts() {
        List<BaseProduct> products = new ArrayList<>();
        List<Integer> productId = new ArrayList<>();

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id FROM shoe_shop_db_new.base_product")) {

            while (rs.next()) {
                productId.add(rs.getInt("id"));
            }

            products = productId.stream()
                    .map(this::getBaseProductById)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}
