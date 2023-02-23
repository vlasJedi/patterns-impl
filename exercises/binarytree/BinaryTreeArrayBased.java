package exercises.binarytree;

import java.util.Arrays;
import java.util.function.Consumer;

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
// all LEFT children are ODD, RIGHT - EVEN

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
        if (res > 1) {
            int shouldLR = getTypeOfRotation(start);
            if (shouldLR == 0) {
                System.out.println("*** Need to balance left subtree for index: " + start + " as unbalanced");
                doRightRotation(start);
                System.out.println("*** Tree was balanced by right rotation");
            } else {
                System.out.println("*** Need to do left right rotation for index: " + start + " as unbalanced");
                doLeftRightRotation(start);
                System.out.println("*** Tree was balanced by left right rotation");
            }
            print();
        }
        if (res < -1) {
            int shouldRL = getTypeOfRotation(start);
            if (shouldRL == 0) {
                System.out.println("*** Need to balance right subtree for index: " + start + " as unbalanced");
                doLeftRotation(start);
                System.out.println("*** Tree was balanced by left rotation");
            } else {
                System.out.println("*** Need to do right left rotation for index: " + start + " as unbalanced");
                doRightLeftRotation(start);
                System.out.println("*** Tree was balanced by right left rotation");
            }
            print();
        }
        if (start == 0) return 0;
        leftHeight = calcHeightPostOrder(leftIndex(start));
        rightHeight = calcHeightPostOrder(rightIndex(start));
        res = leftHeight - rightHeight;
        return Math.abs(res) + 1;
    }

    // counterclockwise direction, right skewed tree
    // 3(-2)              4
    //   4(-1)     ->  3     5
    //     5(0)
    private void doLeftRotation(int indexStart) {
        int swap = nodes[indexStart];
        int rightIndex = rightIndex(indexStart);
        nodes[indexStart] = nodes[rightIndex];
        nodes[rightIndex] = nodes[rightIndex(rightIndex)];
        nodes[rightIndex(rightIndex)] = 0;
        //nodes[leftIndex(indexStart)] = swap;
        shiftDownChildren(leftIndex(indexStart), swap, true);
        shiftUpChildren(rightIndex(rightIndex));
    }

    private void shiftUpChildren(int newEmptyIndex) {
        nodes[newEmptyIndex] = 0;
        int leftChild = getChildIndexIfExists(newEmptyIndex, true);
        int rightChild = getChildIndexIfExists(newEmptyIndex, false);
        int newParent = getParent(newEmptyIndex);
        if (leftChild != -1) {
            nodes[leftIndex(newParent)] = nodes[leftChild];
            shiftUpChildren(leftChild);
        }
        if (rightChild != -1) {
            nodes[rightIndex(newParent)] = nodes[rightChild];
            shiftUpChildren(rightChild);
        }
    }

    private void shiftDownChildren(int start, int newValue, boolean toLeft) {
        if (nodes.length <= start) nodes = realloc(start);
        int swap = nodes[start];
        if (swap == 0) {
            nodes[start] = newValue;
            return;
        }
        int childIndex = toLeft ? leftIndex(start) : rightIndex(start);

        if (childIndex >= nodes.length) {
            nodes = realloc(childIndex);
        }
        shiftDownChildren(childIndex, swap, toLeft);

        int otherChild = getChildIndexIfExists(start, !toLeft);
        if (otherChild == -1) return;

        swap = nodes[otherChild];

        shiftDownChildren(childIndex, swap, !toLeft);
    }

    // counterclockwise direction, right skewed tree
    //     5(2)           4
    //   4(1)     ->   3     5
    // 3(0)
    private void doRightRotation(int indexStart) {
        int swap = nodes[indexStart];
        int leftIndex = leftIndex(indexStart);
        nodes[indexStart] = nodes[leftIndex];
        nodes[leftIndex] = nodes[leftIndex(leftIndex)];
        nodes[leftIndex(leftIndex)] = 0;
        nodes[rightIndex(indexStart)] = swap;
    }

    //      LR       RL
    //     6(2)    4(-2)
    //   4(-1)       6(1)
    //     5(0)    5(0)
    /**
     * Should be used only if DIF is 2 or -2
     * 0 - no rotation, -1 LR, 1 RL
     * @param index
     * @return
     */
    private int getTypeOfRotation(int index) {
        int leftIndex = getChildIndexIfExists(index, true);
        int rightIndex = getChildIndexIfExists(index, false);
        if (leftIndex == -1 && rightIndex != -1) {
            leftIndex = getChildIndexIfExists(rightIndex, true);
            rightIndex = getChildIndexIfExists(rightIndex, false);
            if (rightIndex == -1 && leftIndex != -1) {
                return 1;
            }
        }
        if (rightIndex == -1 && leftIndex != -1) {
            leftIndex = getChildIndexIfExists(leftIndex, true);
            rightIndex = getChildIndexIfExists(leftIndex, false);
            if (leftIndex == -1 && rightIndex != -1) {
                return -1;
            }
        }
        return 0;
    }

    private int getChildIndexIfExists(int parentIndex, boolean isLeft) {
        int childIndex = isLeft ? leftIndex(parentIndex) : rightIndex(parentIndex);
        return childIndex < nodes.length && nodes[childIndex] != 0 ? childIndex : -1;
    }

    //
    //     6(2)          6        5
    //   4(-1)     ->  5    -> 4     6
    //     5(0)     4
    private void doLeftRightRotation(int indexStart) {
        int leftIndex = leftIndex(indexStart);
        int swap = nodes[leftIndex];
        nodes[leftIndex] = nodes[rightIndex(leftIndex)];
        nodes[rightIndex(leftIndex)] = 0;
        int leftLeftIndex = leftIndex(leftIndex);
        // actually it will be allocated, but left for symmetry with RL
        if (leftLeftIndex >= nodes.length) {
            nodes = realloc(leftLeftIndex);
        }
        nodes[leftLeftIndex] = swap;
        // regular right
        doRightRotation(indexStart);
    }

    //
    //  4(-2)          4            5
    //     6(1)     ->  5    -> 4     6
    //   5(0)             6
    private void doRightLeftRotation(int indexStart) {
        int rightIndex = rightIndex(indexStart);
        int swap = nodes[rightIndex];
        nodes[rightIndex] = nodes[leftIndex(rightIndex)];
        nodes[leftIndex(rightIndex)] = 0;
        int rightRightIndex = rightIndex(rightIndex);
        if (rightRightIndex >= nodes.length) {
            nodes = realloc(rightRightIndex);
        }
        nodes[rightRightIndex] = swap;
        // regular left
        doLeftRotation(indexStart);
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
