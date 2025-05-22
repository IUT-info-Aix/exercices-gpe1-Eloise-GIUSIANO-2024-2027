open module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;
    exports com.example.partie1;
    exports com.example.partie2;

   // opens fr.amu.iut.exemple1 to javafx.fxml;
    exports fr.amu.iut.exemple1;
}
