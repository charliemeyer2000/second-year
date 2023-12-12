package edu.virginia.sde.javafx.tacos;

import java.sql.SQLException;

public class HotDogVotesService {
    public void save(HotDogVotes hotDogVotes) {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = new DatabaseConnection();
            databaseConnection.updateVotes(hotDogVotes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (databaseConnection != null) {
                    databaseConnection.disconnect();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public HotDogVotes retrieve() {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = new DatabaseConnection();
            return databaseConnection.getVotes();
        } catch (SQLException e) {
            return new HotDogVotes();
        } finally {
            try {
                if (databaseConnection != null) {
                    databaseConnection.disconnect();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
