package com.RealBusinessCRM;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AppointmentInfoController {

    /*######################################
     * Variables
      ######################################*/

    private static boolean isExistingAppointment;


    @FXML
    private Text txt_appointmentheadline;

    @FXML
    private Text txt_appid;

    @FXML
    private TextField tf_appid;


    /*######################################
     * FXML Actions & Events
      ######################################*/
    @FXML
    private void initialize() {

        if (isExistingAppointment)
        {
            txt_appointmentheadline.setText("Update Appointment");
            populateAppointmentData();
        }
        else
        {
            txt_appid.setVisible(false);
            tf_appid.setVisible(false);
        }

    }

    /** Handles the Cancel Button Clicked Event
     *
     * <p>Does not save any information on the page. Loads Main Screen.</p>
     */
    @FXML
    private void onCancelButtonClick() {

        //Try to load the Main Window
        loadMainScreen();
    }

    /** Handles the Save Button Clicked Event
     *
     * <p>Saves all information to database as a new record or updates existing record if isExistingAppointment is true.
     * All fields must have valid data and cannot be left blank in order to save. Transitions to main screen once
     * database has been updated.</p>
     */
    @FXML
    private void onSaveButtonClick() {

        //TODO: Implement Function to Save Data

        loadMainScreen();
    }

    /*######################################
     * Helper Functions
      ######################################*/

    /** Changes the state of isExistingAppointment to ensure scene opens in either "new" or "update" mode
     *
     * <p>Should only be called BEFORE changing to the Appointment Info scene. Used by other class functions
     * to determine if data should be populated into the form and if text should read "new" or "update" appointment.
     * True = Editing existing appointment, False = Adding new appointment</p>
     */
    public static void updatingExistingRecord(boolean bool) {
        isExistingAppointment = bool;
    }

    /** Changes the current screen to the Main Screen
     *
     * <p>Should only be used after a valid save or cancel event. Calls changeScene() from class
     * ScheduleApplication to load the main-screen.fxml. If file is not found, method will exit the program.</p>
     */
    private void loadMainScreen() {
        try
        {
            ScheduleApplication app = new ScheduleApplication();
            app.changeScene("main-screen.fxml");
        }
        catch (IOException err)
        {
            System.out.println("FATAL ERROR: \"main-screen.fxml\" NOT FOUND!");
            System.out.println("Message: " + err.getMessage());
            System.out.println("Cause: " + err.getCause());
            System.exit(-1);
        }
    }

    private void populateAppointmentData() {
        //TODO: Implement populateAppointmentData()
    }
}
