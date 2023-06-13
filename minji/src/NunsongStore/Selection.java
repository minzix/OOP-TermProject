package NunsongStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Selection extends JPanel {
    private JButton usedBookstoreButton;
    private JButton jointPurchaseButton;

    public Selection() {
        setLayout(new GridLayout(1, 2));

        usedBookstoreButton = new JButton("눈송중고서점"); //2-1. 중고눈송서점으로 이동하는 버튼
        usedBookstoreButton.setPreferredSize(new Dimension(300, 300));
        usedBookstoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // UsedBookstore 클래스로 연결하는 코드 작성
                UsedBookstore usedBookstore = new UsedBookstore();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Selection.this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(usedBookstore);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        add(usedBookstoreButton);

        jointPurchaseButton = new JButton("눈송공구서점"); //2-2. 눈송공구서점으로 이동하는 버튼
        jointPurchaseButton.setPreferredSize(new Dimension(300, 300));
        jointPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JointPurchase 클래스로 연결하는 코드 작성
                JointPurchase jointPurchase = new JointPurchase();
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Selection.this);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(jointPurchase);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        add(jointPurchaseButton);
    }
}
