package L4.training;

public class Car {
    static final int AGE_TO_BECOME_RARE = 15;
    static int carCount = 0;
    static int rareCarCount = 0;
    String licensePlate;
    Manufacturer manufacturer;
    Date buildDate;
    double price;
    boolean isRare;


    public Car(String licensePlate, Manufacturer manufacturer, Date buildDate, double price) {
        this.licensePlate = licensePlate;
        this.manufacturer = manufacturer;
        this.buildDate = buildDate;
        this.price = price;
        isRare = 2025 - buildDate.getYear() >= AGE_TO_BECOME_RARE;
        carCount++;
    }

    public static int getCarCount() {
        return carCount;
    }

    public static void setCarCount(int carCount) {
        Car.carCount = carCount;
    }

    public void addAsRare() {
        if (2025 - buildDate.getYear() >= AGE_TO_BECOME_RARE && !isRare) {
            isRare = true;
            rareCarCount++;
        }
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void printAge() {
        int age = 2025 - buildDate.getYear();
        System.out.println("The car is " + age + " years old.");
    }


    public enum Manufacturer {
        Ford,
        Chevrolet,
        Toyota,
        Nissan,
        BMW,
        Volkswagen
    }
}
