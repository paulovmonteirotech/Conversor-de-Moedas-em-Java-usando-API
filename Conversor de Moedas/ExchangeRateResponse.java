//PAULO MONTEIRO

//estrutura de dados que armazena pares de chave-valor
import java.util.Map;
/////////////////////////////////////////////////////////////////////////////////////


//ESTA CLASSE EH USADA PARA REPRESENTAR A RESPOSTA DA API DE CAMBIO.

public class ExchangeRateResponse {
    //código da moeda base
    private String base_code;
    //mapa das taxas de conversão. Cada entrada tem uma chave que é o código de uma moeda ("EUR")
    // e um valor que é a taxa de conversão
    private Map<String, Double> conversion_rates;


//////////////////////////////////////////////////////////////////////////////////////////////////////

//METODOS DE (Getters e Setters)
    public String getBaseCode() {
        return base_code;
    }

    public void setBaseCode(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    public void setConversionRates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
}
