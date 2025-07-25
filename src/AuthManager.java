import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class AuthManager {
    private final List<UserModel> users = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void register() {
        try {
            System.out.println("\n=== РЕГИСТРАЦИЯ НОВОГО ПОЛЬЗОВАТЕЛЯ ===");

            System.out.print("Введите логин: ");
            String login = scanner.next();

            if (login.trim().isEmpty()) {
                throw new IllegalArgumentException("Логин не может быть пустым");
            }

            // Проверка на существующего пользователя
            for (UserModel user : users) {
                if (user.getLogin().equals(login)) {
                    throw new IllegalArgumentException("Пользователь с таким логином уже существует");
                }
            }

            System.out.print("Введите пароль: ");
            String passw = scanner.next();

            if (passw.length() < 3) {
                throw new IllegalArgumentException("Пароль должен содержать минимум 3 символа");
            }

            System.out.println("Выберите роль:");
            System.out.println("1 - Покупатель");
            System.out.println("2 - Сотрудник");
            System.out.print("Ваш выбор: ");
            int role = scanner.nextInt();

            UserModel newUser;
            if (role == 1) {
                newUser = new Customer(login, passw);
            } else if (role == 2) {
                newUser = new Employee(login, passw);
            } else {
                throw new IllegalArgumentException("Неверный выбор роли. Доступны только варианты 1 или 2");
            }

            users.add(newUser);
            System.out.println("\nУспешно! Пользователь \"" + login + "\" был зарегистрирован.");
            newUser.getRole();

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: Пожалуйста, введите корректные данные.");
            scanner.nextLine();
        }
    }

    public UserModel login() {
        try {
            System.out.println("\n=== АВТОРИЗАЦИЯ ===");

            System.out.print("Введите логин: ");
            String login = scanner.next();

            System.out.print("Введите пароль: ");
            String passw = scanner.next();

            for (UserModel user : users) {
                if (user.getLogin().equals(login) && user.getPassw().equals(passw)) {
                    System.out.println("\nАвторизация успешна! Добро пожаловать!");
                    user.getRole();
                    return user;
                }
            }

            System.out.println("Ошибка авторизации: Неверный логин или пароль.");

        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: Пожалуйста, введите корректные данные.");
            scanner.nextLine();
        }
        return null;
    }

    public List<UserModel> getUsers() {
        return users;
    }
}