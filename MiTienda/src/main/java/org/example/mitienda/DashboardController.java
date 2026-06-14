package org.example.mitienda;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DashboardController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;

    @FXML
    private ComboBox<String> cbEstado;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, String> colEstado;

    private final ObservableList<Producto> lista =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        cbCategoria.getItems().addAll(
                "Electrónica",
                "Ropa",
                "Hogar"
        );

        cbEstado.getItems().addAll(
                "Activo",
                "Inactivo"
        );

        colCodigo.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getCodigo()));

        colNombre.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getNombre()));

        colCategoria.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getCategoria()));

        colPrecio.setCellValueFactory(
                data -> new SimpleObjectProperty<>(data.getValue().getPrecio()));

        colStock.setCellValueFactory(
                data -> new SimpleObjectProperty<>(data.getValue().getStock()));

        colEstado.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getEstado()));

        tabla.setItems(lista);

        tabla.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, producto) -> {

                    if (producto != null) {

                        txtCodigo.setText(producto.getCodigo());
                        txtNombre.setText(producto.getNombre());
                        cbCategoria.setValue(producto.getCategoria());
                        txtPrecio.setText(String.valueOf(producto.getPrecio()));
                        txtStock.setText(String.valueOf(producto.getStock()));
                        cbEstado.setValue(producto.getEstado());
                    }
                });
    }

    @FXML
    private void guardar() {

        try {

            if (txtCodigo.getText().isEmpty() ||
                    txtNombre.getText().isEmpty() ||
                    cbCategoria.getValue() == null ||
                    txtPrecio.getText().isEmpty() ||
                    txtStock.getText().isEmpty() ||
                    cbEstado.getValue() == null) {

                mostrarMensaje("Complete todos los campos");
                return;
            }

            Producto p = new Producto(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    cbCategoria.getValue(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText()),
                    cbEstado.getValue()
            );

            lista.add(p);

            limpiar();

        } catch (Exception e) {

            mostrarMensaje("Precio o Stock inválido");
        }
    }

    @FXML
    private void actualizar() {

        try {

            Producto p = tabla.getSelectionModel().getSelectedItem();

            if (p == null) {
                mostrarMensaje("Seleccione un producto");
                return;
            }

            p.setCodigo(txtCodigo.getText());
            p.setNombre(txtNombre.getText());
            p.setCategoria(cbCategoria.getValue());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setStock(Integer.parseInt(txtStock.getText()));
            p.setEstado(cbEstado.getValue());

            tabla.refresh();

            mostrarMensaje("Producto actualizado");

        } catch (Exception e) {

            mostrarMensaje("Datos inválidos");
        }
    }

    @FXML
    private void eliminar() {

        Producto p = tabla.getSelectionModel().getSelectedItem();

        if (p != null) {

            lista.remove(p);
            limpiar();
        }
    }

    @FXML
    private void limpiar() {

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtStock.clear();

        cbCategoria.setValue(null);
        cbEstado.setValue(null);

        tabla.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String texto) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);
        alert.setContentText(texto);

        alert.showAndWait();
    }
}