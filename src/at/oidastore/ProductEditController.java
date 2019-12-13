package at.oidastore;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.PrintWriter;

public class ProductEditController {
    @FXML private ListView<Product> productList;
    @FXML private Button prodListReportBtn;

    @FXML private TextField prodNameTxtFld;
    @FXML private TextField prodQuantTxtFld;
    @FXML private TextField prodOldPriceTxtFld;
    @FXML private TextField prodNewPriceTxtFld;

    @FXML private Button prodUpdateBtn;

    @FXML private ImageView prodImageView;
    @FXML private Text prodDescription;

    @FXML
    public void initialize() {
        _initializeProductList();
        _bindFormToList();
        _initButtons();
    }

    private void _initializeProductList() {
        productList.getItems().addAll(
                new Product("Katzenmilch", "6x50ml", 19.90,17.99, "milch.png",
                        "Mit dem Besten von der Katze!"),
                new Product("Marsriegel", "1x200g Tafel", 650.50, 399.99, "mars.jpg",
                        "Feiner Riegel aus Marsgestein von erlesener Qualität, mit freundlichen Grüßen von E. Musk."),
                new Product("Kalte Soße", "700ml Glasflasche", 4.39, 2.90, "sosse.jpg",
                        "Überraschungssoße von variabler Zusammensetzung, wie von Oma! Am besten eiskalt genießen."),
                new Product("Warme Soße", "750ml Glasflasche", 7.49, 6.90, "sosse2.jpg",
                        "Noch ganz warm...! (Rechtsanspruch auf spezifische Kauftemperatur ausgeschlossen)"),
                new Product("Frisches Salatblatt", "1 Blt.", 0.49, 0.39, "blatt.jpg",
                        "Gibt Ihrem Jausensandwich das gewisse BOOAH!"),
                new Product("Original Emmentaler Käsekuchen", "1 Stk.", 13.49, 8.90, "kas.jpg",
                        "Hausgemacht nach dem Ursprungsrezept. Ein vollmundiger Genuß!")
        );
    }

    private void _bindFormToList() {
        productList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super Product>) change -> {
            change.next();
            Product selected = change.getAddedSubList().get(0);
            prodNameTxtFld.setText(selected.getName());
            prodQuantTxtFld.setText(selected.getQuantity());
            prodOldPriceTxtFld.setText(Double.toString(selected.getOldPrice()));
            prodNewPriceTxtFld.setText(Double.toString(selected.getNewPrice()));
            prodImageView.setImage(_makeImage(selected.getImgName()));
            prodDescription.setText(selected.getDescription());
        });
    }

    private Image _makeImage(String imgname) {
        Image img = new Image("stuff/" + imgname);
        return img;
    }

    private void _initButtons() {
        prodUpdateBtn.setOnAction(e -> {
            Product activeItem = productList.getSelectionModel().getSelectedItems().get(0);
            activeItem.setOldPrice(Double.parseDouble(prodOldPriceTxtFld.getText()));
            activeItem.setNewPrice(Double.parseDouble(prodNewPriceTxtFld.getText()));
            productList.refresh();
        });

        prodListReportBtn.setOnAction(e -> {
            try {
                _printReport();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void _printReport() throws IOException {
        String outfile = "oidastore-productlist-report.txt";
        PrintWriter pw = new PrintWriter(outfile);
        for (Product p : productList.getItems()) {
            pw.println(p.getName());
            pw.println(p.getQuantity());
            pw.println("statt " + p.getOldPrice());
            pw.println("nur " + p.getNewPrice());
            pw.println();
        }
        pw.close();
    }
}
