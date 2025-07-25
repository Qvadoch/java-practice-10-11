import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class FlowerShop {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FlowerService flowerService = new FlowerService();
    private static final AuthManager authManager = new AuthManager();

    public static void main(String[] args) {
        System.out.println("=== ЦВЕТОЧНЫЙ МАГАЗИН ===");
        System.out.println("Загрузка сохраненных данных...");
        DataManager.autoLoadData(flowerService.getFlowers(), authManager.getUsers());

        while (true) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Регистрация нового пользователя");
            System.out.println("2. Войти в систему");
            System.out.println("3. Выход из программы");
            System.out.print("Выберите действие (1-3): ");

            try {
                int action = scanner.nextInt();
                scanner.nextLine();

                switch (action) {
                    case 1:
                        authManager.register();
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
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
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        System.out.println("\nДанные автоматически сохранены.");
                        System.out.println("Программа завершена. До свидания!");
                        System.exit(0);
                    default:
                        System.out.println("Ошибка: Неверный выбор. Введите число от 1 до 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: Пожалуйста, введите число от 1 до 3.");
                scanner.nextLine();
            }
        }
    }

    private static void showEmployeeMenu() {
        while (true) {
            System.out.println("\n=== МЕНЮ СОТРУДНИКА ===");
            System.out.println("1. Добавить новый цветок");
            System.out.println("2. Просмотреть каталог цветов");
            System.out.println("3. Редактировать цветок");
            System.out.println("4. Удалить цветок");
            System.out.println("5. Отменить последнее действие");
            System.out.println("6. Экспорт каталога в текстовый файл");
            System.out.println("7. Импорт каталога из текстового файла");
            System.out.println("8. Сохранить все данные в бинарный файл");
            System.out.println("9. Загрузить все данные из бинарного файла");
            System.out.println("10. Вернуться в главное меню");
            System.out.print("Введите номер действия (1-10): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        flowerService.addFlower(scanner);
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 2:
                        flowerService.displayAllFlowers();
                        break;
                    case 3:
                        flowerService.editFlower(scanner);
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 4:
                        flowerService.deleteFlower(scanner);
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 5:
                        flowerService.cancelLastAction();
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 6:
                        try {
                            DataManager.exportToFile(scanner, flowerService.getFlowers());
                        } catch (IOException e) {
                            System.out.println("Ошибка при экспорте: " + e.getMessage());
                        }
                        break;
                    case 7:
                        try {
                            DataManager.importFromFile(scanner, flowerService.getFlowers());
                            DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        } catch (FileNotFoundException e) {
                            System.out.println("Ошибка импорта: " + e.getMessage());
                        } catch (IOException e) {
                            System.out.println("Ошибка при чтении файла: " + e.getMessage());
                        }
                        break;
                    case 8:
                        DataManager.saveToBinaryFile(scanner, flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 9:
                        DataManager.loadFromBinaryFile(scanner, flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 10:
                        System.out.println("Возвращение в главное меню...");
                        return;
                    default:
                        System.out.println("Ошибка: Неверный выбор. Введите число от 1 до 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: Пожалуйста, введите число от 1 до 10.");
                scanner.nextLine();
            }
        }
    }

    private static void showCustomerMenu() {
        while (true) {
            System.out.println("\n=== МЕНЮ ПОКУПАТЕЛЯ ===");
            System.out.println("1. Просмотреть каталог цветов");
            System.out.println("2. Купить цветы");
            System.out.println("3. Вернуться в главное меню");
            System.out.print("Введите номер действия (1-3): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        flowerService.displayAllFlowers();
                        break;
                    case 2:
                        flowerService.buyFlowers(scanner);
                        DataManager.autoSaveData(flowerService.getFlowers(), authManager.getUsers());
                        break;
                    case 3:
                        System.out.println("Возвращение в главное меню...");
                        return;
                    default:
                        System.out.println("Ошибка: Неверный выбор. Введите число от 1 до 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: Пожалуйста, введите число от 1 до 3.");
                scanner.nextLine();
            }
        }
    }
}