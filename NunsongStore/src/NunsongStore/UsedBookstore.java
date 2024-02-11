package NunsongStore;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsedBookstore extends JFrame {
	private UsedBooksUpload usedBooksUpload;
    private JLabel frametitle;
    private JButton sellButton;
    private JPanel bookPanel;
    private List<JButton> buttons;
    private int currentButtonIndex = 0; 
    private List<String> uploadedTitles; 
    private List<String> uploadedImagePaths;  

    public UsedBookstore() {
        setTitle("Used Book Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        usedBooksUpload = new UsedBooksUpload(this);

        frametitle = new JLabel("눈송중고서점");
        frametitle.setFont(new Font("Dialog", Font.BOLD, 24));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(frametitle);

        sellButton = new JButton("중고전공서적판매하기"); 
        sellButton.setFont(new Font("Dialog", Font.BOLD, 15));
        topPanel.add(sellButton);
        add(topPanel, BorderLayout.NORTH);

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	UsedBooksUpload usedBooksUpload = new UsedBooksUpload(UsedBookstore.this);
                usedBooksUpload.setVisible(true);
            }
        });

        bookPanel = new JPanel(new GridLayout(4, 8));

        buttons = new ArrayList<>();
        uploadedTitles = new ArrayList<>();
        uploadedImagePaths = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(120, 150));
            buttons.add(button);
            bookPanel.add(button);
            button.setVisible(false);
        }

        add(bookPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String title;
    private String subject;
    private String condition;
    private String price;
    private String desiredPrice;
    private String description;
    private String imagePath;

    public void updateBookInfo(UsedBooksUpload.BookInfo bookInfo) {
        this.title = bookInfo.getTitle();
        this.subject = bookInfo.getSubject();
        this.condition = bookInfo.getCondition();
        this.price = bookInfo.getPrice();
        this.desiredPrice = bookInfo.getDesiredPrice();
        this.description = bookInfo.getDescription();
        this.imagePath = bookInfo.getImagePath();

        BufferedImage uploadedImage = createImageIcon(imagePath);
        uploadedTitles.add(title);
        uploadedImagePaths.add(imagePath);

        if (currentButtonIndex < buttons.size()) {
            JButton currentButton = buttons.get(currentButtonIndex);
            currentButton.setIcon(new ImageIcon(uploadedImage));
            currentButton.setText(title);
            currentButton.setVisible(true);
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int index = buttons.indexOf(currentButton);
                    String bookInfo = "책 제목: " + uploadedTitles.get(index) + "\n" +
                            "과목명: " + subject + "\n" +
                            "책 상태: " + condition + "\n" +
                            "정가: " + price + "\n" +
                            "희망가: " + desiredPrice + "\n" +
                            "설명: " + description;

                    JOptionPane.showMessageDialog(UsedBookstore.this, bookInfo, "책 정보", JOptionPane.PLAIN_MESSAGE);
                }
            });
            currentButtonIndex++;
        }
    }


    public BufferedImage createImageIcon(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UsedBookstore();
            }
        });
    }
}
