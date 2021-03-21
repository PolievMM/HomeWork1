package ru.geekbrains.home_work_8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends JFrame {
    private final JTextField textField;
    private char operation;
    private double total;
    private boolean isFirstNum;

    public MyFrame() {

        setTitle("Калькулятор");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 280, 400);
        setResizable(false);
        isFirstNum = true;

        total = 0;

        JPanel jPanel = new JPanel();

        textField = new JTextField(20);
        textField.setEditable(false);

        jPanel.add(textField);

        JButton[] jKeys = new JButton[10];
        for (int i = 1; i < jKeys.length; i++) {
            jKeys[i] = new JButton(String.valueOf(i));
            jPanel.add(jKeys[i]);
            jKeys[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    action(e);
                }
            });
        }
        JButton zeroKey = new JButton("0");
        jPanel.add(zeroKey);
        JButton plusKey = new JButton("+");
        jPanel.add(plusKey);
        JButton minusKey = new JButton("-");
        jPanel.add(minusKey);
        JButton multiplyKey = new JButton("*");
        jPanel.add(multiplyKey);
        JButton splitKey = new JButton("/");
        jPanel.add(splitKey);
        JButton parityKey = new JButton("=");
        jPanel.add(parityKey);
        zeroKey.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        plusKey.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        minusKey.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        multiplyKey.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        splitKey.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(e);
            }
        });

        parityKey.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculation(e);
            }
        });


        add(jPanel);

        setVisible(true);


    }


    private void calculation(ActionEvent e) {
        String text = textField.getText();
        String calcNum = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '-' || ch == '+' || ch == '*' || ch == '/') {
                double currentVal = Double.parseDouble(calcNum);
                if (isFirstNum) {
                    total += currentVal;
                    isFirstNum = false;
                } else {
                    if (operation == '-') {
                        total -= currentVal;
                    } else if (operation == '+') {
                        total += currentVal;
                    } else if (operation == '*') {
                        total *= currentVal;
                    } else if (operation == '/') {
                        total /= currentVal;
                    }
                }
                calcNum = "";
                operation = ch;
                continue;
            }
            calcNum += ch;


        }

        double currentVal = Double.parseDouble(calcNum);
        if (operation == '-') {
            total -= currentVal;
        } else if (operation == '+') {
            total += currentVal;
        } else if (operation == '*') {
            total *= currentVal;
        } else if (operation == '/') {
            total /= currentVal;
        }
        isFirstNum = true;
        textField.setText(String.valueOf(total));
        total = 0;
    }

    public void action(ActionEvent event) {

        textField.setText(textField.getText() + event.getActionCommand());
    }


}
