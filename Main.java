import exercises.binarytree.BinaryTreeArrayBased;

public class Main {
    public static void main(String[] args) {
//         composition > inheritance
//         inheritance binds concrete parent class name, if need to extend parent class, but it is forbidden due to some reason,
//         then it requires to create another branch of subclasses - and it is a problem for already existing
//         even slight change of behavior in parent class can cause problems for children
//         polymorphism - each concrete class can be SET of types/interfaces that he implements
//         delegate - generally internal ref to object operation that will actually execute the request

        // abstract factory
//        AutoFactory autoFactory = new FactoryFor4DoorsAutos();
//        Sedan4Doors sedan4Doors = autoFactory.get4DoorsSedan();
//        sedan4Doors.printManuf();
//        sedan4Doors.openAllDoors();
        // bridge
//        AbstractDog dog1 = new Barbos();
//        dog1.setDogTraining(new JumpTrainedDog());
//        dog1.bark();
//        AbstractDog dog2 = new Jhonny();
//        dog2.setDogTraining(new SwimmingTrainedDog());
//        dog2.bark();
//        THanoi hanoi = new THanoi(3);
//        hanoi.start();

        BinaryTreeArrayBased tree = new BinaryTreeArrayBased();
        tree.print();
        tree.add(5);
        tree.print();
        tree.add(3);
        tree.print();
        tree.add(7);
        tree.print();
        tree.add(7);
        tree.print();
        tree.add(13);
        tree.add(6);
        tree.print();
    }
}
