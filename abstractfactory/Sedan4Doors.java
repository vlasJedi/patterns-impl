package abstractfactory;

//abstracted one of factory's product
public abstract class Sedan4Doors {
    protected String manufacturer;

    public Sedan4Doors(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract void printManuf();
    public abstract void openAllDoors();
}
