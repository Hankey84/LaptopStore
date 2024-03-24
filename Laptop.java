import java.util.Objects;

public class Laptop {
    private String brand;
    private int year;
    private String color;
    private String cpu;
    private int ram;
    private int storage;
    private String system;
    private double price;

    public Laptop(String brand, int year, String color, String cpu, int ram, int storage, String system, double price) {
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.system = system;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public String getCpu() {
        return cpu;
    }
    public String getColor() {
        return color;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getSystem() {
        return system;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {        
        if (this == obj) return true;
        if (this == null  || getClass() != obj.getClass())  return false;
        Laptop laptop = (Laptop) obj;
        return (this.brand == laptop.brand) && (this.year == laptop.year) && ( this.color == laptop.color) 
        && (this.cpu == laptop.cpu) && (this.ram == laptop.ram) && (this.storage == laptop.storage)
        && (this.system == laptop.system) && (this.price == laptop.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, year, color, cpu, ram, storage, system, price);
    }
}
