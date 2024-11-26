module org.edem.textprocessing {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.jshell;

    opens org.edem.textprocessing to javafx.fxml;
    opens org.edem.textprocessing.controller to javafx.fxml;
    exports org.edem.textprocessing;
    exports org.edem.textprocessing.utils;
    exports  org.edem.textprocessing.controller;
    opens org.edem.textprocessing.utils to javafx.fxml;
}