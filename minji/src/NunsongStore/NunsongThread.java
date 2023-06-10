package NunsongStore;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.FontFormatException;

public class NunsongThread extends JPanel implements Runnable {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 400;
    private static final int SNOWFLAKE_WIDTH = 100;
    private static final int TEXT_WIDTH = 200;
    private static final int DELAY = 10;

    private int snowflakeX;
    private String text;
    private int textX;

    private boolean button1Clicked;
    private boolean button2Clicked;
    private boolean button3Clicked;
    private boolean button4Clicked;

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    private BufferedImage snowflakeImage;
    private Font koreanFont;

    public NunsongThread() {
        snowflakeX = 0;
        text = "로그인/회원가입";
        textX = PANEL_WIDTH / 2 - TEXT_WIDTH / 2;

        button1Clicked = false;
        button2Clicked = false;
        button3Clicked = false;
        button4Clicked = false;

        try {
            snowflakeImage = ImageIO.read(new File("snowflake.png"));
            koreanFont = Font.createFont(Font.TRUETYPE_FONT, new File("KoreanFont.ttf")).deriveFont(24f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        button1 = new JButton("버튼 1");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1Clicked = true;
                button2Clicked = false;
                button3Clicked = false;
                button4Clicked = false;
            }
        });

        button2 = new JButton("버튼 2");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1Clicked = false;
                button2Clicked = true;
                button3Clicked = false;
                button4Clicked = false;
            }
        });

        button3 = new JButton("버튼 3");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1Clicked = false;
                button2Clicked = false;
                button3Clicked = true;
                button4Clicked = false;
            }
        });

        button4 = new JButton("버튼 4");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button1Clicked = false;
                button2Clicked = false;
                button3Clicked = false;
                button4Clicked = true;
            }
        });

        add(button1);
        add(button2);
        add(button3);
        add(button4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        int snowflakeY = PANEL_HEIGHT / 2 - SNOWFLAKE_WIDTH / 2;
        g.drawImage(snowflakeImage, snowflakeX, snowflakeY, SNOWFLAKE_WIDTH, SNOWFLAKE_WIDTH, null);

        int textY = snowflakeY + SNOWFLAKE_WIDTH + 30;
        g.setFont(koreanFont);
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);
        g.drawString(text, textX, textY);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void run() {
        while (true) {
            if (button1Clicked) {
                snowflakeX = 0;
                text = "로그인/회원가입";
            } else if (button2Clicked) {
                snowflakeX = PANEL_WIDTH / 4 - SNOWFLAKE_WIDTH / 2;
                text = "회원가입";
            } else if (button3Clicked) {
                snowflakeX = PANEL_WIDTH / 2 - SNOWFLAKE_WIDTH / 2;
                text = "로그인";
            } else if (button4Clicked) {
                snowflakeX = PANEL_WIDTH - SNOWFLAKE_WIDTH;
                text = "중고책&공동구매 서점";
            }

            repaint();

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Nunsong Animation");
            NunsongThread animation = new NunsongThread();
            frame.getContentPane().add(animation);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            Thread thread = new Thread(animation);
            thread.start();
        });
    }
}