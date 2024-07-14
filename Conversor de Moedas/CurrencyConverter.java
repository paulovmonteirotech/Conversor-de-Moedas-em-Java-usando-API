//PAULO MONTEIRO

import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    //UTILIZACAO DE CURRENCIES POR SE TRATAR DE NOMECLATURA MONETARIA
        String[] currencies = {"USD (Dólar Americano)", "EUR (Euro)", "GBP (Libra Esterlina)", "JPY (Iene)", "AUD (Dólar Australiano)", "CAD (Dólar Canadense)"};
    //CRIA NOVO OBJETO DA CLASSE ExchangeRateService QUE CONTEM A KEY E URL DA API
        ExchangeRateService exchangeRateService = new ExchangeRateService();
    //LOOP
        while (true) {

    //PRIMEIRA PERGUNTA

            System.out.println("\nESCOLHA UMA DAS SEGUINTES OPÇÕES DE CONVERÇÃO DE MOEDAS!\n");

    //PERCORRENDO A CURRENCIES (FOI ESCOLHIDA NO LUGAR DE UMA ARRAYLIST POR SE TRATAR DE DINHEIRO

            for (int i = 0; i < currencies.length; i++) {
                System.out.println((i + 1) + ". BRL para " + currencies[i]);
            }
            System.out.println("\n->->->");

    //SEGUNDA PARTE DE OPÇÕES DE OUTRAS MOEDAS PARA O BRL

            for (int i = 0; i < currencies.length; i++) {
                System.out.println((i + 7) + ". " + currencies[i] + " para BRL");
            }

    //APENAS FORMATAÇÃO PARA ISOLAR O SAIR DO RESTO DA LISTA

            System.out.println("->->->");
            System.out.println("13. Sair");
            System.out.println("------------------");

    //CHAMANDO O INPUT NO TERMINAL PARA ESCOLHA DE CONVERCAO

            int option = scanner.nextInt();

    //SE FOR == 13 PARAR A APLICACAO
            if (option == 13) {
                System.out.println("PROGAMA ENCERRADO.");
                break;
            }
    //SE FOR MENOR QUE UM OU MAIOR QUE DOZE MSG DE INVALIDO

            if (option < 1 || option > 12) {
                System.out.println("OPÇÃO INVÁLIDA! TENTE NOVAMENTE.");
                continue;
            }

    //BOOLEANO PARA SABER SE VAI SER DE REAL OU PARA REAL
    //baseCurrency: Se isToBRL for true, a moeda base será a moeda selecionada para conversao para BRL. O índice option - 7 obtém a moeda de currencies a partir do indice correto. substring(0, 3) extrai o código da moeda (por exemplo, "USD", "EUR"). Se isToBRL for false, a moeda base sera "BRL".
    //targetCurrency: Se isToBRL for true, a moeda alvo eh "BRL". Se isToBRL for false, a moeda alvo sera a moeda selecionada para conversao a partir do indice option - 1, e substring(0, 3) obtem o codigo da moeda.

            boolean isToBRL = option > 6;
            String baseCurrency = isToBRL ? currencies[option - 7].substring(0, 3) : "BRL";
            String targetCurrency = isToBRL ? "BRL" : currencies[option - 1].substring(0, 3);

    //MSG PERSONALIZADA Se isToBRL for true, a mensagem exibirá a moeda de origem. Se isToBRL for false, a mensagem exibirá "Real Brasileiro (BRL)".

            System.out.print("Informe o valor em " + (isToBRL ? currencies[option - 7] : "Real Brasileiro (BRL)") + " que deseja converter: \n\n");

    //OBTEM VALOR PARA CONVERCAO E ARMAZENA EM "amount"
            double amount = scanner.nextDouble();

    //exchangeRateService.getConvertedAmount: Chama um método do serviço de taxa de câmbio para obter o valor convertido.
    //System.out.printf: Formata e exibe o valor convertido. Se isToBRL for true, exibe "Real Brasileiro (BRL)"; caso contrário, exibe a moeda de destino.
    //catch (Exception e): Captura e trata qualquer exceção que possa ocorrer durante a conversão e exibe uma mensagem de erro.
            try {
                double convertedAmount = exchangeRateService.getConvertedAmount(baseCurrency, targetCurrency, amount);
                System.out.printf("VALOR CONVERTIDO: %.2f %s\n", convertedAmount, (isToBRL ? "Real Brasileiro (BRL)\n" : currencies[option - 1]));
            } catch (Exception e) {
                System.out.println("Erro ao obter a taxa de conversão. Tente novamente.");
            }
    //Mais uma linha de formatacao
            System.out.println("------------------\n");
        }
    //fecha o scanner
        scanner.close();
    }
}
