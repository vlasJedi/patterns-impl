package abstractfactory;

public class FactoryFor4DoorsAutos implements AutoFactory {

    @Override
    public Sedan4Doors get4DoorsSedan() {
        return new CarSedan("concrete benz sedan");
    }
}
