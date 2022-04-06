package bridgepattern;

public class Jhonny extends AbstractDog {
    public void bark() {
        System.out.println("I am Jhonny and I can do such");
        this.getDogTraining().showWhatTrained();
    }
}
