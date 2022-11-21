package exercises.towershanoi;

import java.util.*;

public class THanoi {
    private final List<Bar> bars = new ArrayList<>();

    public THanoi(int numberOfPlates) {
        for (int i = 0; i < 3; i++) {
            bars.add(new Bar(i));
        }
        for (int i = 0; i < numberOfPlates; i++) {
            bars.get(0).push(i, null, null);
        }
    }

    public void start() {
        while(bars.get(0).peek() != -1) {
            bars.get(1).push(bars.get(0).peek(), bars.get(0), bars.get(2));
            bars.get(0).pop();
        }
    }

    private class Bar {
        private final int id;
        private final Stack<Integer> plates = new Stack<>();

        public Bar(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public int peek() {
            if (plates.empty()) {
                return -1;
            }
            return plates.peek();
        }

        public boolean push(int newPlate, Bar pusherBar, Bar otherBar) {
            if (plates.search(newPlate) != -1) {
                System.out.printf("Bar %d already has this plate id %d, error\n", id, newPlate);
                return false;
            }
            int platesNumberToReturn = 0;
            while(true) {
                if (peek() < newPlate) {
                    plates.push(newPlate);
                    if (pusherBar != null) System.out.printf("From bar %d -> to bar %d accepts new plate so new plates are %s\n", pusherBar.getId(), id, plates);
                    break;
                }
                boolean success = otherBar.push(peek(), this, pusherBar);
                if (!success) {
                    System.out.printf("Bar %d failed to push its plate %d\n", id, peek());
                    return false;
                }
                success = pop();
                System.out.printf("Bar %d -> to bar %d pushed for more space and has %s\n", id, otherBar.getId(), plates);
                if (!success) {
                    System.out.printf("Bar %d failed to pop its plate %d\n", id, peek());
                    return false;
                }
                platesNumberToReturn++;
            }
            while(platesNumberToReturn != 0) {
                boolean success = push(otherBar.peek(), otherBar, pusherBar);
                if (!success) {
                    System.out.printf("Bar %d failed to return its plate %d\n", id, peek());
                    return false;
                }
                success = otherBar.pop();
                System.out.printf("Bar %d -> to bar %d has returned own and has %s\n", otherBar.getId(), id, plates);
                if (!success) {
                    System.out.printf("Bar %d failed to pop other bar to return its plate %d\n", id, peek());
                    return false;
                }
                platesNumberToReturn--;
            }
            if (pusherBar != null) System.out.printf("From bar %d -> to bar %d plates are %s\n", pusherBar.getId(), id, plates);
            return true;
        }

        public boolean pop() {
            if (plates.empty()) {
                System.out.println("Nothing to single pop");
                return false;
            }
            plates.pop();
            return true;
        }
    }
}
