package teamWork2;

import teamWork2.entity.Product;
import teamWork2.repository.DatabaseService;
import teamWork2.utils.ReadingFromJson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        ReadingFromJson readingFromJson = new ReadingFromJson();
        List<Product> products = readingFromJson.convertJsonToList("src/main/resources/data.json");
        DatabaseService databaseService = new DatabaseService();
        databaseService.CreateProductsTable();
        databaseService.CreateManufacturerTable();
        databaseService.insertIntoProductsTable();
        databaseService.insertIntoManufacturerTable("src/main/resources/data.json");
        System.out.println("All products: ");
        System.out.println(databaseService.findAllProducts());
        System.out.println();
        System.out.println("Find products by name: ");
        System.out.println(databaseService.findProductByName("Bread"));
        System.out.println("findAllProductsByGivenManufacturer");
        System.out.println(databaseService.findProductByGivenManufacturer("Fasma"));
        System.out.println();
        Product product = new Product();
        product.setId(3);
        //databaseService.deleteProduct(3);
        //databaseService.deleteManufacturer(2);

    }
}
