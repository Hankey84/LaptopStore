import java.util.*;

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Set<Object> extractFieldValues(String fieldName) {
        Set<Object> fieldValues = new TreeSet<>();
        for (Laptop laptop : laptops) {
            switch (fieldName) {
                case "brand":
                    fieldValues.add(laptop.getBrand());
                    break;
                case "year":
                    fieldValues.add(laptop.getYear());
                    break;
                case "color":
                    fieldValues.add(laptop.getColor());
                    break;
                case "cpu":
                    // fieldValues.add(laptop.getCpu());
                    break;
                case "ram":
                    fieldValues.add(laptop.getRam());
                    break;
                case "storage":
                    fieldValues.add(laptop.getStorage());
                    break;
                case "system":
                    fieldValues.add(laptop.getSystem());
                    break;
                // case "price":
                // fieldValues.add(laptop.getPrice());
                // break;
                default:
                    // Неизвестное поле
                    // System.out.println("Неизвестное поле: " + fieldName);
                    break;
            }
        }
        return fieldValues;
    }

    public void filterLaptops(Map<String, Object> filters) {
        List<Laptop> filteredLaptops = new ArrayList<>();

        for (Laptop laptop : laptops) {
            boolean passFilter = true;

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case "Модель":
                        String requiredBrand = (String) value;
                        if (!laptop.getBrand().equals(requiredBrand)) {
                            passFilter = false;
                        }
                        break;
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
        System.out.println("Список отобранных ноутбуков по фильтру " + filters + ":");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(
                    "Модель: " + laptop.getBrand() + " " + laptop.getYear() + " г.в., RAM: " + laptop.getRam() +
                            ", SDD: " + laptop.getStorage() + ", Опер.cистема: " + laptop.getSystem() +
                            ", Цвет: " + laptop.getColor() + ", Цена: " + laptop.getPrice() + " руб.");
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.addLaptop(new Laptop("Lenovo", 2019, "Black", "AMD Ryzen 3", 8, 256, "Linux", 36000));
        store.addLaptop(new Laptop("Samsung", 2022, "Graphite", "Intell Core 7", 32, 512, "Windows", 310000));
        store.addLaptop(new Laptop("Asus", 2023, "Gray", "AMD Ryzen 9", 64, 1024, "n/a", 240000));
        store.addLaptop(new Laptop("Asus", 2020, "Black", "Intell Core5", 32, 256, "Linux", 52000));
        store.addLaptop(new Laptop("Acer", 2021, "Gray", "AMD Ryzen 5", 16, 256, "Windows", 54000.00));
        store.addLaptop(new Laptop("Acer", 2019, "Blue", "AMD Ryzen 3", 8, 256, "Windows", 30000.00));
        store.addLaptop(new Laptop("MacBook", 2022, "Gray", "Apple M5 chip", 32, 512, "Mac Os", 400000.00));
        store.addLaptop(new Laptop("Acer", 2023, "Graphite", "AMD Ryzen 7", 32, 1024, "Windows", 155000.00));

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        
        do {
            Map<String, Object> filters = new HashMap<>();
            System.out.println("Выберите критерий фильтрации:");
            System.out.println("1 - По модели");
            System.out.println("2 - Году производства");
            System.out.println("3 - Цене");
            System.out.println("4 - По ОЗУ");
            System.out.println("5 - По объёму SDD");
            System.out.println("6 - Операционной системе");
            System.out.println("7 - Цвету");
            System.out.println("9 - Для выхода из программы");
            int criterion = scanner.nextInt();
            scanner.nextLine();

            switch (criterion) {
                case 1:
                    Set<Object> brandValues = store.extractFieldValues("brand");
                    System.out.print("Введите требуемую модель: " + brandValues + ": ");
                    String requiredBrand = scanner.nextLine();
                    filters.put("Модель", requiredBrand);
                    break;
                case 2:
                    System.out.print("Введите  год производства: ");
                    int minYear = scanner.nextInt();
                    filters.put("Год производства", minYear);
                    break;
                case 3:
                    System.out.print("Введите  минимальную цену(руб.): ");
                    double minPrice = scanner.nextDouble();
                    filters.put("Цена", minPrice);
                    break;
                case 4:
                    Set<Object> ramValues = store.extractFieldValues("ram");
                    System.out.print("Введите минимальный объём  ОЗУ из " + ramValues + ": ");
                    int minRAM = scanner.nextInt();
                    filters.put("ОЗУ", minRAM);
                    break;
                case 5:
                    Set<Object> storageValues = store.extractFieldValues("storage");
                    System.out.print("Введите  минимальный объем SDD-диска из " + storageValues + ": ");
                    int minStorageCapacity = scanner.nextInt();
                    filters.put("Объём SDD", minStorageCapacity);
                    break;
                case 6:
                    Set<Object> systemValues = store.extractFieldValues("system");
                    System.out.print("Введите требуемую опер.систему из " + systemValues + ": ");
                    String requiredOS = scanner.nextLine();
                    filters.put("Операционная система", requiredOS);
                    break;
                case 7:
                    Set<Object> colorValues = store.extractFieldValues("color");
                    System.out.print("Введите требуемый цвет из " + colorValues + ": ");
                    String requiredColor = scanner.nextLine();
                    filters.put("Цвет", requiredColor);
                    break;
                case 9:
                    System.out.println("Программа завершена");
                    scanner.close();
                    System.exit(1);
                    // exit = true;
                    // break;
                default:
                    System.out.println("Неправильный выбор!");
                    return;
            }

            store.filterLaptops(filters);
            System.out.println("Нажмите любую клавишу для продолжения...");
            scanner.nextLine();
        } while (!exit);
        // scanner.close();
    }
}
