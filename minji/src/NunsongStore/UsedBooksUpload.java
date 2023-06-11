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
import javax.swing.filechooser.FileNameExtensionFilter;


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
    private JLabel imageLabel;
    private JButton insertImageButton;
    private String selectedImagePath;

    public UsedBooksUpload() {
        setTitle("Used Books Upload");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

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

        subjectLabel = new JLabel("과목명");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(subjectLabel, gbc);

        String[] subjects = {
                "IT기술의 이해",
                "프로그래밍입문 (Python)(필수)",
                "IT수학",
                "클라우드컴퓨팅"
        };

        subjectComboBox = new JComboBox<>(subjects);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(subjectComboBox, gbc);

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

        ImageIcon conditionImageIcon = createCircularImageIcon("src/image/q.PNG", 20);
        JLabel conditionImageLabel = new JLabel(conditionImageIcon);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(conditionImageLabel, gbc);

        conditionImageLabel.setToolTipText("<html>최상: 새것에 가까운 책, 변색이나 찢어진 흔적, <br>닳은 흔적, 낙서나 얼룩이 없는 상태이다. <br>상: 약간의 사용감은 있으나 깨끗한 책.  희미한 변색이나 작은 얼룩이 있고, <br>찢어진 흔적은 없으며 밑줄을 그은 정도의 필기가 되어있는 상태이다.<br> 중: 전체적인 변색이나 오염이 있고, <br>찢어진 흔적이 있으며 낙서가 있음.<br></html>");

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

        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setBackground(Color.LIGHT_GRAY);
        imageLabel.setOpaque(true);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectImage();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        insertImageButton = new JButton("이미지 삽입");
        insertImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(insertImageButton, gbc);

        uploadButton = new JButton("업로드"); //4. 창을 닫는 버튼
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(uploadButton, gbc);

        uploadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookInfo bookInfo = new BookInfo(
                        titleTextField.getText(),
                        (String) subjectComboBox.getSelectedItem(),
                        (String) conditionComboBox.getSelectedItem(),
                        priceTextField.getText(),
                        desiredPriceTextField.getText(),
                        descriptionTextArea.getText(),
                        selectedImagePath
                );
                UsedBookstore usedBookstore = new UsedBookstore();
                usedBookstore.getBookInfo(bookInfo);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class BookInfo {
        private String title;
        private String subject;
        private String condition;
        private String price;
        private String desiredPrice;
        private String description;
        private String imagePath;

        public BookInfo(String title, String subject, String condition, String price, String desiredPrice, String description, String imagePath) {
            this.title = title;
            this.subject = subject;
            this.condition = condition;
            this.price = price;
            this.desiredPrice = desiredPrice;
            this.description = description;
            this.imagePath = imagePath;
        }
        
        public String getTitle() {
            return title;
        }

        public String getSubject() {
            return subject;
        }

        public String getCondition() {
            return condition;
        }

        public String getPrice() {
            return price;
        }

        public String getDesiredPrice() {
            return desiredPrice;
        }

        public String getDescription() {
            return description;
        }

        public String getImagePath() {
            return imagePath;
        }

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

    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            selectedImagePath = file.getAbsolutePath();

            try {
                BufferedImage image = ImageIO.read(file);
                ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(
                        imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH));
                imageLabel.setIcon(imageIcon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

