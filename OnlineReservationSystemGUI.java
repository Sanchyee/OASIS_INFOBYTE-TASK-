import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// User class to manage user credentials
class User {
    private String userId;
    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

// Reservation class to manage reservation details
class Reservation {
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String fromLocation;
    private String toLocation;
    private String pnr;

    public Reservation(String trainNumber, String trainName, String classType, String dateOfJourney,
                       String fromLocation, String toLocation, String pnr) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.pnr = pnr;
    }

    public String getPnr() {
        return pnr;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "trainNumber='" + trainNumber + '\'' +
                ", trainName='" + trainName + '\'' +
                ", classType='" + classType + '\'' +
                ", dateOfJourney='" + dateOfJourney + '\'' +
                ", fromLocation='" + fromLocation + '\'' +
                ", toLocation='" + toLocation + '\'' +
                ", pnr='" + pnr + '\'' +
                '}';
    }
}

// ReservationSystem class to manage the overall system
class ReservationSystem {
    private List<User> users;
    private List<Reservation> reservations;

    public ReservationSystem() {
        users = new ArrayList<>();
        reservations = new ArrayList<>();
        // Adding a sample user
        users.add(new User("sanchyee123", "password"));
    }

    public boolean login(String userId, String password) {
        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void makeReservation(String trainNumber, String trainName, String classType, String dateOfJourney,
                                String fromLocation, String toLocation) {
        String pnr = generatePnr(); // Generate a random PNR
        Reservation reservation = new Reservation(trainNumber, trainName, classType, dateOfJourney, fromLocation, toLocation, pnr);
        reservations.add(reservation);
        JOptionPane.showMessageDialog(null, "Reservation successful! Your PNR is: " + pnr);
    }

    public String cancelReservation(String pnr) {
        for (Reservation reservation : reservations) {
            if (reservation.getPnr().equals(pnr)) {
                reservations.remove(reservation);
                return "Reservation with PNR " + pnr + " has been cancelled.";
            }
        }
        return "No reservation found with that PNR.";
    }

    private String generatePnr() {
        return String.valueOf((int) (Math.random() * 100000)); // Simple PNR generation
    }
}

// Main GUI application
public class OnlineReservationSystemGUI extends JFrame {
    private ReservationSystem reservationSystem;

    public OnlineReservationSystemGUI() {
        reservationSystem = new ReservationSystem();
        setTitle("Online Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Login Panel
        JPanel loginPanel = new JPanel();
        JTextField userIdField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginPanel.add(new JLabel("User ID:"));
        loginPanel.add(userIdField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        add(loginPanel, BorderLayout.NORTH);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String password = new String(passwordField.getPassword());
                if (reservationSystem.login(userId, password)) {
                    showReservationPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials.");
                }
            }
        });
    }

    private void showReservationPanel() {
        // Clear the frame and set the reservation panel
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel reservationPanel = new JPanel();
        JTextField trainNumberField = new JTextField(10);
        JTextField trainNameField = new JTextField(10);
        JTextField classTypeField = new JTextField(10);
        JTextField dateOfJourneyField = new JTextField(10);
        JTextField fromLocationField = new JTextField(10);
        JTextField toLocationField = new JTextField(10);
        JButton makeReservationButton = new JButton("Make Reservation");
        JButton cancelReservationButton = new JButton("Cancel Reservation");

        reservationPanel.add(new JLabel("Train Number:"));
        reservationPanel.add(trainNumberField);
        reservationPanel.add(new JLabel("Train Name:"));
        reservationPanel.add(trainNameField);
        reservationPanel.add(new JLabel("Class Type:"));
        reservationPanel.add(classTypeField);
        reservationPanel.add(new JLabel("Date of Journey:"));
        reservationPanel.add(dateOfJourneyField);
        reservationPanel.add(new JLabel("From Location:"));
        reservationPanel.add(fromLocationField);
        reservationPanel.add(new JLabel("To Location:"));
        reservationPanel.add(toLocationField);
        reservationPanel.add(makeReservationButton);
        reservationPanel.add(cancelReservationButton);
        add(reservationPanel, BorderLayout.CENTER);

        // Action listener for making a reservation
        makeReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trainNumber = trainNumberField.getText();
                String trainName = trainNameField.getText();
                String classType = classTypeField.getText();
                String dateOfJourney = dateOfJourneyField.getText();
                String fromLocation = fromLocationField.getText();
                String toLocation = toLocationField.getText();

                reservationSystem.makeReservation(trainNumber, trainName, classType, dateOfJourney, fromLocation, toLocation);
            }
        });

        // Action listener for cancelling a reservation
        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pnr = JOptionPane.showInputDialog("Enter your PNR number:");
                String message = reservationSystem.cancelReservation(pnr);
                JOptionPane.showMessageDialog(null, message);
            }
        });

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OnlineReservationSystemGUI gui = new OnlineReservationSystemGUI();
            gui.setVisible(true);
        });
    }
}
