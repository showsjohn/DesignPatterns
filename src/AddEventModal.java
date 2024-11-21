import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class AddEventModal extends JDialog
{

    public AddEventModal()
    {
        setTitle("Add Event");
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
        setSize(new Dimension(500,500));
        setLocation(ScreenSize.getCenter().x - 250, ScreenSize.getCenter().y - 250 );

        JPanel beginPanel = new JPanel();
        JPanel endPanel = new JPanel();
        JPanel textPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name: ");
        JTextField name = new JTextField();
        JLabel locationLabel = new JLabel("Location: ");
        JTextField location = new JTextField();

        textPanel.setLayout(new GridLayout(2, 2));
        textPanel.add(nameLabel);
        textPanel.add(name);
        textPanel.add(locationLabel);
        textPanel.add(location);

        JSpinner beginHourSpinner = new JSpinner(new SpinnerNumberModel(12, 1, 12, 1 ));
        JSpinner beginMinuteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 59, 1 ));
        JSpinner beginPeriodSpinner = new JSpinner(new SpinnerListModel(new String[]{"AM", "PM"}));

        JSpinner endHourSpinner = new JSpinner(new SpinnerNumberModel(12, 1, 12, 1 ));
        JSpinner endMinuteSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 59, 1 ));
        JSpinner endPeriodSpinner = new JSpinner(new SpinnerListModel(new String[]{"AM", "PM"}));

        beginPanel.add(new JLabel("Beginning time: "));
        beginPanel.add(beginHourSpinner);
        beginPanel.add(new JLabel(" : "));
        beginPanel.add(beginMinuteSpinner);
        beginPanel.add(new JLabel("  "));
        beginPanel.add(beginPeriodSpinner);

        endPanel.add(new JLabel("Ending time: "));
        endPanel.add(endHourSpinner);
        endPanel.add(new JLabel(" : "));
        endPanel.add(endMinuteSpinner);
        endPanel.add(new JLabel("  "));
        endPanel.add(endPeriodSpinner);


        add(textPanel);
        add(beginPanel);
        add(endPanel);

        add(new JComboBox<String>(new String[] {"Meeting", "Deadline"}));

        JButton submit = new JButton("Submit");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        revalidate();
        repaint();


    }

}
