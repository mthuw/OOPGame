package main.homepage;
import javax.swing.*;
import java.awt.*;
public class HeaderSection extends UIComponent{
    private final String title;
    private static final int DIVIDER_WIDTH = 360;
    private static final int DIVIDER_HEIGHT = 1;
    private static final Color DIVIDER_COLOR = new Color(180, 180, 175);
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);

    public HeaderSection(String title){
        this.title = title;
    }

    @Override
    public JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);

        JPanel divider = createDivider();
        divider.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(divider);
        return panel;

    }

    private JPanel createDivider(){
        JPanel divider = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(HeaderSection.DIVIDER_COLOR);
                g.fillRect(0,0, 1000, HeaderSection.DIVIDER_HEIGHT);
            }
        };
        divider.setPreferredSize(new Dimension(HeaderSection.DIVIDER_WIDTH, HeaderSection.DIVIDER_HEIGHT));
        return divider;
    }
}
