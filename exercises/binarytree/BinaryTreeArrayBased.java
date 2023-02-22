package exercises.binarytree;

import java.util.Arrays;

public class BinaryTreeArrayBased {
//                     level-n most left      level-n most right  left child           right
//       0             2^0 - 1 = 0            2^(0 + 1) - 2 = 0
//      1 2            2^1 - 1 = 1            2^(1 + 1) - 2 = 2   index(0) * 2 + 1 = 1 index(0) * 2 + 2 = 2
//     34 56           2^2 - 1 = 3            2^(2 + 1) - 2 = 6   index(1) * 2 + 1 = 3 index(1) * 2 + 1 = 4
// 789 10 11 12 13 14  2^3 - 1 = 7
    private int[] nodes;

    public BinaryTreeArrayBased() {
        this.nodes = new int[1];
    }

    public boolean add(int value) {
        if (value <= 0) {
            return false;
        }
        return insert(value, 0);
    }

    private boolean insert(int value, int indexToCheck) {
        if (nodes.length <= indexToCheck) {
            nodes = realloc(indexToCheck);
        }
        if (nodes[indexToCheck] == 0) {
            nodes[indexToCheck] = value;
            return true;
        }
        if (nodes[indexToCheck] == value) {
            return true;
        }
        return nodes[indexToCheck] > value
                ? insert(value, leftIndex(indexToCheck))
                : insert(value, rightIndex(indexToCheck));
    }

    private int leftIndex(int index) {
        return (index << 1) + 1;
    }

    private int rightIndex(int index) {
        return leftIndex(index) + 1;
    }

    private int[] realloc(int targetIndex) {
        return Arrays.copyOf(nodes, getSize(targetIndex, nodes.length));
    }

    private int getSize(int targetIndex, int lastSize) {
        if (targetIndex < lastSize) {
            return lastSize;
        }
        return getSize(targetIndex, lastSize * 2);
    }

    public void print() {
        Arrays.stream(nodes).forEach(val -> System.out.printf("%d ", val));
        System.out.println("");
    }
}
