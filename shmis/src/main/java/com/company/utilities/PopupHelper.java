package com.company.utilities;

import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.company.App;
import com.company.classes.Appointment;
import com.company.classes.Patient;

/**
 * Helper class with methods for popup operations
 */
public class PopupHelper extends JPanel {
    private static String errorMessage;

    /**
     * Checks if the patient in the create patient popup is valid.
     *
     * @param inputs The inputted information
     * @param prevEmail The previous email of the patient
     * @return
     */
    public static boolean validPatient(JTextField[] inputs, String prevEmail) {
        // Trim whitespace
        for (int i = 0; i < 9; i++) {
            inputs[i].setText(inputs[i].getText().trim());
        }

        // Check for empty input
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

    /**
     * Checks if the appointment in the book appointment popup is valid.
     *
     * @param imaging The appointment's body areas
     * @param from The appointment's start time
     * @param to The appointment's end time
     * @return <code>True</code> if the appointment booked is valid
     */
    public static boolean validAppointment(JRadioButton[] imaging, JComboBox<String> from, JComboBox<String> to) {
        // Checking that no JRadioButtons are left empty
        boolean atLeastOne = false;

        // atLeastOne is only false if no body areas are selected
        for (JRadioButton i : imaging) {
            atLeastOne |= i.isSelected();
        }

        if (!atLeastOne) {
            errorMessage = "Please make sure to check at least 1 body area";
            return false;
        }

        // Comparing start long values
        if (stringToStart((String)to.getSelectedItem()) <= stringToStart((String)from.getSelectedItem())) {
            errorMessage = "Please make sure your end time is after your start time";
            return false;
        }

        return true;
    }

    /**
     * Check if a newly booked appointment conflicts with a current one.
     *
     * @param day The new appointment's day
     * @param from The new appointment's start time
     * @param to The new appointment's end time
     * @return
     */
    public static boolean conflictExists(JComboBox<String> day, JComboBox<String> from, JComboBox<String> to) {
        return true;
    }

    /**
     * Converts the time formatted in HH:MM to a long.
     *
     * @param time The time as a String
     * @return
     */
    public static long stringToStart(String time) {
        String[] arr = time.split(":");  // Separating hours and minutes
        long min = Long.valueOf(arr[0])*60 + Long.valueOf(arr[1]);
        long numMin = (min-540) / 30;
        return numMin;
    }

    /**
     * Checks if an email exists or not. Ignores the case when an employee does not change a patient's email in the edit patient information popup.
     *
     * @param prevEmail The previous email
     * @param email The new email
     * @return <code>True</code> if there is an email conflict
     */
    public static boolean emailExists(String prevEmail, String email) {
        // If email is not changed when editing patient information
        if (prevEmail.equals(email)) {
            return false;
        }

        for (Patient p : App.dsm.getPatientList()) {
            if (p.getEmail().equals(email))
                return true;
        }

        return false;
    }

    /**
     * Checks if an appointment conflicts with a booked appointment.
     *
     * @param booked New appointment to be checked
     * @return <code>True</code> if there is a conflict
     */
    public static boolean existsTimeConflict(Appointment booked) {
        for (long appId: App.dsm.getInCalendar()) {
            Appointment appointment = App.dsm.query(appId);
            long x1 = booked.getStart(), x2 = x1 + booked.getSpan();
            long y1 = appointment.getStart(), y2 = y1 + appointment.getSpan();

            // Start time of the first appointment is before the end time of the second appointment and
            // Start time of the second appointment is before the end time of the first appointment
            if (x1 < y2 && y1 < x2 && booked.getDate().equals(appointment.getDate())) {
                errorMessage = "Your appointment conflicts with another appointment";
                return true;
            }
        }

        return false;
    }

    /**
     * Returns an error message based on invalid input.
     *
     * @return an error message based on invalid input
     */
    public static String getError() {
        return errorMessage;
    }
}
