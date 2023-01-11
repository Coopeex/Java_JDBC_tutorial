package jdbcConnectionTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMain {

    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    //adresas iki duomenu bazes.
    public static final String DB_URL = "jdbc:mysql://localhost:3306/sundayfunday";

    public static void main(String[] args) throws SQLException {
        //use drive manager to create connection object.
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);//ctrl+alt+v pasitalpinam i kintamaji
        // using a connection object we can create a statement object.
        Statement statement = connection.createStatement();

        //SELECT * FROM Players;
        String sql_select_all_players = "SELECT * FROM players;";

        ResultSet resultAllPlayers = statement.executeQuery(sql_select_all_players);

        List<Player> players = new ArrayList<>();

        while (resultAllPlayers.next()) {

            // nurodau stulpelio pavadinima ir gaunu jo reiksme
            String firstName = resultAllPlayers.getString("first_name");
            String lastName = resultAllPlayers.getString("last_name");
            String team = resultAllPlayers.getString("team");
            int wins = resultAllPlayers.getInt("wins");
            int loses = resultAllPlayers.getInt("loses");
            double height = resultAllPlayers.getDouble("height");
            double ppg = resultAllPlayers.getDouble("ppg");
            int id = resultAllPlayers.getInt("id");
            Player player = Player.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .team(team)
                    .wins(wins)
                    .loses(loses)
                    .height(height)
                    .ppg(ppg)
                    .build();
            players.add(player);

        }
        for (Player player : players) {
            System.out.println(player);

        }

        ResultSet resultSet = statement.executeQuery("SELECT * FROM players WHERE team = 'Spurs'");
        System.out.println();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("first_name"));
        }

        System.out.println();
        //Insert into blabla
        String insertSql = "INSERT INTO players VALUES (21, 'Lebron', 'James', 'Heat', 2.11, 35.5, 56, 12)";
        int affectedRows = statement.executeUpdate(insertSql); //grazina rezultata kiek eiliuciu buvo paveikta

        if(affectedRows == 1) {
            System.out.println("iterpta sekmingai");
        } else {
            System.out.println("neiterpta");

        }
    }
}
