import java.io.*;
import java.util.List;
import java.util.Scanner;

public class DataManager {
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
