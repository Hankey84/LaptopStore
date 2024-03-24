import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    // public 

    public void filterLaptops(Map<String, Object> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean passFilter = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case "ОЗУ":
                        int minRAM = (int) value;
                        if (laptop.getRam() < minRAM) {
                            passFilter = false;
                        }
                        break;
                    case "Объём SDD":
                        int minStorageCapacity = (int) value;
                        if (laptop.getStorage() < minStorageCapacity) {
                            passFilter = false;
                        }
                        break;
                    case "Операционная система":
                        String requiredOS = (String) value;
                        if (!laptop.getSystem().equals(requiredOS)) {
                            passFilter = false;
                        }
                        break;
                    case "Цвет":
                        String requiredColor = (String) value;
                        if (!laptop.getColor().equals(requiredColor)) {
                            passFilter = false;
                        }
                        break;
                    case "Цена":
                        double minPrice = (double) value;
                        if (laptop.getPrice() < minPrice) {
                            passFilter = false;
                        }
                        break;
                    case "Год производства":
                        int minYear = (int) value;
                        if (laptop.getYear() < minYear) {
                            passFilter = false;
                        }
                        break;
                    default:
                    
                        break;
                }
            }

            if (passFilter) {
                filteredLaptops.add(laptop);
            }
        }
        System.out.println("Список отобранных ноутбуков по фильтру " + filters + " :");
        for (Laptop laptop : filteredLaptops) {
            System.out.println("Модель: " + laptop.getModel()+ " "+ laptop.getYear() + " г.в., RAM: " + laptop.getRam() +
                    ", SDD: " + laptop.getStorage() + ", Опер.cистема: " + laptop.getSystem() +
                    ", Цвет: " + laptop.getColor() + ", Цена: " + laptop.getPrice()+ " руб.");
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop( new Laptop("Lenovo L340",2019, "черный", "AMD Ryzen 3", 8, 256, "Linux", 36000));
        store.addLaptop( new Laptop("Samsug Book3 Ultra",2022, "графитовый", "Intell Core 7", 32, 512, "Windows", 310000));
        store.addLaptop( new Laptop("Asus Strix G15",2023, "серый", "AMD Ryzen 9", 64, 1024,"n/a", 240000));
        store.addLaptop( new Laptop("Asus Vivobook",2020, "черный", "Intell Core5", 32, 256, "Linux", 52000));
        store.addLaptop( new Laptop("Acer Aspire A5",2021, "серый", "AMD Ryzen 5", 16, 256, "Windows", 54000.00));
        store.addLaptop( new Laptop("Acer Aspire A3",2019, "синий", "AMD Ryzen 3", 8, 256, "Windows", 30000.00));
        


    Scanner scanner = new Scanner(System.in);

        Map<String, Object> filters = new HashMap<>();
        System.out.println("Выберите критерий фильтрации:");
        System.out.println("1 - По ОЗУ");
        System.out.println("2 - По объёму SDD");
        System.out.println("3 - Операционной системе");
        System.out.println("4 - Цвету");
        System.out.println("5 - Цене");
        System.out.println("6 - Году производства");
        System.out.println("9 - Для выхода из программы");
        int criterion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (criterion) {
            case 1:
                System.out.print("Введите минимальный объём  ОЗУ: ");
                int minRAM = scanner.nextInt();
                filters.put("ОЗУ", minRAM);
                break;
            case 2:
                System.out.print("Введите  минимальный объем SDD-диска: ");
                int minStorageCapacity = scanner.nextInt();
                filters.put("Объём SDD", minStorageCapacity);
                break;
            case 3:
                System.out.print("Введите требуемую опер.систему: ");
                String requiredOS = scanner.nextLine();
                filters.put("Операционная система", requiredOS);
                break;
            case 4:
                System.out.print("Введите требуемый цвет: ");
                String requiredColor = scanner.nextLine();
                filters.put("Цвет", requiredColor);
                break;
            case 5:
                System.out.print("Введите  минимальную цену(руб.): ");
                double minPrice = scanner.nextDouble();
                filters.put("Цена", minPrice);
                break;
            case 6:
                System.out.print("Введите  год производства: ");
                int minYear = scanner.nextInt();
                filters.put("Год производства", minYear);
                break;
            case 9:
                System.out.println("Программа завершена"); 
                System.exit(1);               
                //break;
            default:
                System.out.println("Неправильный выбор!");
                return;
        }
        
        store.filterLaptops(filters);
        scanner.close();
}
}
    