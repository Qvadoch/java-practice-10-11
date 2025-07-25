import java.io.*;
import java.util.List;
import java.util.Scanner;

public class DataManager {
    private static final String BINARY_FILE_PATH = "flower_shop_data.bin";

    public static void saveToBinaryFile(Scanner scanner, List<Flower> flowers, List<UserModel> users) {
        System.out.print("Введите путь к бинарному файлу для сохранения: ");
        String path = scanner.nextLine();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeInt(flowers.size());
            for (Flower flower : flowers) {
                oos.writeObject(flower);
            }

            oos.writeInt(users.size());
            for (UserModel user : users) {
                oos.writeObject(user);
            }

            System.out.println("Данные успешно сохранены в бинарный файл: " + path);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в бинарный файл: " + e.getMessage());
        }
    }

    public static void loadFromBinaryFile(Scanner scanner, List<Flower> flowers, List<UserModel> users) {
        System.out.print("Введите путь к бинарному файлу для загрузки: ");
        String path = scanner.nextLine();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            flowers.clear();
            users.clear();

            int flowerCount = ois.readInt();
            for (int i = 0; i < flowerCount; i++) {
                Flower flower = (Flower) ois.readObject();
                flowers.add(flower);
            }

            int userCount = ois.readInt();
            for (int i = 0; i < userCount; i++) {
                UserModel user = (UserModel) ois.readObject();
                users.add(user);
            }

            System.out.println("Данные успешно загружены из бинарного файла: " + path);
            System.out.println("Загружено цветов: " + flowers.size());
            System.out.println("Загружено пользователей: " + users.size());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: бинарный файл не найден: " + path);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке из бинарного файла: " + e.getMessage());
        }
    }

    public static void autoSaveData(List<Flower> flowers, List<UserModel> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BINARY_FILE_PATH))) {

            oos.writeInt(flowers.size());
            for (Flower flower : flowers) {
                oos.writeObject(flower);
            }

            oos.writeInt(users.size());
            for (UserModel user : users) {
                oos.writeObject(user);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при автоматическом сохранении данных: " + e.getMessage());
        }
    }

    public static void autoLoadData(List<Flower> flowers, List<UserModel> users) {
        File file = new File(BINARY_FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BINARY_FILE_PATH))) {
            flowers.clear();
            users.clear();

            int flowerCount = ois.readInt();
            for (int i = 0; i < flowerCount; i++) {
                Flower flower = (Flower) ois.readObject();
                flowers.add(flower);
            }

            int userCount = ois.readInt();
            for (int i = 0; i < userCount; i++) {
                UserModel user = (UserModel) ois.readObject();
                users.add(user);
            }

            System.out.println("Автоматически загружено цветов: " + flowers.size());
            System.out.println("Автоматически загружено пользователей: " + users.size());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при автоматической загрузке данных: " + e.getMessage());
        }
    }

    public static void importFromFile(Scanner scanner, List<Flower> flowers) throws IOException {
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();
        File file = new File(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    flowers.add(new Flower(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Double.parseDouble(parts[3]),
                            parts[4],
                            parts[5]
                    ));
                } else {
                    System.out.println("Ошибка: некорректные данные в файле.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректные числовые данные в файле.");
        }
    }

    public static void exportToFile(Scanner scanner, List<Flower> flowers) throws IOException{
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();
        File file = new File(path);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            for (Flower flower : flowers){
                bufferedWriter.write(flower.getName() + "," + flower.getColor() + "," + flower.getQuantity() + ","
                        + flower.getPrice() + "," + flower.getBloomSeason() + "," + flower.getFragrance() + "\n");
            }
        }
    }
}
