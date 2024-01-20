package dad.geo.api;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import dad.geo.api.ObtenerDatosIP.IPAPIClient.IPAPIApi.IPAPIResponse.Language.Connection;
import dad.geo.api.ObtenerDatosIP.IPAPIClient.IPAPIApi.IPAPIResponse.Language.Currency;
import dad.geo.api.ObtenerDatosIP.IPAPIClient.IPAPIApi.IPAPIResponse.Language.Security;
import dad.geo.api.ObtenerDatosIP.IPAPIClient.IPAPIApi.IPAPIResponse.Language.TimeZone;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ObtenerDatosIP {
	public class IPAPIClient {

		// Define la interfaz de la API usando Retrofit
		public interface IPAPIApi {
			@GET("/ip_api.php?")
			Call<IPAPIResponse> obtenerDatosIP(@Query("ip") String ip);

			public class IPAPIResponse {
				@SerializedName("ip")
				public String ip;

				@SerializedName("hostname")
				public String hostname;

				@SerializedName("type")
				public String type;

				@SerializedName("continent_code")
				public String continentCode;

				@SerializedName("continent_name")
				public String continentName;

				@SerializedName("country_code")
				public String countryCode;

				@SerializedName("country_name")
				public String countryName;

				@SerializedName("region_code")
				public String regionCode;

				@SerializedName("region_name")
				public String regionName;

				@SerializedName("city")
				public String city;

				@SerializedName("zip")
				public String zip;

				@SerializedName("latitude")
				public double latitude;

				@SerializedName("longitude")
				public double longitude;

				@SerializedName("location")
				public Location location;

				@SerializedName("time_zone")
				public TimeZone timeZone;

				@SerializedName("currency")
				public Currency currency;

				@SerializedName("connection")
				public Connection connection;

				@SerializedName("security")
				public Security security;

				public static class Location {
					@SerializedName("geoname_id")
					public int geonameId;

					@SerializedName("capital")
					public String capital;

					@SerializedName("languages")
					public List<Language> languages;

					@SerializedName("country_flag")
					public String countryFlag;

					@SerializedName("country_flag_emoji")
					public String countryFlagEmoji;

					@SerializedName("country_flag_emoji_unicode")
					public String countryFlagEmojiUnicode;

					@SerializedName("calling_code")
					public String callingCode;

					@SerializedName("is_eu")
					public boolean isEu;
				}

				public static class Language {
					@SerializedName("code")
					public String code;

					@SerializedName("name")
					public String name;

					@SerializedName("native")
					public String nativeName;

					public static class TimeZone {
						@SerializedName("id")
						public String id;

						@SerializedName("current_time")
						public String currentTime;

						@SerializedName("gmt_offset")
						public int gmtOffset;

						@SerializedName("code")
						public String code;

						@SerializedName("is_daylight_saving")
						public boolean isDaylightSaving;
					}

					public static class Currency {
						@SerializedName("code")
						public String code;

						@SerializedName("name")
						public String name;

						@SerializedName("plural")
						public String plural;

						@SerializedName("symbol")
						public String symbol;

						@SerializedName("symbol_native")
						public String symbolNative;
					}

					public static class Connection {
						@SerializedName("asn")
						public int asn;

						@SerializedName("isp")
						public String isp;
					}

					public static class Security {
						@SerializedName("is_proxy")
						public boolean isProxy;

						@SerializedName("proxy_type")
						public String proxyType;

						@SerializedName("is_crawler")
						public boolean isCrawler;

						@SerializedName("crawler_name")
						public String crawlerName;

						@SerializedName("crawler_type")
						public String crawlerType;

						@SerializedName("is_tor")
						public boolean isTor;

						@SerializedName("threat_level")
						public String threatLevel;

						@SerializedName("threat_types")
						public List<String> threatTypes;
					}
				}
			}

			// Método para obtener datos a partir de una dirección IP
			public static IPAPIResponse obtenerDatosDesdeIP(String ip) {
				// Configura Retrofit
				Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ipapi.com")
						.addConverterFactory(GsonConverterFactory.create()).build();

				// Crea una instancia de la interfaz de la API
				IPAPIApi ipapiApi = retrofit.create(IPAPIApi.class);

				// Realiza la llamada a la API
				Call<IPAPIResponse> call = ipapiApi.obtenerDatosIP(ip);

				try {
					// Ejecuta la llamada de manera sincrónica
					retrofit2.Response<IPAPIResponse> response = call.execute();

					if (response.isSuccessful()) {
						return response.body();
					} else {
						System.out.println("Error en la solicitud: " + response.code());
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				// En caso de error, devuelve null
				return null;
			}

		}

	}
}
