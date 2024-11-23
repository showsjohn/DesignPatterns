import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class AddEventModal extends JDialog
{

    JPanel beginPanel, endPanel, textPanel;
    JSpinner beginHourSpinner;
    JSpinner beginMinuteSpinner;
    JSpinner endHourSpinner;
    JSpinner endMinuteSpinner;
    JComboBox<String> eventType;
    JButton submit;
    LocalDate date;
    Event event;

    public AddEventModal(LocalDate date)
    {
        this.date = date;
        setTitle("Add Event");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(300,275));
        setLocation(ScreenSize.getCenter().x-150, ScreenSize.getCenter().y - 180 );

        beginPanel = new JPanel();
        endPanel = new JPanel();
        textPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name: ");
        JTextField name = new JTextField();
        JLabel locationLabel = new JLabel("Location: ");
        JTextField location = new JTextField();

        textPanel.setLayout(new GridLayout(2, 2));
        textPanel.add(nameLabel);
        textPanel.add(name);
        textPanel.add(locationLabel);
        textPanel.add(location);

        beginHourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1 ));
        beginMinuteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 59, 1 ));
        endHourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1 ));
        endMinuteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 59, 1 ));

        beginPanel.add(new JLabel("Beginning time: "));
        beginPanel.add(beginHourSpinner);
        beginPanel.add(new JLabel(" : "));
        beginPanel.add(beginMinuteSpinner);
        beginPanel.add(new JLabel("  "));

        endPanel.add(new JLabel("Ending time: "));
        endPanel.add(endHourSpinner);
        endPanel.add(new JLabel(" : "));
        endPanel.add(endMinuteSpinner);
        endPanel.add(new JLabel("  "));

        eventType = new JComboBox<String>(new String[] {"Meeting", "Deadline"});

        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime dt;
                LocalTime time = LocalTime.of((int) beginHourSpinner.getValue(), (int) beginMinuteSpinner.getValue());
                dt = LocalDateTime.of(date, time);
                if (eventType.getSelectedItem() == "Meeting")
                {
                    event = EventFactory.createEvent(name.getText(), location.getText(), dt);
                }
                else{
                    event =  EventFactory.createEvent(name.getText(), dt);
                }

                dispose();
            }
        });

        add(textPanel);
        add(beginPanel);
        add(endPanel);
        add(eventType);
        add(submit);
        revalidate();
        repaint();
    }

    public Event getData()
    {
        if (event!= null)
            return event;
        else {
            throw new RuntimeException("event is null!");
        }
    }
}
