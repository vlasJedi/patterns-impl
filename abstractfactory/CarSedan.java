package abstractfactory;

public class CarSedan extends Sedan4Doors {

    public CarSedan(String name) {
        super(name);
    }

    @Override
    public void printManuf() {
        System.out.printf("4 doors from %s\r\n", super.manufacturer);
    }

    @Override
    public void openAllDoors() {
        System.out.println("Be aware that all 4 doors are opened");
    }
}
