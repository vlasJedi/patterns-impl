import bridgepattern.*;
import exercises.towershanoi.THanoi;

public class Main {
    public static void main(String[] args) {
//        AbstractDog dog1 = new Barbos();
//        dog1.setDogTraining(new JumpTrainedDog());
//        dog1.bark();
//        AbstractDog dog2 = new Jhonny();
//        dog2.setDogTraining(new SwimmingTrainedDog());
//        dog2.bark();
        THanoi hanoi = new THanoi(3);
        hanoi.start();
    }
}
