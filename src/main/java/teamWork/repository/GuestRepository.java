package teamWork.repository;


import jdbc_recap.utils.DatabaseUtils;
import teamWork.entity.Guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {
    private static final String SELECT_FROM_GUESTS = "SELECT * FROM guests";
    private static final String INSERT_GUESTS = "INSERT INTO Guests (name, age, emailAddress, nationality) VALUES('%s', %d, '%s', '%s')";

    public void add(Guest guest) {
        try {
            Statement statement = DatabaseUtils.dbConnection.createStatement();
            statement.executeUpdate(String.format(INSERT_GUESTS, guest.getName(), guest.getAge(), guest.getEmailAddress(), guest.getNationality()));
            System.out.println("Guest was added: " + guest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Guest> findAll() {

        List<Guest> guests;

        try {
            Statement statement = DatabaseUtils.dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_GUESTS);

            guests = constructGuestList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guests;

    }

    private static List<Guest> constructGuestList(ResultSet resultSet) throws SQLException {
        List<Guest> guests = new ArrayList<>();
        while (resultSet.next()) {
            Guest guest = new Guest();
            guest.setId(resultSet.getInt("id"));
            guest.setName(resultSet.getString("name"));
            guest.setAge(resultSet.getInt("age"));
            guest.setEmailAddress(resultSet.getString("emailAddress"));
            guest.setNationality(resultSet.getString("nationality"));
            guests.add(guest);
        }
        return guests;
    }

    public List<Guest> findGuestByName(String name) {
        Statement statement;
        List<Guest> guests;
        try {
            statement = DatabaseUtils.dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM guests WHERE name = '" + name + "'");

            guests = constructGuestList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guests;
    }
    public List<Guest> findGuestByGivenEmailEnding(String emailEnding) {
        Statement statement;
        List<Guest> guests;
        try {
            statement = DatabaseUtils.dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM guests WHERE emailAddress like '%" + emailEnding + "%'");

            guests = constructGuestList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return guests;
    }
}

