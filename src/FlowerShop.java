// FlowerShop.java
import java.util.Scanner;

public class FlowerShop {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FlowerService flowerService = new FlowerService();
    private static final AuthManager authManager = new AuthManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Цветочный магазин ===");
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            try {
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 1:
                        authManager.register();
                        break;
                    case 2:
                        UserModel user = authManager.login();
                        if (user != null) {
                            if (user instanceof Employee) {
                                showEmployeeMenu();
                            } else if (user instanceof Customer) {
                                showCustomerMenu();
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Программа завершена. До свидания!");
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 3.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }
    }

    private static void showEmployeeMenu() {
        while (true) {
            System.out.println("\n=== Меню сотрудника ===");
            System.out.println("1. Добавить новый цветок");
            System.out.println("2. Просмотреть все цветы");
            System.out.println("3. Редактировать цветок");
            System.out.println("4. Удалить цветок");
            System.out.println("5. Экспорт");
            System.out.println("6. Импорт");
            System.out.println("7. Выход в главное меню");
            System.out.print("Введите номер действия: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        flowerService.addFlower(scanner);
                        break;
                    case 2:
                        flowerService.displayAllFlowers();
                        break;
                    case 3:
                        flowerService.editFlower(scanner);
                        break;
                    case 4:
                        flowerService.deleteFlower(scanner);
                        break;
                    case 5:
                        try {
                            DataManager.exportToFile(scanner, flowerService.getFlowers());
                            System.out.println("Данные успешно экспортированы.");
                        } catch (Exception e) {
                            System.out.println("Ошибка при экспорте: " + e.getMessage());
                        }
                        break;
                    case 6:
                        try {
                            DataManager.importFromFile(scanner, flowerService.getFlowers());
                            System.out.println("Данные успешно импортированы.");
                        } catch (Exception e) {
                            System.out.println("Ошибка при импорте: " + e.getMessage());
                        }
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 7.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }
    }

    private static void showCustomerMenu() {
        while (true) {
            System.out.println("\n=== Меню покупателя ===");
            System.out.println("1. Просмотреть все цветы");
            System.out.println("2. Купить цветы");
            System.out.println("3. Выход в главное меню");
            System.out.print("Введите номер действия: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        FlowerShop.flowerService.displayAllFlowers();
                        break;
                    case 2:
                        FlowerShop.flowerService.buyFlowers(scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 3.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите число.");
                scanner.nextLine();
            }
        }
    }
}