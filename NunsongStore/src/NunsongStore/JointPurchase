package NunsongStore;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class JointPurchase extends JFrame {
    private JLabel subjectLabel;//전공 선택 화면에서 사용되는 레이블
    private JComboBox<String> subjectComboBox;//전공 선택 화면에서 사용되는 콤보박스

    private static String[] majorOptions = {"전공 선택", "전공 필수"};//전공선택화면의 콤보박스에 표시되는 옵션 배열
    private static String[] subjects = {//전공 선택 화면과 전공 필수 화면에서 과목을 나타내는 배열
            "IT기술의 이해",
            "IT수학",
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

    private static String[] necessary = {//전공 필수 화면에서 필수 과목을 나타내는 배열
            "IT기술의 이해",
            "프로그래밍입문 (Python)(필수)",
            "IT수학",
            "기초프로그래밍 (C)(필수)",
            "객체지향프로그래밍 (Java)(필수)",
            "웹프로그래밍기초(HTML5+JavaScript)(필수)",
            "데이터구조(필수)",
    };
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
   

    public static void createAndShowGUI() {//전공 선택 화면을 구성하는 GUI요소를 생성하고 화면을 표시
        JFrame frame = new JFrame("전공 선택");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        // "눈송 공구 서점" 레이블 추가
        JLabel titleLabel = new JLabel("눈송 공구 서점");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout());

        JComboBox<String> comboBox = new JComboBox<>(majorOptions);
        comboBoxPanel.add(comboBox);

        JButton nextButton = new JButton("다음 화면으로");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("전공 선택")) {
                    showMajorSelectionScreen();
                } else if (selectedOption.equals("전공 필수")) {
                    showMajorRequiredScreen();
                }
            }
        });

        comboBoxPanel.add(nextButton);

        frame.add(comboBoxPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }



    private static void showMajorSelectionScreen() {
       /*전공 선택 화면에서 과목을 선택하고 공동구매 참여 버튼을 클릭할 때 호출됨*/
       /*선택한 과목에 대한 공동구매 참여 여부를 확인하고, 참여 가능 여부에 따라 
        * 메시지 다이얼로그를 표시*/
        JFrame selectionFrame = new JFrame("전공 선택 화면");
        selectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectionFrame.setSize(300, 200);
        selectionFrame.setLayout(new BorderLayout());

        JComboBox<String> subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.setPreferredSize(new Dimension(200, 30));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton selectButton = new JButton("공동구매 참여");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSubject = (String) subjectComboBox.getSelectedItem();
                int option = JOptionPane.showConfirmDialog(null, "공동구매에 참여하시겠습니까?", "공동구매 참여", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // 공동구매에 참여하는 로직 추가
                    int participants = 5; // 예시로 5명 이상인 경우
                    if (participants >= 5) {
                        JOptionPane.showMessageDialog(null, "공동구매가 가능합니다.", "공동구매 가능", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "공동구매 참여가 완료되었습니다. (" + participants + "/5)", "공동구매 참여 완료", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // 사용자가 아니오를 선택한 경우 처리할 내용을 추가합니다.
                }
                selectionFrame.dispose(); // 전공 선택 화면을 닫습니다.
            }
        });
        buttonPanel.add(selectButton);

        selectionFrame.add(subjectComboBox, BorderLayout.CENTER);
        selectionFrame.add(buttonPanel, BorderLayout.SOUTH);

        selectionFrame.setVisible(true);
    }

    private static void showMajorRequiredScreen() {
       /*전공 필수 화면에서 과목을 선택하고 공동구매 참여 버튼을 클릭할 때 호출됨*/
       /*선택한 과목에 대한 공동구매 참여 여부를 확인하고, 참여 가능 여부에 메시지
        * 다이얼로그를 표시*/
        JFrame selectionFrame = new JFrame("전공 필수 화면");
        selectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectionFrame.setSize(300, 200);
        selectionFrame.setLayout(new BorderLayout());

        JComboBox<String> subjectComboBox = new JComboBox<>(subjects);
        subjectComboBox.setPreferredSize(new Dimension(200, 30));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton selectButton = new JButton("공동구매 참여");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSubject = (String) subjectComboBox.getSelectedItem();
                int option = JOptionPane.showConfirmDialog(null, "공동구매에 참여하시겠습니까?", "공동구매 참여", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // 공동구매에 참여하는 로직 추가
                    int participants = 3; // 예시로 3명인 경우
                    if (participants >= 5) {
                        JOptionPane.showMessageDialog(null, "공동구매가 가능합니다.", "공동구매 가능", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "공동구매 참여가 완료되었습니다. (" + participants + "/5)", "공동구매 참여 완료", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // 사용자가 아니오를 선택한 경우 처리할 내용을 추가합니다.
                }
                selectionFrame.dispose(); // 전공 선택 화면을 닫습니다.
            }
        });
        buttonPanel.add(selectButton);

        selectionFrame.add(subjectComboBox, BorderLayout.CENTER);
        selectionFrame.add(buttonPanel, BorderLayout.SOUTH);

        selectionFrame.setVisible(true);
    }
}
