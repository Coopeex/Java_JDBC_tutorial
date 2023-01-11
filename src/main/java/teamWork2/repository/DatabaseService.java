package teamWork2.repository;

import jdbc_recap.utils.DatabaseUtils;
import teamWork2.entity.Product;
import teamWork2.utils.ReadingFromJson;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jdbc_recap.utils.DatabaseUtils.dbConnection;


public class DatabaseService {
    private static final String SELECT_FROM_PRODUCTS = "SELECT * FROM products";
    private static final String SELECT_BY_NAME = "SELECT * FROM products WHERE name ='%s'";
    private static final String SELECT_BY_GIVEN_MANUFACTURER = "SELECT * FROM products WHERE manufacturer ='%s'";
    private static final String DELETE_PRODUCT_BY_ID = "SET FOREIGN_KEY_CHECKS=0;\n" +
            "DELETE FROM products WHERE id = '%s' LIMIT 1 ;\n" +
            "SET FOREIGN_KEY_CHECKS =1; ";

    public void CreateProductsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                "name varchar(60) not null," +
                "country VARCHAR(60) NOT NULL," +
                " price INT NOT NULL, " +
                " manufacturer VARCHAR(60) NOT NULL, " +
                " PRIMARY KEY (id))";
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void CreateManufacturerTable() {
        String sql = "CREATE TABLE IF NOT EXISTS manufacturers " +
                "(id INT not NULL AUTO_INCREMENT, " +
                "name varchar(60) not null," +
                "countryManufacturer VARCHAR(60) NOT NULL," +
                "numberOfEmployees INT NOT NULL," +
                "manufacturerAddress VARCHAR(80) NOT NULL, " +
                "PRIMARY KEY (id)," +
                "FOREIGN KEY(id) REFERENCES products(id))";
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoProductsTable() throws IOException, SQLException {
        ReadingFromJson readingFromJson = new ReadingFromJson();
        Statement statement = dbConnection.createStatement();
        List<Product> products = readingFromJson.convertJsonToList("src/main/resources/data.json");
        for (Product product : products) {
            String name = product.getName_product();
            String country = product.getCountry_product();
            int price = product.getPrice_product();
            String manufacturer = product.getManufacturer();
            String sql = "insert into products(name, country, price, manufacturer) Values('%s','%s','%d', '%s')";
            statement.executeUpdate(String.format(sql, name, country, price, manufacturer));
        }
    }

    public void insertIntoManufacturerTable(String pathToJsonFile) throws IOException, SQLException {
        ReadingFromJson readingFromJson = new ReadingFromJson();
        Statement statement = dbConnection.createStatement();
        List<Product> products = readingFromJson.convertJsonToList(pathToJsonFile);
        for (Product product : products) {
            String name = product.getManufacturer();
            String country = product.getCountry_product();
            int numberOfEmployees = product.getManufacturer_emp_count();
            String manufacturerAddress = product.getManufacturer_address();
            String sql = "INSERT INTO manufacturers(name,countryManufacturer,numberOfEmployees,manufacturerAddress)" +
                    "VALUES('%s','%s','%d','%s')";
            statement.executeUpdate(String.format(sql, name, country, numberOfEmployees, manufacturerAddress));

        }
    }

    public List<Product> findAllProducts() {
        Statement statement = null;
        List<Product> products;
        try {
            statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_PRODUCTS);
            products = constructProductsList(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }


    private static List<Product> constructProductsList(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            product.setName_product(resultSet.getString("name"));
            product.setCountry_product(resultSet.getString("country"));
            product.setPrice_product(resultSet.getInt("price"));
            product.setManufacturer(resultSet.getString("manufacturer"));
            products.add(product);
        }
        return products;
    }

    public List<Product> findProductByName(String name) {
        Statement statement;
        List<Product> productsByName;
        try {
            statement = DatabaseUtils.dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(SELECT_BY_NAME, name));

            productsByName = constructProductsList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsByName;
    }

    public List<Product> findProductByGivenManufacturer(String name) {
        Statement statement;
        List<Product> productsByName;
        try {
            statement = DatabaseUtils.dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(SELECT_BY_GIVEN_MANUFACTURER, name));

            productsByName = constructProductsList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productsByName;
    }

    public void deleteProduct(Integer id) {

        Statement statement;
        try {
            dbConnection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS=0;");
            dbConnection.createStatement().executeUpdate("DELETE FROM products WHERE id = " + id + " LIMIT 1");
            dbConnection.createStatement().executeUpdate("SET FOREIGN_KEY_CHECKS=1;");
            System.out.println("Product deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        public void deleteManufacturer (Integer id){

            Statement statement;
            try {
                dbConnection.createStatement().executeUpdate("DELETE FROM manufacturers WHERE id = " + id);
                System.out.println("Manufacturer deleted");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


