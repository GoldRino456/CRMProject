package com.RealBusinessCRM;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class CustomerInfoController {

    /*######################################
     * Variables
      ######################################*/

    private static boolean isExistingCustomer;

    /*######################################
     * FXML References
      ######################################*/
    //Text
    @FXML
    private Text txt_customerheadline;

    @FXML
    private Text txt_custid;

    @FXML
    private TextField tf_custid;

    /*######################################
     * FXML Actions & Events
      ######################################*/
    @FXML
    private void initialize() {

        if (isExistingCustomer)
        {
            txt_customerheadline.setText("Update Customer");
            populateCustomerData();
        }
        else
        {
            //CustomerID should be set by the Database automatically, so will only be visible upon an update
            txt_custid.setVisible(false);
            tf_custid.setVisible(false);
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
     * <p>Saves all information to database as a new record or updates existing record if isExistingCustomer is true.
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

    /** Updates the isExistingCustomer variable
     *
     * <p>Called by other classes just before transitioning to the Customer Info screen to ensure class is in
     * the correct mode: "new" or "update".</p>
     */
    public static void updatingExistingRecord(boolean bool) {
        isExistingCustomer = bool;
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

    /** Fills all form fields with existing Customer's data
     *
     * <p>Called at initialization when isExistingCustomer is TRUE. Pulls the selected customer's information
     * and fills all forms on the screen with that data.</p>
     */
    private void populateCustomerData() {
        //TODO: Implement populateCustomerData()
    }

}
