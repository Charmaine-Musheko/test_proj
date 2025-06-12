
import com.example.librarymanagementsystem.util.AlertMessage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RegisterController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField homeAddressField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField repeatPasswordField;
    @FXML
    private TextField dateOfBirthField;

    private Database db = new Database();

    @FXML
    public void submitRegistration(ActionEvent event) {
        if (!passwordField.getText().equals(repeatPasswordField.getText())) {
            AlertMessage.error("Registration Failed", "Passwords do not match");
            return;
        }
        try {
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthField.getText());
            db.insertRecord(firstNameField.getText(), lastNameField.getText(), emailField.getText(),
                    homeAddressField.getText(), telephoneField.getText(), passwordField.getText(),
                    repeatPasswordField.getText(), dateOfBirth);
            AlertMessage.success("Success", "Registration successful!");
            clearFields();
        } catch (DateTimeParseException e) {
            AlertMessage.error("Invalid Date", "Please enter date in YYYY-MM-DD format");
        }
    }

    private void clearFields() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        homeAddressField.clear();
        telephoneField.clear();
        passwordField.clear();
        repeatPasswordField.clear();
        dateOfBirthField.clear();
    }
}