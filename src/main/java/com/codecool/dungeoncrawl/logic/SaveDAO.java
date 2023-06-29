package com.codecool.dungeoncrawl.logic;

import java.sql.*;

public class SaveDAO {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Dungeon_Crawl_Saves.public.Saves_table";
        String username = "postgres";
        String password = "postgres";
        return DriverManager.getConnection(url, username, password);
    }

    public void save(Save save) throws SQLException {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO saves_table (save_time, saves_status) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, save.getSaveTime());
            statement.setString(2, save.getSavesStatus());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long id = generatedKeys.getLong(1);
                save.setId(id);
            }
        }
    }
}

