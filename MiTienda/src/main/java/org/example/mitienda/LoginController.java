package org.example.mitienda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> cbRol;

    @FXML
    public void initialize() {

        cbRol.getItems().addAll(
                "Administrador",
                "Vendedor",
                "Cajero"
        );
    }

    @FXML
    private void ingresar() {

        String usuario = txtUsuario.getText();
        String clave = txtPassword.getText();
        String rol = cbRol.getValue();

        if(usuario.isEmpty() || clave.isEmpty() || rol == null){

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setHeaderText(null);
            alert.setContentText("Complete todos los campos");
            alert.showAndWait();
            return;
        }

        if(usuario.equals("admin")
                && clave.equals("1234")
                && rol.equals("Administrador")) {

            try {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource("dashboard.fxml"));

                Scene scene = new Scene(loader.load());

                scene.getStylesheets().add(
                        getClass()
                                .getResource("styles.css")
                                .toExternalForm()
                );

                Stage stage =
                        (Stage) txtUsuario.getScene().getWindow();

                stage.setTitle("Mi Tienda");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setContentText("Acceso denegado");

            alert.showAndWait();
        }
    }
}