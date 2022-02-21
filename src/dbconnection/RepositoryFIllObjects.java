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
    private String connectionString;

    public RepositoryFIllObjects(Properties properties) {
        this.properties = properties;
        setConnectionStrings();
    }

    private void setConnectionStrings() {
        name = properties.getProperty("name");
        password = properties.getProperty("password");
        connectionString = properties.getProperty("connectionString");
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
                shoe.setProduct(product);
                colors.forEach(shoe::addColor);
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
