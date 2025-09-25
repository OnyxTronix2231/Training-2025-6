package TrainingUtils;

import javax.swing.*;
import java.awt.event.*;

public class KeyBinder {
    private final char keyCh;
    private final Runnable func;

    public KeyBinder(char keyCh, Runnable func) {
        this.keyCh = keyCh;
        this.func = func;
        setupFrame();
    }

    private void setupFrame() {
        JFrame frame = new JFrame("KB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.setFocusable(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                if (key == keyCh && func != null) {
                    func.run();
                }
            }
        });

        frame.setVisible(true);
        frame.requestFocusInWindow();
    }

    public static void main(String[] args) {
        KeyBinder keyBinder = new KeyBinder('a',
                () -> {System.out.println("ploop");}
        );
    }
}