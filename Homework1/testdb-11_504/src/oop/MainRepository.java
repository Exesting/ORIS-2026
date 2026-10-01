package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MainRepository {

    private static final String DB_USERNAME = "postgres";

    private static final String DB_PASSWORD = "YOUR_PASSWORD";

    private static final String DB_URL =
            "jdbc:postgresql://localhost:5432/testbd_11-504";

    public static void main(String[] args) throws SQLException {
        Connection connection =
                DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        UserRepository userRepository =
                new UserRepositoryJdbcImpl(connection);

        List<User> users = new ArrayList<>();

        users.add(new User(null, "Алексей", "Жаднов", 19));
        users.add(new User(null, "Олег", "Сатья", 54));
        users.add(new User(null, "Андрей", "Наггетс", 15));
        users.add(new User(null, "Даник", "Хойщик", 19));
        users.add(new User(null, "Азамат", "Сталкрафтер", 19));
        users.add(new User(null, "Дмитрий", "Накиев", 59));

        userRepository.saveAll(users);
        System.out.println("Добавлено 6 пользователей");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите возраст для поиска:");
        int age = scanner.nextInt();

        List<User> foundUsers = userRepository.findAllByAge(age);

        if (foundUsers.isEmpty()) {
            System.out.println("Пользователи не найдены");
        }

        for (User user : foundUsers) {
            System.out.println(
                    user.getId() + " " +
                            user.getName() + " " +
                            user.getSurname() + " " +
                            user.getAge()
            );
        }

        connection.close();
    }
}