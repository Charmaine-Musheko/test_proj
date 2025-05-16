module com.example.librarymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

//    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.librarymanagementsystem to javafx.fxml;
    exports com.example.librarymanagementsystem;

    opens com.example.librarymanagementsystem.controllers to javafx.fxml;
    exports com.example.librarymanagementsystem.controllers;

    opens com.example.librarymanagementsystem.controllers.librarian  to javafx.fxml;
    exports com.example.librarymanagementsystem.controllers.librarian ;

    opens com.example.librarymanagementsystem.errorHandling to javafx.fxml;
    exports com.example.librarymanagementsystem.errorHandling;

    opens com.example.librarymanagementsystem.controllers.admin to javafx.fxml;
    exports com.example.librarymanagementsystem.controllers.admin;

    opens com.example.librarymanagementsystem.controllers.members to javafx.fxml;
    exports com.example.librarymanagementsystem.controllers.members;

    opens com.example.librarymanagementsystem.dao to javafx.fxml;
    exports com.example.librarymanagementsystem.dao;

    opens com.example.librarymanagementsystem.model to javafx.fxml;
    exports com.example.librarymanagementsystem.model;
}