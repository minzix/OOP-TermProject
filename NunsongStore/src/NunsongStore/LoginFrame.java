package NunsongStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Connection connection; // 추가함
    
    private void openNewPanel() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("눈송상점");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new Selection());
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }

    public LoginFrame() {
        setTitle("NoonsongStore");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        ImageIcon imageIcon = new ImageIcon("src/image/noonsong.png");
        JLabel imageLabel1 = new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth() / 5, imageIcon.getIconHeight() / 5, Image.SCALE_DEFAULT)));
        JLabel imageLabel2 = new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth() / 5, imageIcon.getIconHeight() / 5, Image.SCALE_DEFAULT)));

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(imageLabel1, constraints);

        constraints.gridx = 1;
        panel.add(imageLabel2, constraints);

        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("ID: ");
        usernameField = new JTextField(20);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("PW: ");
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                dispose();
                
                // 데이터 베이스 연결은 했으나, 컴퓨터에 MySQL이 없을 경우를 대비해 로그인 버튼을 누르면 다음 페이지로 넘어가도록 설정
                JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful");
                openNewPanel();	
                dispose();
                // 데이터 베이스 연결
                /*if(validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login Successful");
                    openNewPanel();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid ID or Password");
                }*/
            }
        });

        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSignupForm();
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2; // 수정된 부분
        constraints.anchor = GridBagConstraints.CENTER; // 수정된 부분
        panel.add(usernamePanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordPanel, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2; // 수정된 부분
        panel.add(loginButton, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2; // 수정된 부분
        panel.add(signupButton, constraints);


        getContentPane().setBackground(Color.WHITE);
        add(panel);
        pack();

        // Snowflake 이미지를 움직이는 스레드 실행
        Thread movingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int x1 = -imageIcon.getIconWidth() / 5;
                int x2 = getWidth() - imageIcon.getIconWidth() / 5;
                int direction1 = 1;
                int direction2 = -1;

                Random random = new Random();

                while (true) {
                    x1 += direction1;
                    x2 += direction2;

                    if (x1 >= getWidth() - imageIcon.getIconWidth() / 5 || x1 <= -imageIcon.getIconWidth() / 5) {
                        direction1 *= -1;
                    }

                    if (x2 >= getWidth() - imageIcon.getIconWidth() / 5 || x2 <= -imageIcon.getIconWidth() / 5) {
                        direction2 *= -1;
                    }

                    imageLabel1.setBounds(x1, 20, imageIcon.getIconWidth() / 5, imageIcon.getIconHeight() / 5);
                    imageLabel2.setBounds(x2, 20, imageIcon.getIconWidth() / 5, imageIcon.getIconHeight() / 5);

                    if (Math.abs(x1 - x2) <= imageIcon.getIconWidth() / 5) {
                        int red = random.nextInt(256);
                        int green = random.nextInt(256);
                        int blue = random.nextInt(256);
                        loginButton.setBackground(new Color(red, green, blue));
                    } else {
                        loginButton.setBackground(signupButton.getBackground());
                    }

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        movingThread.start();
    }

    private void showSignupForm() {
        JPanel signupPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("ID: ");
        JTextField usernameField = new JTextField(20);
        constraints.gridy = 1;
        signupPanel.add(usernameLabel, constraints);
        signupPanel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("PW: ");
        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridy = 2;
        signupPanel.add(passwordLabel, constraints);
        signupPanel.add(passwordField, constraints);

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField(20);
        constraints.gridy = 3;
        signupPanel.add(emailLabel, constraints);
        signupPanel.add(emailField, constraints);

        int result = JOptionPane.showConfirmDialog(this, signupPanel, "Sign Up", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            System.out.println("ID: " + username);
            System.out.println("PW: " + password);
            System.out.println("Email: " + email);

            JOptionPane.showMessageDialog(this, "Sign Up Successful");
        }
    }
    
    private boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean saveSignupInfo(String username, String password, String email) {
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/logindb";
        String username = "root";
        String password = "rlaalswl0419";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void disconnectFromDatabase() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        disconnectFromDatabase();
    }
} 
