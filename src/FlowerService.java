import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlowerService {
    private final List<Flower> flowers = new ArrayList<>();

    public void addFlower(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите название цветка: ");
                String name = scanner.next();

                System.out.print("Введите цвет: ");
                String color = scanner.next();

                System.out.print("Введите количество: ");
                int quantity = scanner.nextInt();

                System.out.print("Введите цену: ");
                double price = scanner.nextDouble();

                System.out.print("Введите сезон цветения: ");
                String bloomSeason = scanner.next();

                System.out.print("Введите аромат (сильный/слабый/отсутствует): ");
                String fragrance = scanner.next();

                if (isFlowerExists(name)) {
                    System.out.println("Такой цветок уже есть");
                } else {
                    Flower flower = new Flower(name, color, quantity, price, bloomSeason, fragrance);
                    flowers.add(flower);
                    System.out.println("Цветок \"" + flower.getName() + "\" был добавлен.");
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите корректные данные.");
                scanner.nextLine();
            }
        }
    }

    public void displayAllFlowers() {
        if (flowers.isEmpty()) {
            System.out.println("Список цветов пуст.");
        } else {
            System.out.println("--- Список цветов ---");
            int i = 1;
            for (Flower flower : flowers) {
                System.out.println(i++ + ". " + flower);
            }
        }
    }

    public void editFlower(Scanner scanner) {
        if (flowers.isEmpty()) {
            System.out.println("Список цветов пуст.");
            return;
        }

        displayAllFlowers();
        System.out.print("Введите номер цветка для редактирования: ");
        try {
            int number = scanner.nextInt() - 1;

            if (number < 0 || number >= flowers.size()) {
                System.out.println("Некорректный номер цветка.");
                return;
            }

            Flower flower = flowers.get(number);
            String firstName = flower.getName();

            System.out.print("Введите новое название цветка: ");
            flower.setName(scanner.next());

            System.out.print("Введите новый цвет: ");
            flower.setColor(scanner.next());

            System.out.print("Введите новое количество: ");
            flower.setQuantity(scanner.nextInt());

            System.out.print("Введите новую цену: ");
            flower.setPrice(scanner.nextDouble());

            System.out.print("Введите новый сезон цветения: ");
            flower.setBloomSeason(scanner.next());

            System.out.print("Введите новый аромат: ");
            flower.setFragrance(scanner.next());

            System.out.println("Цветок \"" + firstName + "\" был изменен.");
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Пожалуйста, введите число.");
            scanner.nextLine();
        }
    }

    public void deleteFlower(Scanner scanner) {
        if (flowers.isEmpty()) {
            System.out.println("Список цветов пуст.");
            return;
        }

        displayAllFlowers();
        System.out.print("Введите номер цветка для удаления: ");
        try {
            int choice = scanner.nextInt() - 1;

            if (choice < 0 || choice >= flowers.size()) {
                System.out.println("Некорректный номер цветка.");
                return;
            }

            String name = flowers.get(choice).getName();
            flowers.remove(choice);
            System.out.println("Цветок \"" + name + "\" был удален.");
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Пожалуйста, введите число.");
            scanner.nextLine();
        }
    }

    public void buyFlowers(Scanner scanner) {
        if (flowers.isEmpty()) {
            System.out.println("Список цветов пуст.");
            return;
        }

        displayAllFlowers();
        System.out.print("Введите номер цветка для покупки: ");
        try {
            int number = scanner.nextInt() - 1;

            if (number < 0 || number >= flowers.size()) {
                System.out.println("Некорректный номер цветка.");
                return;
            }

            Flower flower = flowers.get(number);
            System.out.print("Введите количество для покупки: ");
            int quantity = scanner.nextInt();

            if (quantity <= 0) {
                System.out.println("Количество должно быть положительным.");
            } else if (quantity > flower.getQuantity()) {
                System.out.println("Недостаточно цветов в наличии.");
            } else {
                flower.setQuantity(flower.getQuantity() - quantity);
                double total = quantity * flower.getPrice();
                System.out.printf("Вы купили %d %s на сумму %.2f\n", quantity, flower.getName(), total);
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Пожалуйста, введите число.");
            scanner.nextLine();
        }
    }

    public boolean isFlowerExists(String name) {
        for (Flower flower : flowers) {
            if (flower.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }
}