package com.RealBusinessCRM;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;


public class MainScreenController {

    /*######################################
     * Variables
      ######################################*/

    ObservableList<Customer> customerList;

    /*######################################
     * FXML References
      ######################################*/

    //Customer Table
    @FXML
    private TableView customerTable;

    @FXML
    private TableColumn col_c_custid;

    @FXML
    private TableColumn col_c_name;

    @FXML
    private TableColumn col_c_address;

    @FXML
    private TableColumn col_c_postalcode;

    @FXML
    private TableColumn col_c_phone;

    @FXML
    private TableColumn col_c_division;

    //Clock
    @FXML
    private Text txt_a_currenttime;

    @FXML
    private Text txt_c_currenttime;

    @FXML
    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            txt_c_currenttime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")) + " " + ZoneId.systemDefault().getDisplayName(TextStyle.NARROW, Locale.ENGLISH));
            txt_a_currenttime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")) + " " + ZoneId.systemDefault().getDisplayName(TextStyle.NARROW, Locale.ENGLISH));
        }
    };

    /*######################################
     * FXML Actions & Events
      ######################################*/

    @FXML
    private void initialize() {
        //Starts the clock to display on both Customer and Appointment Pages
        timer.start();
        populateCustomerTable();

    }

    private void populateCustomerTable() {

        getFullCustomerList();

        //Add data from Vector to Table
        col_c_custid.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CustomerID"));
        col_c_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("Name"));
        col_c_address.setCellValueFactory(new PropertyValueFactory<Customer, String>("Address"));
        col_c_postalcode.setCellValueFactory(new PropertyValueFactory<Customer, String>("PostalCode"));
        col_c_phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("PhoneNumber"));
        col_c_division.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("DivisionID"));
        customerTable.setItems(customerList);

    }

    private void getFullCustomerList() {
        //Tries to Query Customer Table
        try
        {
            customerList = CustomerQuery.selectAll();
            System.out.println("Customer Table Query (Select All) Successful!");

        }
        catch (SQLException err)
        {
            System.out.println("FATAL ERROR: Customer Table Query (Select All) Failed!");
            System.out.println("Message: " + err.getMessage());
            System.out.println("Cause: " + err.getCause());
            System.exit(-1);
        }
    }


    /** Handles the Create Button Clicked Event
     *
     * <p>Opens the customer info screen to allow user to add a new customer to the customer list.</p>
     */
    @FXML
    private void onCustomerCreateButtonClick() {
        //Ensures that Scene will be in "New Customer" mode
        CustomerInfoController.updatingExistingRecord(false);

        loadCustomerInfoScreen();
    }

    /** Handles the Update Button Clicked Event
     *
     * <p>Opens the customer info screen and populates data for the selected customer. Will call function
     *  to display error message if no customer is selected.</p>
     */
    @FXML
    private void onCustomerUpdateButtonClick() {
        //Ensures that Scene will be in "Update Customer" mode
        CustomerInfoController.updatingExistingRecord(true);

        loadCustomerInfoScreen();
    }

    /** Handles the Create Button Clicked Event in the Appointments Tab
     *
     * <p>Opens the appointment info screen to allow user to add a new customer to the customer list.</p>
     */
    @FXML
    private void onAppointmentCreateButtonClick() {
        //Ensures that Scene will be in "New Appointment" mode
        AppointmentInfoController.updatingExistingRecord(false);

        loadAppointmentInfoScreen();
    }

    /** Handles the Update Button Clicked Event in the Appointments Tab
     *
     * <p>Opens the appointment info screen and populates data for the selected appointment. Will call function
     *  to display error message if no appointment is selected.</p>
     */
    @FXML
    private void onAppointmentUpdateButtonClick() {
        //Ensures that Scene will be in "Update Appointment" mode
        AppointmentInfoController.updatingExistingRecord(true);

        loadAppointmentInfoScreen();
    }

    /*######################################
    * Helper Functions
      ######################################*/

    /** Changes the scene to display the Customer Info Screen
     *
     * <p>Calls changeScene() from class ScheduleApplication to load the customerInfo-screen.fxml. If
     * file is not found, method will exit the program.</p>
     */
    private void loadCustomerInfoScreen() {
        //Try to load the Customer Info Window
        try
        {
            ScheduleApplication app = new ScheduleApplication();
            app.changeScene("customerInfo-screen.fxml");
            timer.stop();
        }
        catch (IOException err)
        {
            System.out.println("FATAL ERROR: \"customerInfo-screen.fxml\" NOT FOUND!");
            System.out.println("Message: " + err.getMessage());
            System.out.println("Cause: " + err.getCause());
            System.exit(-1);
        }
    }

    /** Changes the scene to display the Appointment Info Screen
     *
     * <p>Calls changeScene() from class ScheduleApplication to load the appointmentInfo-screen.fxml. If
     * file is not found, method will exit the program.</p>
     */
    private void loadAppointmentInfoScreen() {
        //Try to load the Appointment Info Window
        try
        {
            ScheduleApplication app = new ScheduleApplication();
            app.changeScene("appointmentInfo-screen.fxml");
            timer.stop();
        }
        catch (IOException err)
        {
            System.out.println("FATAL ERROR: \"appointmentInfo-screen.fxml\" NOT FOUND!");
            System.out.println("Message: " + err.getMessage());
            System.out.println("Cause: " + err.getCause());
            System.exit(-1);
        }
    }

}
