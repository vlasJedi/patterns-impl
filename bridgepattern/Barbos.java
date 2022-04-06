package bridgepattern;

public class Barbos extends AbstractDog{
    public void bark() {
        System.out.println("I am Barbos and I can do such");
        this.getDogTraining().showWhatTrained();
    }
}
