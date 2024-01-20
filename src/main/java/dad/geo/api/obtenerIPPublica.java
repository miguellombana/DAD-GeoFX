package dad.geo.api;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class obtenerIPPublica {

	// Define la interfaz de la API usando Retrofit
	public interface IpifyApi {
		@GET("/")
		Call<IpifyResponse> obtenerDireccionIP(@Query("format") String format, @Query("apiKey") String apiKey);
	}

	// Clase para representar la respuesta JSON de ipify
	public static class IpifyResponse {
		@SerializedName("ip")
		public String ip;
	}

	// Método para obtener la dirección IP pública
	public static String obtenerIPPublica(String apiKey) {
		// Configura Retrofit
		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.ipify.org")
				.addConverterFactory(GsonConverterFactory.create()).build();

		// Crea una instancia de la interfaz de la API
		IpifyApi ipifyApi = retrofit.create(IpifyApi.class);

		// Realiza la llamada a la API
		Call<IpifyResponse> call = ipifyApi.obtenerDireccionIP("json", apiKey);

		try {
			// Ejecuta la llamada de manera sincrónica
			retrofit2.Response<IpifyResponse> response = call.execute();

			if (response.isSuccessful()) {
				IpifyResponse ipifyResponse = response.body();
				// Devuelve la dirección IP pública
				return ipifyResponse.ip;
			} else {
				System.out.println("Error en la solicitud: " + response.code());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// En caso de error, devuelve null o una cadena indicativa
		return null;
	}

}
