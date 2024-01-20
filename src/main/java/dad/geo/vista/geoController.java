package dad.geo.vista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;

import dad.geo.api.ObtenerDatosIP.IPAPIClient;
import dad.geo.api.ObtenerDatosIP.IPAPIClient.IPAPIApi.IPAPIResponse;
import dad.geo.api.obtenerIPPublica;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class geoController implements Initializable {

	@FXML
	private Label ASNLabel;

	@FXML
	private Button BuscarButton;

	@FXML
	private Label CallingLabel;

	@FXML
	private Label CityLabel;

	@FXML
	private CheckBox CrawlerCheck;

	@FXML
	private Label CurrencyLabel;

	@FXML
	private Label Hostnamelabel;

	@FXML
	private Label IpAddressLabel;

	@FXML
	private Label LanguajeLabel;

	@FXML
	private Label LatitudeLabel;

	@FXML
	private ImageView LocationImagen;

	@FXML
	private Label LocationLabel;

	@FXML
	private Label LongitudeLabel;

	@FXML
	private Label PotecialLabel;

	@FXML
	private CheckBox ProxyCheck;

	@FXML
	private TabPane TabPaneView;

	@FXML
	private Label ThreatLabel;

	@FXML
	private CheckBox TorCheck;

	@FXML
	private Label TypeLabel;

	@FXML
	private BorderPane View;

	@FXML
	private Label ZipLabel;

	@FXML
	private Label ZoneLabel;

	@FXML
	private TextField ipTextF;

	@FXML
	private Label RegisteredLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		CompletableFuture.runAsync(() -> {
			String IPpublica = obtenerIPPublica.obtenerIPPublica("at_VlLqkVjJ5GLZiqoWEMh48VWHp1m9h");
			IPAPIResponse ipapiResponse = IPAPIClient.IPAPIApi.obtenerDatosDesdeIP(IPpublica);
			Platform.runLater(() -> ActualizarUI(ipapiResponse));
		});
	}

	@FXML
	void OnCheck(ActionEvent event) {

		String ipText = ipTextF.getText();
		CompletableFuture.runAsync(() -> {
			IPAPIResponse ipapiResponse = IPAPIClient.IPAPIApi.obtenerDatosDesdeIP(ipText);
			Platform.runLater(() -> ActualizarUI(ipapiResponse));
		});

	}

	public geoController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GeoView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public BorderPane getView() {
		return View;
	}

	private void ActualizarUI(IPAPIResponse ipapiResponse) {

		ipTextF.setText(ipapiResponse.ip);

		// 1 TAP
		LatitudeLabel.setText(String.valueOf(ipapiResponse.latitude));
		LongitudeLabel.setText(String.valueOf(ipapiResponse.longitude));

		CityLabel.setText(ipapiResponse.city);
		ZipLabel.setText(ipapiResponse.zip);

		LanguajeLabel.setText(ipapiResponse.location.languages.get(0).name + "(" + ipapiResponse.countryCode + ")");
		ZoneLabel.setText(ipapiResponse.timeZone.code);

		CallingLabel.setText("+ " + ipapiResponse.location.callingCode);
		CurrencyLabel.setText(ipapiResponse.currency.name + " (" + ipapiResponse.currency.symbol + ")");

		LocationLabel.setText(ipapiResponse.countryName + " (" + ipapiResponse.countryCode + ")");

		Image flag = new Image(getClass().getResourceAsStream("/icons/96x64/" + ipapiResponse.countryCode + ".png"));
		LocationImagen.setImage(flag);

		// 2 TAP

		IpAddressLabel.setText(ipapiResponse.ip);
		TypeLabel.setText(ipapiResponse.type);
		Hostnamelabel.setText(ipapiResponse.hostname);
		ASNLabel.setText(String.valueOf(ipapiResponse.connection.asn));
		RegisteredLabel.setText(ipapiResponse.connection.isp);

		// 3 TAP

		ProxyCheck.setSelected(ipapiResponse.security.isProxy);

		CrawlerCheck.setSelected(ipapiResponse.security.isCrawler);

		TorCheck.setSelected(ipapiResponse.security.isTor);

		PotecialLabel.setText(String.valueOf(ipapiResponse.security.threatTypes));

	}

}
