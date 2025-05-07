import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Рисование домика");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Создаем панель для рисования
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        // Панель управления с полями ввода координат
        JPanel controlPanel = new JPanel();
        JTextField xField = new JTextField("100", 5);
        JTextField yField = new JTextField("100", 5);
        JButton drawButton = new JButton("Нарисовать");

        drawButton.addActionListener(e -> {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                drawingPanel.setHousePosition(x, y);
                drawingPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Введите корректные координаты!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        controlPanel.add(new JLabel("X:"));
        controlPanel.add(xField);
        controlPanel.add(new JLabel("Y:"));
        controlPanel.add(yField);
        controlPanel.add(drawButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    // Панель для рисования домика
    class DrawingPanel extends JPanel {
        private int houseX = 100;
        private int houseY = 100;

        public void setHousePosition(int x, int y) {
            this.houseX = x;
            this.houseY = y;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawHouse(g, houseX, houseY);
        }

        private void drawHouse(Graphics g, int x, int y) {
            // Основной цвет домика
            g.setColor(new Color(240, 200, 150));

            // Корпус домика
            g.fillRect(x, y, 200, 150);

            // Окна
            g.setColor(new Color(100, 180, 255));
            g.fillRect(x + 30, y + 30, 50, 50);  // Левое окно
            g.fillRect(x + 120, y + 30, 50, 50); // Правое окно

            // Дверь
            g.setColor(new Color(150, 100, 50));
            g.fillRect(x + 80, y + 80, 40, 70);

            // Крыша
            int[] roofX = {x - 20, x + 100, x + 220};
            int[] roofY = {y, y - 80, y};
            g.setColor(new Color(180, 80, 60));
            g.fillPolygon(roofX, roofY, 3);

            // Контуры
            g.setColor(Color.BLACK);
            g.drawRect(x, y, 200, 150); // Контур дома
            g.drawRect(x + 30, y + 30, 50, 50); // Контур левого окна
            g.drawRect(x + 120, y + 30, 50, 50); // Контур правого окна
            g.drawRect(x + 80, y + 80, 40, 70); // Контур двери
            g.drawPolygon(roofX, roofY, 3); // Контур крыши

            // Переплеты окон
            g.drawLine(x + 55, y + 30, x + 55, y + 80); // Вертикаль левого окна
            g.drawLine(x + 30, y + 55, x + 80, y + 55); // Горизонталь левого окна
            g.drawLine(x + 145, y + 30, x + 145, y + 80); // Вертикаль правого окна
            g.drawLine(x + 120, y + 55, x + 170, y + 55); // Горизонталь правого окна

            // Ручка двери
            g.fillOval(x + 110, y + 120, 5, 5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}