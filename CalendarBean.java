import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CalendarBean extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDate currentDate;
    private List<String> events;
    private JTextField eventTextField;
    private JList<String> eventList;

    public CalendarBean() {
        this.currentDate = LocalDate.now();
        this.events = new ArrayList<>();

        // Create GUI components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel addPanel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new BorderLayout());
        eventTextField = new JTextField();
        JButton addButton = new JButton("Add");
        eventList = new JList<>(events.toArray(new String[0]));
        JScrollPane listScrollPane = new JScrollPane(eventList);
        JLabel dateLabel = new JLabel(currentDate.toString());
        JButton prevButton = new JButton("<");
        JButton nextButton = new JButton(">");

        // Add components to panels
        addPanel.add(eventTextField, BorderLayout.CENTER);
        addPanel.add(addButton, BorderLayout.EAST);
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.add(dateLabel, BorderLayout.NORTH);
        listPanel.add(prevButton, BorderLayout.WEST);
        listPanel.add(nextButton, BorderLayout.EAST);
        mainPanel.add(addPanel, BorderLayout.NORTH);
        mainPanel.add(listPanel, BorderLayout.CENTER);

        // Add listeners to components
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEvent(eventTextField.getText());
                eventTextField.setText("");
            }
        });
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.minusDays(1);
                dateLabel.setText(currentDate.toString());
                updateEvents();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.plusDays(1);
                dateLabel.setText(currentDate.toString());
                updateEvents();
            }
        });

        // Configure main frame
        setTitle("Calendar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setContentPane(mainPanel);
        setVisible(true);
    }

    private void updateEvents() {
        eventList.setListData(events.toArray(new String[0]));
    }

    public void addEvent(String event) {
        events.add(event);
        updateEvents();
    }

    public void removeEvent(String event) {
        events.remove(event);
        updateEvents();
    }

    public boolean hasEvent(String event) {
        return events.contains(event);
    }

    public void printEvents() {
        for (String event : events) {
            System.out.println(event);
        }
    }

    public static void main(String[] args) {
        new CalendarBean();
    }
}
