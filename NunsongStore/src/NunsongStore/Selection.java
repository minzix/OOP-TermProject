package NunsongStore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Selection extends JPanel {
    private JButton usedBookstoreButton;
    private JButton jointPurchaseButton;

    public Selection() {
        setLayout(new GridLayout(1, 2));

        ImageIcon usedBookstoreIcon = new ImageIcon("src/image/usedbook.jpg");
        ImageIcon jointPurchaseIcon = new ImageIcon("src/image/jointp.jpg");

        usedBookstoreButton = new JButton(usedBookstoreIcon);
        usedBookstoreButton.setPreferredSize(new Dimension(100, 100));
        usedBookstoreButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        usedBookstoreButton.setHorizontalTextPosition(SwingConstants.CENTER);
        usedBookstoreButton.setText("눈송중고서점");
        Font buttonFont = usedBookstoreButton.getFont();
        Font largerFont = buttonFont.deriveFont(buttonFont.getSize() + 50f);
        usedBookstoreButton.setFont(largerFont);

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

        jointPurchaseButton = new JButton(jointPurchaseIcon);
        jointPurchaseButton.setPreferredSize(new Dimension(100, 100));
        jointPurchaseButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        jointPurchaseButton.setHorizontalTextPosition(SwingConstants.CENTER);
        jointPurchaseButton.setText("눈송공구서점");
        jointPurchaseButton.setFont(largerFont);
        jointPurchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JointPurchase 클래스로 연결하는 코드 작성
                JointPurchase jointPurchase = new JointPurchase();
                jointPurchase.createAndShowGUI();
            }
        });
        add(jointPurchaseButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Selection());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
} 
