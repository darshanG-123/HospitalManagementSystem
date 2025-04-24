import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class HospitalManagementSystemUI {
    private JFrame frame;
    private DefaultListModel<String> patientListModel = new DefaultListModel<>();
    private DefaultListModel<String> doctorListModel = new DefaultListModel<>();
    private DefaultListModel<String> appointmentListModel = new DefaultListModel<>();
    private Map<Integer, String> patients = new HashMap<>();
    private Map<Integer, String> doctors = new HashMap<>();
    private int patientId = 1, doctorId = 1;

    public HospitalManagementSystemUI() {
        frame = new JFrame("Hospital Management System");
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 3));
        JTextArea outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JButton addPatientBtn = new JButton("Add Patient");
        JButton viewPatientsBtn = new JButton("View Patients");
        JButton updatePatientBtn = new JButton("Update Patient");
        JButton dischargePatientBtn = new JButton("Discharge Patient");

        JButton addDoctorBtn = new JButton("Add Doctor");
        JButton viewDoctorsBtn = new JButton("View Doctors");
        JButton assignDoctorBtn = new JButton("Assign Doctor");

        JButton bookApptBtn = new JButton("Book Appointment");
        JButton cancelApptBtn = new JButton("Cancel Appointment");
        JButton viewApptBtn = new JButton("View Appointments");

        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(addPatientBtn);
        buttonPanel.add(viewPatientsBtn);
        buttonPanel.add(updatePatientBtn);
        buttonPanel.add(dischargePatientBtn);

        buttonPanel.add(addDoctorBtn);
        buttonPanel.add(viewDoctorsBtn);
        buttonPanel.add(assignDoctorBtn);

        buttonPanel.add(bookApptBtn);
        buttonPanel.add(cancelApptBtn);
        buttonPanel.add(viewApptBtn);

        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        addPatientBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter patient name:");
            if (name != null && !name.isEmpty()) {
                patients.put(patientId, name);
                outputArea.append("Added Patient ID " + patientId + ": " + name + "\n");
                patientId++;
            }
        });

        viewPatientsBtn.addActionListener(e -> {
            outputArea.append("--- Patients ---\n");
            patients.forEach((id, name) -> outputArea.append("ID: " + id + ", Name: " + name + "\n"));
        });

        updatePatientBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID to update:"));
                if (patients.containsKey(id)) {
                    String newName = JOptionPane.showInputDialog("Enter new name:");
                    patients.put(id, newName);
                    outputArea.append("Updated Patient ID " + id + " to " + newName + "\n");
                } else {
                    outputArea.append("Patient not found.\n");
                }
            } catch (Exception ex) {
                outputArea.append("Invalid input.\n");
            }
        });

        dischargePatientBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID to discharge:"));
                if (patients.remove(id) != null) {
                    outputArea.append("Patient ID " + id + " discharged.\n");
                } else {
                    outputArea.append("Patient not found.\n");
                }
            } catch (Exception ex) {
                outputArea.append("Invalid input.\n");
            }
        });

        addDoctorBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Enter doctor name:");
            if (name != null && !name.isEmpty()) {
                doctors.put(doctorId, name);
                outputArea.append("Added Doctor ID " + doctorId + ": " + name + "\n");
                doctorId++;
            }
        });

        viewDoctorsBtn.addActionListener(e -> {
            outputArea.append("--- Doctors ---\n");
            doctors.forEach((id, name) -> outputArea.append("ID: " + id + ", Name: " + name + "\n"));
        });

        assignDoctorBtn.addActionListener(e -> {
            try {
                int pid = Integer.parseInt(JOptionPane.showInputDialog("Enter Patient ID:"));
                int did = Integer.parseInt(JOptionPane.showInputDialog("Enter Doctor ID:"));
                if (patients.containsKey(pid) && doctors.containsKey(did)) {
                    outputArea.append("Doctor " + doctors.get(did) + " assigned to Patient " + patients.get(pid) + "\n");
                } else {
                    outputArea.append("Invalid patient or doctor ID.\n");
                }
            } catch (Exception ex) {
                outputArea.append("Invalid input.\n");
            }
        });

        bookApptBtn.addActionListener(e -> {
            String details = JOptionPane.showInputDialog("Enter appointment details (Patient - Doctor):");
            if (details != null && !details.isEmpty()) {
                appointmentListModel.addElement(details);
                outputArea.append("Appointment booked: " + details + "\n");
            }
        });

        cancelApptBtn.addActionListener(e -> {
            try {
                int index = Integer.parseInt(JOptionPane.showInputDialog("Enter appointment index to cancel:"));
                if (index >= 0 && index < appointmentListModel.size()) {
                    outputArea.append("Cancelled: " + appointmentListModel.get(index) + "\n");
                    appointmentListModel.remove(index);
                } else {
                    outputArea.append("Invalid index.\n");
                }
            } catch (Exception ex) {
                outputArea.append("Invalid input.\n");
            }
        });

        viewApptBtn.addActionListener(e -> {
            outputArea.append("--- Appointments ---\n");
            for (int i = 0; i < appointmentListModel.size(); i++) {
                outputArea.append(i + ": " + appointmentListModel.get(i) + "\n");
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalManagementSystemUI::new);
    }
}
