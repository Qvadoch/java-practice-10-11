import java.util.*;

public class FlowerService {
    private final List<Flower> flowers = new ArrayList<>();
    Stack<ActionLog> actionLogs = new Stack<>();

    public void addFlower(Scanner scanner) {
        while (true) {
            try {
                System.out.println("\n=== ДОБАВЛЕНИЕ НОВОГО ЦВЕТКА ===");

                System.out.print("Введите название цветка: ");
                String name = scanner.next();

                if (name.trim().isEmpty()) {
                    throw new IllegalArgumentException("Название цветка не может быть пустым");
                }

                if (isFlowerExists(name)) {
                    throw new IllegalArgumentException("Цветок с названием \"" + name + "\" уже существует");
                }

                System.out.print("Введите цвет: ");
                String color = scanner.next();

                if (color.trim().isEmpty()) {
                    throw new IllegalArgumentException("Цвет не может быть пустым");
                }

                System.out.print("Введите количество: ");
                int quantity = scanner.nextInt();

                if (quantity < 0) {
                    throw new IllegalArgumentException("Количество не может быть отрицательным");
                }

                System.out.print("Введите цену: ");
                double price = scanner.nextDouble();

                if (price <= 0) {
                    throw new IllegalArgumentException("Цена должна быть положительной");
                }

                System.out.print("Введите сезон цветения: ");
                String bloomSeason = scanner.next();

                System.out.print("Введите аромат (сильный/слабый/отсутствует): ");
                String fragrance = scanner.next();

                Flower flower = new Flower(name, color, quantity, price, bloomSeason, fragrance);
                flowers.add(flower);

                System.out.println("\nУспешно! Цветок \"" + flower.getName() + "\" добавлен в каталог.");

                ActionLog actionLog = new ActionLog(ActionLog.Type.ADD, flower);
                actionLogs.push(actionLog);
                break;

            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода: Пожалуйста, введите корректные данные.");
                scanner.nextLine();
            }
        }
    }

    public void displayAllFlowers() {
        System.out.println("\n=== КАТАЛОГ ЦВЕТОВ ===");

        if (flowers.isEmpty()) {
            System.out.println("Каталог пуст. Добавьте цветы для начала работы.");
            return;
        }

        System.out.println("Всего позиций в каталоге: " + flowers.size());
        System.out.println("-".repeat(100));

        for (int i = 0; i < flowers.size(); i++) {
            System.out.printf("%2d. %s%n", (i + 1), flowers.get(i));
        }
        System.out.println("-".repeat(100));
    }

    public void editFlower(Scanner scanner) {
        if (flowers.isEmpty()) {
            System.out.println("Каталог цветов пуст. Нечего редактировать.");
            return;
        }

        System.out.println("\n=== РЕДАКТИРОВАНИЕ ЦВЕТКА ===");
        displayAllFlowers();

        try {
            System.out.print("Введите номер цветка для редактирования: ");
            int number = scanner.nextInt() - 1;

            if (number < 0 || number >= flowers.size()) {
                throw new IndexOutOfBoundsException("Цветок с номером " + (number + 1) + " не найден");
            }

            Flower flower = flowers.get(number);
            Flower oldFlower = new Flower(flower);

            System.out.println("\nТекущие данные: " + flower);
            System.out.println("\nВведите новые данные:");

            System.out.print("Новое название цветка: ");
            String newName = scanner.next();
            if (!newName.trim().isEmpty()) {
                flower.setName(newName);
            }

            System.out.print("Новый цвет: ");
            String newColor = scanner.next();
            if (!newColor.trim().isEmpty()) {
                flower.setColor(newColor);
            }

            System.out.print("Новое количество: ");
            int newQuantity = scanner.nextInt();
            if (newQuantity >= 0) {
                flower.setQuantity(newQuantity);
            } else {
                throw new IllegalArgumentException("Количество не может быть отрицательным");
            }

            System.out.print("Новая цена: ");
            double newPrice = scanner.nextDouble();
            if (newPrice > 0) {
                flower.setPrice(newPrice);
            } else {
                throw new IllegalArgumentException("Цена должна быть положительной");
            }

            System.out.print("Новый сезон цветения: ");
            String newSeason = scanner.next();
            if (!newSeason.trim().isEmpty()) {
                flower.setBloomSeason(newSeason);
            }

            System.out.print("Новый аромат: ");
            String newFragrance = scanner.next();
            if (!newFragrance.trim().isEmpty()) {
                flower.setFragrance(newFragrance);
            }

            System.out.println("\nУспешно! Цветок \"" + oldFlower.getName() + "\" обновлен.");

            ActionLog actionLog = new ActionLog(ActionLog.Type.EDIT, oldFlower, number);
            actionLogs.push(actionLog);

        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: Пожалуйста, введите корректные данные.");
            scanner.nextLine();
        }
    }

