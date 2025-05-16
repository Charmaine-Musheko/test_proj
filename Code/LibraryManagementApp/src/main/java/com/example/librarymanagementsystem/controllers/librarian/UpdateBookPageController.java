
package com.example.librarymanagementsystem.controllers.librarian;

        import com.example.librarymanagementsystem.SceneController;
        import com.example.librarymanagementsystem.errorHandling.InvalidBookException;
        import com.example.librarymanagementsystem.model.Book;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.AnchorPane;

        import java.net.URL;
        import java.util.ResourceBundle;

        import static com.example.librarymanagementsystem.dao.BookDao.updateBook;
        import static com.example.librarymanagementsystem.util.AlertMessage.error;
        import static com.example.librarymanagementsystem.util.AlertMessage.success;

public class UpdateBookPageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    Book bookToBeUpdated;

    @FXML
    private AnchorPane panelMain;

    @FXML
    private Button btnGoBack;

    @FXML
    private Button btnUpdateBook;

    @FXML
    private TextField txtBoxYearPublished;

    @FXML
    private TextField txtBoxEdition;

    @FXML
    private TextField txtBoxISBN;

    @FXML
    private TextField txtBoxTitle;

    SceneController sceneController = new SceneController();

    @FXML
    void goToViewAllBooks(ActionEvent event) {
        sceneController.changePane(event, "librarian/viewAllBooksPage.fxml", panelMain);
    }

    @FXML
    void updateBookDetails(ActionEvent event) {
        try {
            this.bookToBeUpdated.setIsbn(txtBoxISBN.getText());
            this.bookToBeUpdated.setTitle(txtBoxTitle.getText());
            this.bookToBeUpdated.setEdition(Integer.parseInt(txtBoxEdition.getText()));
            this.bookToBeUpdated.setYearPublished(Integer.parseInt(txtBoxYearPublished.getText()));

            int rows = updateBook(this.bookToBeUpdated);
            if (rows > 0) {
                success("Success", "Book successfully updated");
                goToViewAllBooks(event);
            } else {
                error("Error", "Invalid values entered");
            }
        } catch (InvalidBookException e){
            error("Invalid Book Details", e.getMessage());
        }

    }

    public void loadDetails(Book book){
        this.bookToBeUpdated = book;
        txtBoxISBN.appendText(book.getIsbn());
        txtBoxTitle.appendText(book.getTitle());
        txtBoxEdition.appendText(String.valueOf(book.getEdition()));
        txtBoxYearPublished.appendText(String.valueOf(book.getYearPublished()));
    }

}

