module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lifo_and_fifo_visual to javafx.fxml;
    exports com.example.lifo_and_fifo_visual;
    exports com.example.lifo_and_fifo_visual.controllers;
    opens com.example.lifo_and_fifo_visual.controllers to javafx.fxml;
}