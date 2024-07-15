# Conversor-de-Moedas-em-Java-usando-API
Conversor de Moedas em Java Usando exchangerate-api

(adicione a dependencia Gson 2.11.0 na sua máquina
https://mvnrepository.com/artifact/com.google.code.gson/gson)


Das três Classes utilizadas apenas uma terá uma explicação detalhada (ExchangeRateService) por ser a mais complexa, porém as outras terão uma breve explicação aqui e varios comentários no código, passo a passo.

Explicação das classes:
CurrencyConverter: A classe principal que interage com o usuário via console, oferecendo opções de conversão de moeda e recebendo o valor a ser convertido.
ExchangeRateService: A classe responsável por fazer a solicitação à API de taxa de câmbio, processar a resposta JSON usando Gson e calcular o valor convertido.
ExchangeRateResponse: A classe de modelo para mapear a resposta JSON da API.




COMO FAZER:
Adicione a dependência Gson 	2.11.0
https://mvnrepository.com/artifact/com.google.code.gson/gson

Adquira uma chave na api de cambio:
https://www.exchangerate-api.com/

Crie as três classes em arquivos separados no seu projeto ( utilizei o IntelliJ IDEA).


CurrencyConverter: 
usado o método while para loop COMO UMA CLASSE MAIN (comentários no própio código)


--------------------------------------------------------------------------------------------------------------------------------------------------------
ExchangeRateService:

import com.google.gson.Gson;: Importa a classe Gson da biblioteca Gson, que é usada para converter JSON em objetos Java e vice-versa.

import java.io.BufferedReader;: Importa a classe BufferedReader, que é usada para ler texto de uma entrada de fluxo de dados de forma eficiente.

import java.io.InputStreamReader;: Importa a classe InputStreamReader, que é usada para ler bytes e convertê-los em caracteres. É usada em conjunto com BufferedReader para ler a resposta da API.

import java.net.HttpURLConnection;: Importa a classe HttpURLConnection, que é usada para fazer solicitações HTTP.

import java.net.URL;: Importa a classe URL, que é usada para representar um endereço URL e estabelecer uma conexão com ele.


private static final String API_KEY = "b47b555e1cc4beec8d7edb49";: Define uma chave de API para autenticar as solicitações feitas para a API de taxas de câmbio. Este é um valor fictício; em um código real, você usaria sua própria chave de API.

private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";: Define a URL base da API para obter as taxas de câmbio mais recentes, concatenando a chave da API com a URL base.
public double getConvertedAmount(String baseCurrency, String targetCurrency, double amount) throws Exception: Define um método público que recebe a moeda base, a moeda alvo e o valor a ser convertido. Retorna o valor convertido e pode lançar uma exceção se algo der errado.

String urlString = API_URL + baseCurrency;: Constrói a URL para a solicitação HTTP, adicionando o código da moeda base à URL base.

URL url = new URL(urlString);: Cria um objeto URL a partir da string da URL.

HttpURLConnection conn = (HttpURLConnection) url.openConnection();: Abre uma conexão HTTP com a URL.

conn.setRequestMethod("GET");: Define o método da solicitação HTTP como GET.
BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));: Cria um BufferedReader para ler a resposta da API da entrada do fluxo.

String inputLine; StringBuilder content = new StringBuilder();: Declara uma variável para armazenar cada linha lida e um StringBuilder para construir a resposta completa.

while ((inputLine = in.readLine()) != null) { content.append(inputLine); }: Lê a resposta linha por linha e a adiciona ao StringBuilder.

in.close(); conn.disconnect();: Fecha o BufferedReader e desconecta a conexão HTTP.
String jsonResponse = content.toString();: Converte o StringBuilder para uma string, que contém a resposta JSON da API.

Gson gson = new Gson();: Cria uma instância da classe Gson.

ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);: Converte a resposta JSON em um objeto da classe ExchangeRateResponse usando Gson.
if (response.getConversionRates() == null) { throw new NullPointerException("conversionRates is null in the response"); }: Verifica se a resposta contém as taxas de conversão. Se não, lança uma exceção.

double exchangeRate = response.getConversionRates().get(targetCurrency);: Obtém a taxa de conversão para a moeda alvo do mapa de taxas de conversão.

return amount * exchangeRate;: Calcula e retorna o valor convertido multiplicando o valor original pela taxa de conversão.

Resumo
ExchangeRateService: Classe que lida com a comunicação com a API de taxas de câmbio.
API_KEY e API_URL: Usados para construir a URL da solicitação.
getConvertedAmount: Método para obter a taxa de câmbio e calcular o valor convertido.
Processo:
Constrói a URL com a moeda base.
Faz a solicitação HTTP e lê a resposta.
Converte a resposta JSON em um objeto Java.
Obtém a taxa de conversão e calcula o valor convertido.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------


ExchangeRateResponse: (Comentarios detalhados no codigo)
Esta classe é usada para representar a resposta da API de taxa de câmbio.
Execute a classe CurrencyConverter.
Este código usa a biblioteca Gson para transformar a resposta JSON da API em um objeto Java, melhorando a organização e a legibilidade do código.
Classe baseada na interface map do java.util https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/util/Map.html
