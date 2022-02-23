package com.company.pages.program;

import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Appointment;
import com.company.classes.Patient;

public class PopupHelper extends JPanel {
    private static String errorMessage;

    public static boolean validPatient(JTextField[] inputs, String prevEmail) {
        // Trim whitespace
        for (int i = 0; i < 9; i++) {
            inputs[i].setText(inputs[i].getText().trim());
        }

        // No input check
        if (inputs[1].getText().equals("") ||
            inputs[2].getText().equals("") ||
            inputs[4].getText().equals("") ||
            inputs[5].getText().equals("") ||
            inputs[6].getText().equals("")) {
            errorMessage = "Please fill in all fields";
            return false;
        }

        // Age check
        try {
            long age = Long.valueOf(inputs[0].getText());

            if (age < 0 || age > 150)
                throw new Exception();

        } catch (Exception e) {
            errorMessage = "Please input an age between 0 and 150";
            return false;
        }

        // Gender check
        String gender = inputs[3].getText().toLowerCase();

        if (!gender.equals("m") && !gender.equals("f") && !gender.equals("o")) {
            errorMessage = "Please input a proper gender: male (M), female (F), or other (O)";
            return false;
        }


        // Email regex check
        // TODO improve regex range
        if (!Pattern.compile("[A-Za-z0-9.]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+").matcher(inputs[7].getText()).matches()) {
            errorMessage = "Please input a proper email";
            return false;
        }

        if (emailExists(prevEmail, inputs[7].getText().toLowerCase())) {
            errorMessage = "Email already exists";
            return false;
        }

        // Telephone regex check
        if (!Pattern.compile("[0-9]{10}").matcher(inputs[8].getText()).matches()) {
            errorMessage = "Please input a telephone number in the form ##########";
            return false;
        }

        return true;
    }

    public static boolean validAppointment(JRadioButton[] imaging, JComboBox<String> from, JComboBox<String> to) {
        // Checking that no JRadioButtons are left empty
        boolean atLeastOne = false;

        for (JRadioButton i : imaging) {
            atLeastOne |= i.isSelected();
        }

        if (!atLeastOne) {
            errorMessage = "Please make sure to check at least 1 body area";
            return false;
        }

        if (stringToStart((String)to.getSelectedItem()) <= stringToStart((String)from.getSelectedItem())) {
            errorMessage = "Please make sure your end time is after your start time";
            return false;
        }

        return true;
    }

    public static boolean conflictExists(JComboBox<String> day, JComboBox<String> from, JComboBox<String> to) {
        return true;
    }

    public static long stringToStart(String time) {
        String[] arr = time.split(":");
        long min = Long.valueOf(arr[0])*60 + Long.valueOf(arr[1]);
        long numMin = (min-540) / 30;
        return numMin;
    }

    public static boolean emailExists(String prevEmail, String email) {
        if (prevEmail.equals(email)) {
            return false;
        }

        for (Patient p : App.dsm.getPatientList()) {
            if (p.getEmail().equals(email))
                return true;
        }

        return false;
    }

    public static boolean existsTimeConflict(Appointment booked) {
        for (long appId: App.dsm.getInCalendar()) {
            Appointment appointment = App.dsm.query(appId);
            long x1 = booked.getStart(), x2 = x1 + booked.getSpan();
            long y1 = appointment.getStart(), y2 = y1 + appointment.getSpan();

            if (x1 < y2 && y1 < x2 && booked.getDate().equals(appointment.getDate())) {
                errorMessage = "Your appointment conflicts with another appointment";
                return true;
            }
        }

        return false;
    }

    public static String getError() {
        return errorMessage;
    }
}
