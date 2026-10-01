package oop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private Connection connection;

    private static final String SQL_SELECT_FROM_DRIVERS = "select id, first_name, last_name, age from drivers";

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVERS);

        List<User> result = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")
            );
            result.add(user);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public List<User> findAllByAge(Integer age) throws SQLException {
        String sql = SQL_SELECT_FROM_DRIVERS + " where age = " + age;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );

                result.add(user);
            }

            return result;
        }
    }

    @Override
    public void saveAll(List<User> users) throws SQLException {
        if (users.isEmpty()) {
            return;
        }

        String sql = "insert into drivers (first_name, last_name, age) values ";

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            String name = user.getName().replace("'", "''");
            String surname = user.getSurname().replace("'", "''");

            sql += "('" + name + "', '" + surname + "', "
                    + user.getAge() + ")";

            if (i < users.size() - 1) {
                sql += ", ";
            }
        }

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
