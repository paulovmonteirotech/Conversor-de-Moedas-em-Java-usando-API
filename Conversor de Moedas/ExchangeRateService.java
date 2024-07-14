//PAULO MONTEIRO

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//CRIA CLASSE QUE CONTEM  KEY E URL DA API
public class ExchangeRateService {
    private static final String API_KEY = "b47b555e1cc4beec8d7edb49";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double getConvertedAmount(String baseCurrency, String targetCurrency, double amount) throws Exception {
        String urlString = API_URL + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        String jsonResponse = content.toString();
        //System.out.println("JSON Response: " + jsonResponse); // Adicionando debug para inspeção (deixar comentado)

        Gson gson = new Gson();
        ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

        if (response.getConversionRates() == null) {
            throw new NullPointerException("conversionRates is null in the response");
        }

        double exchangeRate = response.getConversionRates().get(targetCurrency);
        return amount * exchangeRate;
    }
}
