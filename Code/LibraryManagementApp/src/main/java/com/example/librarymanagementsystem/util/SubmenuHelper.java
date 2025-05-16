package com.example.librarymanagementsystem.util;

import javafx.scene.Node;

public class SubmenuHelper {
    public void manageMenu(Node node){
        node.setVisible(!node.isVisible());
        node.setManaged(!node.isManaged());
    }
}
