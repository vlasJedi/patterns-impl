import exercises.binarytree.BinaryTreeArrayBased;
import exercises.linkedlists.LinkedLists;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

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
        // regular right.left rotations
//        tree.add(4);
//        tree.add(7);
//        tree.add(8);
//        tree.add(9);
//        tree.add(10);
//        tree.add(2);
//        tree.add(1);
        // RL
//        tree.add(6);
//        tree.add(3);
//        tree.add(5);
//        tree.add(4);
//        tree.add(7);
        // LR
//        tree.add(6);
//        tree.add(10);
//        tree.add(7);
//        tree.add(9);
//        tree.add(5);
//        tree.add(4);
        // LR + RL
//        tree.add(6);
//        tree.add(10);
//        tree.add(7);
//        tree.add(9);
//        tree.add(3);
//        tree.add(5);
//        tree.add(4);

        tree.print();
        // tree.traversePostOrder(0);
        // left rotation balancing

        LinkedLists linkedLists = new LinkedLists();
        int[] arr = new int[]{-10,-10,-9,-4,1,6,6};
        LinkedLists.ListNode node0 = new LinkedLists.ListNode(arr[0]);
        LinkedLists.ListNode node1 = node0;
        for(int i = 1; i < arr.length; i++) {
            node1.next = new LinkedLists.ListNode(arr[i]);
            node1 = node1.next;
        }
        LinkedLists.ListNode node =
                linkedLists.mergeTwoLists(node0, new LinkedLists.ListNode(-7));
        while(node != null) {
            System.out.println("*** Node" + node.val);
            node = node.next;
        }
        System.out.println("*** End");
    }
}
