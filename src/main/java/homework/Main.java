package homework;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    //adresas iki duomenu bazes.
    public static final String DB_URL = "jdbc:mysql://localhost:3306/christmas_presents";

    public static void main(String[] args) throws SQLException {
        //use drive manager to create connection object.
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);//ctrl+alt+v pasitalpinam i kintamaji
        // using a connection object we can create a statement object.
        Statement statement = connection.createStatement();

        String sql_select_all_christmas_presents = "SELECT * FROM christmas_presents;";

        ResultSet resultAllChristmas_presents = statement.executeQuery(sql_select_all_christmas_presents);

        List<ChristmasPresent> christmasPresents = new ArrayList<>();

        while (resultAllChristmas_presents.next()) {

            // nurodau stulpelio pavadinima ir gaunu jo reiksme
            int id = resultAllChristmas_presents.getInt("id");
            String gift = resultAllChristmas_presents.getString("gift");
            String shop = resultAllChristmas_presents.getString("shop");
            double price = resultAllChristmas_presents.getDouble("price");
            String recipient = resultAllChristmas_presents.getString("recipient");
            boolean is_bought = resultAllChristmas_presents.getBoolean("is_bought");

            ChristmasPresent christmasPresent = ChristmasPresent.builder()
                    .id(id)
                    .gift(gift)
                    .shop(shop)
                    .price(price)
                    .recipient(recipient)
                    .is_bought(is_bought)
                    .build();
            christmasPresents.add(christmasPresent);

        }

        for (ChristmasPresent christmasPresent : christmasPresents) {
            System.out.println("Dovanu sarasas: " + christmasPresent);

        }
        ResultSet resultSet = statement.executeQuery("SELECT * FROM christmas_presents WHERE is_bought = 'true'");
        System.out.println();
        System.out.println("Nupirktos dovanos: ");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("gift"));
        }
        String insertSql = "INSERT INTO christmas_presents VALUES (13, 'Aquarium','Pet24', 650.33, 'Joana', true)";
        int affectedRows = statement.executeUpdate(insertSql);

        if (affectedRows == 1) {
            System.out.println("iterpta sekmingai");
        } else {
            System.out.println("neiterpta");

        }
        ResultSet s = statement.executeQuery("SELECT * FROM christmas_presents WHERE recipient = 'Gitana'");
        System.out.println();
        System.out.println("Dovanos nupirktos tam tikram asmeniui:");

        while (s.next()) {
            System.out.println(s.getString("gift"));
        }
    }
}
