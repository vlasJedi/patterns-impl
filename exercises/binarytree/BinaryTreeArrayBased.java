package exercises.binarytree;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class BinaryTreeArrayBased {
//                     level-n most left      level-n most right
//       0             2^0 - 1 = 0            2^(0 + 1) - 2 = 0
//      1 2            2^1 - 1 = 1            2^(1 + 1) - 2 = 2
//     34 56           2^2 - 1 = 3            2^(2 + 1) - 2 = 6
// 789 10 11 12 13 14  2^3 - 1 = 7

//                     left child           right child
//       0
//      1 2            index(0) * 2 + 1 = 1 index(0) * 2 + 2 = 2
//     34 56           index(1) * 2 + 1 = 3 index(1) * 2 + 1 = 4
// 789 10 11 12 13 14

// parent (index - 1) / 2 (integer division)

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

    public int getHeight() {
        if (nodes.length == 1) {
            return nodes[0] == 0 ? -1 : 0;
        }
        int index = nodes.length - 1;
        while(nodes[index] == 0) index--;
        return calcHeight(index, 0);
    }

    // uses tail recursion
    private int calcHeight(int start, int current) {
        if (start == 0) return current;
        // tail recursion - last statement is ONLY recursion call
        return calcHeight(getParent(start), ++current);
    }

    private int calcHeightPostOrder(int start) {
        if (start >= nodes.length || nodes[start] == 0) {
            return 0;
        }
        int leftHeight = calcHeightPostOrder(leftIndex(start));
        int rightHeight = calcHeightPostOrder(rightIndex(start));
        int res = leftHeight - rightHeight;
        int toReturn = (res > 0 ? leftHeight : rightHeight) + 1;
        if (res > 0) {
            if (res > 1) {
                System.out.println("*** Need to balance left subtree for index: " + start + " as unbalancing is: " + res);
            }
        }
        if (res < -1) {
            System.out.println("*** Need to balance right subtree for index: " + start + " as unbalancing is: " + res);
        }
        return toReturn;
    }

    private int getParent(int index) {
        if (index == 0) return -1;
        return (index - 1) / 2;
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

    public void balanceTreeIfNeeded() {
        calcHeightPostOrder(0);
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

    /**
     * At first left subtree, then node, then right subtree
     * it is increasing order by value, but it can be switched
     * by changing order of left/right statements
     */
    public void traverseInOrder(int index) {
        if (index >= nodes.length || nodes[index] == 0) {
            return;
        }
        traverseInOrder(rightIndex(index));
        System.out.println("*** In order visited index: " + index + " with value: " + nodes[index]);
        traverseInOrder(leftIndex(index));
    }

    /**
     * Used to copy tree starting from root
     * is increasing order by index
     * @param index
     */
    public void traversePreOrder(int index, Consumer<Integer> callback) {
        if (index >= nodes.length || nodes[index] == 0) {
            return;
        }
        callback.accept(index);
        System.out.println("*** Pre order visited index: " + index + " with value: " + nodes[index]);
        traversePreOrder(leftIndex(index), callback);
        traversePreOrder(rightIndex(index), callback);
    }

    /**
     * Used for deletion of tree starting from children
     * @param index
     */
    public void traversePostOrder(int index) {
        if (index >= nodes.length || nodes[index] == 0) {
            return;
        }
        traversePostOrder(rightIndex(index));
        traversePostOrder(leftIndex(index));
        System.out.println("*** Post order visited index: " + index + " with value: " + nodes[index]);
    }

    public void print() {
        Arrays.stream(nodes).forEach(val -> System.out.printf("%d ", val));
        System.out.println("");
    }
}
