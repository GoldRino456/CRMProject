package com.RealBusinessCRM;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.*;
import java.util.Locale;

public class LoginController {

    /*######################################
    * Variables
      ######################################*/
    static ZoneId userTimeZone = ZoneId.systemDefault();
    static Locale userLocale = Locale.getDefault();
    //static Locale userLocale = Locale.FRANCE;

    /*######################################
    * FXML References
      ######################################*/
    //Buttons
    @FXML
    private Button btn_login;

    //Text & Text Fields
    @FXML
    private TextField tf_userid;
    @FXML
    private TextField tf_password;
    @FXML
    private Text txt_err;
    @FXML
    private Text txt_headline;
    @FXML
    private Text txt_title;

    //Labels
    @FXML
    private Label lbl_zoneid;



    /*######################################
    * FXML Actions & Events
      ######################################*/
    /** Initializes the Login Form
     *
     * <p>Run when login screen page first loads. Calls all functions needed to initialize the
     * login screen including localizeText().</p>
     */
    @FXML
    private void initialize() {

        localizeText();

    }

    /** Handles the Login Button Clicked Event
     *
     * <p>If credentials entered are valid and found in list of users, attempts to
     * load the main-screen.fxml page.</p>
     */
    @FXML
    protected void onLoginButtonClick() {

        //Check No Empty Fields
        if(checkLoginFieldsForValue())
        {
            txt_err.setText("It worked! Login successful!");

            //Try to load the Main Window
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
        else
        {
            //TODO: Implement Check for existing Username/PW combo here
            //txt_err.setText("Invalid Username or Password.");

        }


    }

    /*######################################
    * Helper Functions
      ######################################*/
    /** Detects and Displays User's Timezone and Text in Locale Language
     *
     * <p>Called when the Login Form Initializes. Calls displayTimeZone() and the corresponding function
     * to translate the form's text into the language of user's Locale.</p>
     *
     */
    private void localizeText() {

        displayTimeZone();

        //Form by default will display in English
        if(userLocale.getLanguage() != "en")
        {
            switch (userLocale.getLanguage())
            {
                case "fr":
                    displayLogin_FR();
                default:
                    //Language Not Implemented
            }

        }


    }

    /** Displays Login Form in French Language
     *
     * <p>All text on the Login Form will be displayed in the French Language. Should only be called by the localizeText()
     * function after it detects a user with System settings set to FR.</p>
     *
     */
    private void displayLogin_FR() {
        txt_title.setText("DVSPD");
        txt_headline.setText("De vraies solutions de planification d'entreprise");
        btn_login.setText("Connexion");
        tf_userid.setPromptText("Identifiant d'utilisateur");
        tf_password.setPromptText("Mot de passe");
    }

    /** Checks UserID and Password Fields for content.
     *
     * <p>Returns true if a valid String does exist in both UserID and Password fields and that
     *string is not blank. Otherwise returns false.</p>
     *
     * @return boolean - UserID and Password Text Fields contain a non-blank value
     */
    private boolean checkLoginFieldsForValue() {

        try {
            if(tf_userid.getText().isEmpty() || tf_userid.getText().isBlank())
            {
                displayBlankUsernameError();
                return false;
            }
            else if (tf_password.getText().isEmpty() || tf_password.getText().isBlank())
            {
                displayBlankPasswordError();
                return false;
            }

        }
        catch (Error err) {

            txt_err.setText("ERROR: Invalid Input");
            System.out.println("ERROR: " + err.getMessage());
            return false;
        }

        return true;
    }

    /** Displays Blank Username Error in User's Local Language
     *
     * <p>Displays an error message when the username entered is either blank or empty. Error message displayed
     * is based on the Locale of the user's machine.</p>
     *
     */
    private void displayBlankUsernameError() {
        switch (userLocale.getLanguage()) {

            case "en":
                txt_err.setText("ERROR: Username field cannot be empty!");
                break;

            case "fr":
                txt_err.setText("ERREUR: le champ du nom d'utilisateur ne peut pas être vide!");
                break;

            default:
                //Language Not Implemented.

        }

    }

    /** Displays Blank Password Error in User's Local Language
     *
     * <p>Displays an error message when the password entered is either blank or empty. Error message displayed
     * is based on the Locale of the user's machine.</p>
     *
     */
    private void displayBlankPasswordError() {
        switch (userLocale.getLanguage()) {

            case "en":
                txt_err.setText("ERROR: Password field cannot be empty!");
                break;

            case "fr":
                txt_err.setText("ERREUR: le champ du mot de passe ne peut pas être vide!");
                break;

            default:
                //Language Not Implemented.

        }

    }

    /** Displays TimeZone Message in User's Local Language
     *
     * <p>Displays an error message when the password entered is either blank or empty. Error message displayed
     * is based on the Locale of the user's machine.</p>
     *
     */
    private void displayTimeZone() {

        switch (userLocale.getLanguage()) {

            case "en":
                lbl_zoneid.setText("Current Timezone: " + userTimeZone.getId());
                break;

            case "fr":
                lbl_zoneid.setText("Fuseau horaire actuel: " + userTimeZone.getId());
                break;

            default:
                //Language Not Implemented.
        }

    }
}
