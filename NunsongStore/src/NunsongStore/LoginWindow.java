package NunsongStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class LoginWindow extends JFrame {
    private JPanel loginPanel;
    private JPanel signupPanel;
    private Connection connection;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }

    public LoginWindow() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectToDatabase();
        initializeLoginPanel();
        add(loginPanel);
        pack();
    }

    private void openNewPanel() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("눈송상점");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(new NunsongStore());
            frame.pack();
            frame.setVisible(true);
        });
    }

    private void initializeLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JTextField usernameField = new JTextField(20);
        usernameField.setToolTipText("아이디");
        loginPanel.add(usernameField, constraints);

        constraints.gridy = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setToolTipText("비밀번호");
        loginPanel.add(passwordField, constraints);

        constraints.gridy = 2;
        JLabel messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);
        loginPanel.add(messageLabel, constraints);

        constraints.gridy = 3;
        JButton loginButton = new JButton("로그인");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (validateLogin(username, password)) {
                    messageLabel.setText("로그인 성공");
                    openNewPanel();
                    dispose();
                } else {
                    messageLabel.setText("아이디 또는 비밀번호가 올바르지 않습니다.");
                }
            }
        });
        loginPanel.add(loginButton, constraints);

        constraints.gridy = 4;
        JButton signupButton = new JButton("회원가입");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSignupPanel();
            }
        });
        loginPanel.add(signupButton, constraints);
    }

    private void createSignupPanel() {
        signupPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("아이디");
        signupPanel.add(usernameLabel, constraints);

        JTextField signupUsernameField = new JTextField(20);
        signupPanel.add(signupUsernameField, constraints);

        constraints.gridy = 1;
        JLabel passwordLabel = new JLabel("비밀번호");
        signupPanel.add(passwordLabel, constraints);

        JPasswordField signupPasswordField = new JPasswordField(20);
        signupPanel.add(signupPasswordField, constraints);

        constraints.gridy = 2;
        JLabel emailLabel = new JLabel("이메일");
        signupPanel.add(emailLabel, constraints);

        JTextField signupEmailField = new JTextField(20);
        signupPanel.add(signupEmailField, constraints);

        constraints.gridy = 3;
        JButton signupConfirmButton = new JButton("가입하기");
        signupConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = signupUsernameField.getText();
                String password = new String(signupPasswordField.getPassword());
                String email = signupEmailField.getText();

                boolean signupSuccessful = saveSignupInfo(username, password, email);

                if (signupSuccessful) {
                    int option = JOptionPane.showConfirmDialog(null, "회원가입 성공\n로그인 패널로 돌아가시겠습니까?", "회원가입 완료", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        getContentPane().removeAll();
                        add(loginPanel);
                        revalidate();
                        repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "회원가입 실패");
                }
            }
        });
        signupPanel.add(signupConfirmButton, constraints);

        getContentPane().removeAll();
        add(signupPanel);
        revalidate();
        repaint();
    }

    private boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // 사용자가 데이터베이스에 존재하면 로그인 성공
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 사용자가 데이터베이스에 존재하지 않거나 비밀번호가 일치하지 않으면 로그인 실패
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
            connection = DriverManager.getConnection(url, username, password);
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

