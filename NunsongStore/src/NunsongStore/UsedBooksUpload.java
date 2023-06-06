package NunsongStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.Ellipse2D;

public class UsedBooksUpload extends JFrame {
    private JLabel titleLabel;
    private JTextField titleTextField;
    private JLabel subjectLabel;
    private JComboBox<String> subjectComboBox;
    private JLabel conditionLabel;
    private JComboBox<String> conditionComboBox;
    private JLabel priceLabel;
    private JTextField priceTextField;
    private JLabel desiredPriceLabel;
    private JTextField desiredPriceTextField;
    private JLabel descriptionLabel;
    private JTextArea descriptionTextArea;
    private JButton uploadButton;
    private DefaultListModel<String> uploadedListModel;
    private JList<String> uploadedList;

    public UsedBooksUpload() {
        setTitle("Used Books Upload");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // 책 제목
        titleLabel = new JLabel("책 제목");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(titleLabel, gbc);

        titleTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(titleTextField, gbc);

        // 과목명
        subjectLabel = new JLabel("과목명");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(subjectLabel, gbc);

        String[] subjects = {
                "IT기술의 이해",
                "프로그래밍입문 (Python)(필수)",
                "IT수학",
                "기초프로그래밍 (C)(필수)",
                "객체지향프로그래밍 (Java)(필수)",
                "웹프로그래밍기초(HTML5+JavaScript)(필수)",
                "데이터구조(필수)",
                "IT기기구조",
                "C++프로그래밍",
                "모바일프로그래밍 (Android)",
                "서버운영및보안",
                "웹프로그래밍응용",
                "데이터베이스",
                "컴퓨터그래픽프로그래밍",
                "알고리즘입문",
                "영상처리및응용",
                "UI/UX설계(캡스톤 디자인)",
                "IT소프트웨어공학",
                "가상및증강현실응용",
                "네트워크",
                "운영체제원리",
                "센서프로그래밍",
                "IoT응용(캡스톤 디자인)",
                "IT기술및산업동향",
                "데이터마이닝개론",
                "스마트서비스",
                "지능형소프트웨어",
                "HCI개론",
                "데이터분석및활용",
                "컴퓨터비전",
                "클라우드컴퓨팅"
        };

        subjectComboBox = new JComboBox<>(subjects);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(subjectComboBox, gbc);

        // 책 상태
        conditionLabel = new JLabel("책 상태");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(conditionLabel, gbc);

        String[] conditions = {
                "최상",
                "상",
                "중",
        };

        conditionComboBox = new JComboBox<>(conditions);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(conditionComboBox, gbc);

        // 물음표 이미지 넣기
        ImageIcon conditionImageIcon = createCircularImageIcon("src/image/questionmark.PNG", 20); 
        JLabel conditionImageLabel = new JLabel(conditionImageIcon);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(conditionImageLabel, gbc); 

        conditionImageLabel.setToolTipText("");

        // 정가
        priceLabel = new JLabel("정가");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(priceLabel, gbc);

        priceTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(priceTextField, gbc);

        // 희망판매가
        desiredPriceLabel = new JLabel("희망판매가");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(desiredPriceLabel, gbc);

        desiredPriceTextField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(desiredPriceTextField, gbc);

        // 설명
        descriptionLabel = new JLabel("설명");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(descriptionLabel, gbc);

        descriptionTextArea = new JTextArea(5, 20);
        descriptionTextArea.setLineWrap(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(descriptionScrollPane, gbc);

        // 업로드버튼
        uploadButton = new JButton("업로드");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(uploadButton, gbc);

        // 최상, 상, 중 상태 설명
        conditionImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                conditionImageLabel.setToolTipText("<html>최상: 새것에 가까운 책, 변색이나 찢어진 흔적, <br>닳은 흔적, 낙서나 얼룩이 없는 상태이다. <br>상: 약간의 사용감은 있으나 깨끗한 책.  희미한 변색이나 작은 얼룩이 있고, <br>찢어진 흔적은 없으며 밑줄을 그은 정도의 필기가 되어있는 상태이다.<br> 중: 전체적인 변색이나 오염이 있고, <br>찢어진 흔적이 있으며 낙서가 있음.<br></html>");
            }
        });

        // 저장하기
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookInfo = "제목: " + titleTextField.getText() + ", " +
                        "과목: " + subjectComboBox.getSelectedItem() + ", " +
                        "책 상태: " + conditionComboBox.getSelectedItem() + ", " +
                        "정가: " + priceTextField.getText() + ", " +
                        "희망가: " + desiredPriceTextField.getText() + ", " +
                        "설명: " + descriptionTextArea.getText();

                uploadedListModel.addElement(bookInfo);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private ImageIcon createCircularImageIcon(String imagePath, int size) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            BufferedImage resizedImage = resizeImage(image, size, size);
            BufferedImage circularImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = circularImage.createGraphics();
            g2d.setClip(new Ellipse2D.Float(0, 0, size, size));
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();

            return new ImageIcon(circularImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UsedBooksUpload();
            }
        });
    }
}