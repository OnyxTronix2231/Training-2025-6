package L8.lecture;

import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        SlotMachine slot = new SlotMachine(() -> (int) (Math.random() * 100));
    }
}