    public void deleteFlower(Scanner scanner) {
        if (flowers.isEmpty()) {
            System.out.println("Каталог цветов пуст. Нечего удалять.");
            return;
        }

        System.out.println("\n=== УДАЛЕНИЕ ЦВЕТКА ===");
        displayAllFlowers();

        try {
            System.out.print("Введите номер цветка для удаления: ");
            int choice = scanner.nextInt() - 1;

            if (choice < 0 || choice >= flowers.size()) {
                throw new IndexOutOfBoundsException("Цветок с номером " + (choice + 1) + " не найден");
            }

            Flower flower = flowers.get(choice);
            flowers.remove(choice);
            System.out.println("\nУспешно! Цветок \"" + flower.getName() + "\" удален из каталога.");

            ActionLog actionLog = new ActionLog(ActionLog.Type.DELETE, flower, choice);
            actionLogs.push(actionLog);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: Пожалуйста, введите число.");
            scanner.nextLine();
        }
    }

    public void buyFlowers(Scanner scanner) {
        if (flowers.isEmpty()) {
            System.out.println("Каталог цветов пуст. Покупки недоступны.");
            return;
        }

        System.out.println("\n=== ПОКУПКА ЦВЕТОВ ===");
        displayAllFlowers();

        try {
            System.out.print("Введите номер цветка для покупки: ");
            int number = scanner.nextInt() - 1;

            if (number < 0 || number >= flowers.size()) {
                throw new IndexOutOfBoundsException("Цветок с номером " + (number + 1) + " не найден");
            }

            Flower flower = flowers.get(number);

            if (flower.getQuantity() == 0) {
                throw new IllegalStateException("Цветок \"" + flower.getName() + "\" закончился на складе");
            }

            System.out.println("\nВыбранный товар: " + flower);
            System.out.print("Введите количество для покупки: ");
            int quantity = scanner.nextInt();

            if (quantity <= 0) {
                throw new IllegalArgumentException("Количество должно быть положительным числом");
            }

            if (quantity > flower.getQuantity()) {
                throw new IllegalArgumentException("Недостаточно товара на складе. Доступно: " + flower.getQuantity() + " шт.");
            }

            flower.setQuantity(flower.getQuantity() - quantity);
            double total = quantity * flower.getPrice();

            System.out.println("\n=== ПОКУПКА СОВЕРШЕНА ===");
            System.out.printf("Товар: %s (%s)%n", flower.getName(), flower.getColor());
            System.out.printf("Количество: %d шт.%n", quantity);
            System.out.printf("Сумма к оплате: %.2f руб.%n", total);
            System.out.println("Спасибо за покупку!");

            ActionLog actionLog = new ActionLog(ActionLog.Type.BUY, flower, quantity, number);
            actionLogs.push(actionLog);

        } catch (IndexOutOfBoundsException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: Пожалуйста, введите число.");
            scanner.nextLine();
        }
    }

    public void cancelLastAction(){
        if (actionLogs.isEmpty()){
            System.out.println("Нет действий для отмены.");
            return;
        }

        ActionLog actionLog = actionLogs.pop();

        switch (actionLog.getType()){
            case ADD:
                flowers.removeIf(flower -> flower.getName().equals(actionLog.getFlower().getName()));
                System.out.println("Отменено добавление цветка: " + actionLog.getFlower().getName());
                break;
            case EDIT:
                Flower oldFlower = flowers.get(actionLog.getIndex());
                oldFlower.setName(actionLog.getFlower().getName());
                oldFlower.setColor(actionLog.getFlower().getColor());
                oldFlower.setQuantity(actionLog.getFlower().getQuantity());
                oldFlower.setPrice(actionLog.getFlower().getPrice());
                oldFlower.setBloomSeason(actionLog.getFlower().getBloomSeason());
                oldFlower.setFragrance(actionLog.getFlower().getFragrance());
                System.out.println("Отменено редактирование цветка: " + actionLog.getFlower().getName());
                break;
            case DELETE:
                flowers.add(actionLog.getIndex(), actionLog.getFlower());
                System.out.println("Отменено удаление цветка: " + actionLog.getFlower().getName());
                break;
            case BUY:
                Flower oldFlower2 = flowers.get(actionLog.getIndex());
                int newQuantity = oldFlower2.getQuantity() + actionLog.getPurchasedQuantity();
                oldFlower2.setQuantity(newQuantity);
                System.out.println("Отменена покупка: " + actionLog.getPurchasedQuantity() + " шт. " + actionLog.getFlower().getName());
                break;
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