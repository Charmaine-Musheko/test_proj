module library.librarysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens library.librarysystem to javafx.fxml;
    exports library.librarysystem;
}