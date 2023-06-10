package NunsongStore;

import javax.imageio.ImageIO;
import javax.swing.*;

import NunsongStore.UsedBooksUpload.BookInfo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsedBookstore extends JFrame {
    private JLabel frametitle;
    private JButton sellButton;
    private JPanel bookPanel;
    private List<JButton> buttons;
    private int currentButtonIndex = 0;  // 현재 버튼 인덱스
    private List<String> uploadedTitles;  // 업로드된 책 제목 목록
    private List<String> uploadedImagePaths;  // 업로드된 책 이미지 경로 목록

    public UsedBookstore() {
        setTitle("Used Book Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 상단 제목과 판매 버튼
        frametitle = new JLabel("눈송중고서점");
        frametitle.setFont(new Font("Dialog", Font.BOLD, 24));
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(frametitle);

        sellButton = new JButton("중고전공서적판매하기");
        sellButton.setFont(new Font("Dialog", Font.BOLD, 24));
        topPanel.add(sellButton);
        add(topPanel, BorderLayout.NORTH);

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsedBooksUpload usedBooksUpload = new UsedBooksUpload();
                //usedBooksUpload.setUploadListener(UsedBookstore.this);
                usedBooksUpload.setVisible(true);
            }
        });

        // 책 버튼 패널
        bookPanel = new JPanel(new GridLayout(4, 8));

        // 책 버튼 생성
        buttons = new ArrayList<>();
        uploadedTitles = new ArrayList<>();
        uploadedImagePaths = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(120, 150));
            buttons.add(button);
            bookPanel.add(button);
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

    public void getBookInfo(BookInfo bookInfo) {
        this.title = bookInfo.getTitle();
        this.subject = bookInfo.getSubject();
        this.condition = bookInfo.getCondition();
        this.price = bookInfo.getPrice();
        this.desiredPrice = bookInfo.getDesiredPrice();
        this.description = bookInfo.getDescription();
        this.imagePath = bookInfo.getImagePath();

        // 임시로 이미지와 제목 저장
        BufferedImage uploadedImage = createImageIcon(imagePath);
        uploadedTitles.add(title);
        uploadedImagePaths.add(imagePath);

        // 버튼에 이미지와 제목 삽입
        if (currentButtonIndex < buttons.size()) {
            JButton currentButton = buttons.get(currentButtonIndex);
            currentButton.setIcon(new ImageIcon(uploadedImage));
            currentButton.setText(title);

            // 버튼 이벤트 리스너 등록
            currentButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // UsedBooksUpload에서 받아온 정보를 팝업 창으로 출력
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

            // 다음 버튼 인덱스로 이동
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
                UsedBookstore usedBookstore = new UsedBookstore();
            }
        });
    }
}