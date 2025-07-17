// AuthManager.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthManager {
    private final List<UserModel> users = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void register() {
        System.out.print("Введите логин: ");
        String login = scanner.next();

        System.out.print("Введите пароль: ");
        String passw = scanner.next();

        System.out.print("Выберите роль (1 - Покупатель, 2 - Сотрудник): ");
        int role = scanner.nextInt();

        UserModel newUser;
        if (role == 1) {
            newUser = new Customer(login, passw);
        } else {
            newUser = new Employee(login, passw);
        }

        users.add(newUser);
        System.out.println("Пользователь \"" + login + "\" был успешно зарегистрирован.");
        newUser.getRole();
    }

    public UserModel login() {
        System.out.print("Введите логин: ");
        String login = scanner.next();

        System.out.print("Введите пароль: ");
        String passw = scanner.next();

        for (UserModel user : users) {
            if (user.getLogin().equals(login) && user.getPassw().equals(passw)) {
                System.out.println("Авторизация успешна!");
                user.getRole();
                return user;
            }
        }
        System.out.println("Ошибка авторизации. Неверный логин или пароль.");
        return null;
    }
}