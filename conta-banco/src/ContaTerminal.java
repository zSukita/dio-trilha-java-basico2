import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ContaTerminal {

    // Recebe uma String de um número, depois transforma em Double.
    public static double validarEConverter(String input) {
        String entradaFormatada = input;

        // Verifica se a entrada utiliza vírgula como separador decimal
        if (input.contains(",")) {
            entradaFormatada = input.replace(",", ".");
        }

        try {
            return Double.parseDouble(entradaFormatada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número válido.");
            return 0.0; // Valor padrão ou outra ação de tratamento de erro
        }

    }

    // Entidade Conta
    public static class Conta {
        private Integer numero;
        private String agencia;
        private String nomeCliente;
        private Double saldo;

        public Conta() {
        }

        public Conta(Integer numero, String agencia, String nomeCliente, Double saldo) {
            this.numero = numero;
            this.agencia = agencia;
            this.nomeCliente = nomeCliente;
            this.saldo = saldo;
        }

        public Integer getNumero() {
            return numero;
        }

        public void setNumero(Integer numero) {
            this.numero = numero;
        }

        public String getAgencia() {
            return agencia;
        }

        public void setAgencia(String agencia) {
            this.agencia = agencia;
        }

        public String getNomeCliente() {
            return nomeCliente;
        }

        public void setNomeCliente(String nomeCliente) {
            this.nomeCliente = nomeCliente;
        }

        public Double getSaldo() {
            return saldo;
        }

        public void setSaldo(Double saldo) {
            this.saldo = saldo;
        }

        @Override
        public String toString() {
            return "Conta{" +
                    "numero=" + numero +
                    ", agencia='" + agencia + '\'' +
                    ", nomeCliente='" + nomeCliente + '\'' +
                    ", saldo=" + saldo +
                    '}';
        }
    }

    public static void main(String[] args) {

        Scanner inputString = new Scanner(System.in);
        Scanner inputNumber = new Scanner(System.in);
        Conta conta = new Conta();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println("------------------------------------------------------------------------");
        System.out.println("--------- Bem vindo ao cadastro de conta do Banco SantanderDio ---------");
        System.out.println("--- A seguir digite os dados solicitados para a criação da sua conta ---");
        System.out.println("------------------------------------------------------------------------");

        System.out.println("Por favor, digite o número da Agência com o digito separado por (-)!");
        System.out.println("Agência: ");
        conta.setAgencia(inputString.nextLine());
        System.out.println("------------------------------------------------------------------------");

        System.out.println("Por favor, digite o número da Conta sem o digito!");
        System.out.println("Conta: ");
        conta.setNumero(inputNumber.nextInt());
        System.out.println("------------------------------------------------------------------------");

        System.out.println("Por favor, digite seu primeiro e último nome!");
        System.out.println("Nome Cliente: ");
        conta.setNomeCliente(inputString.nextLine());
        System.out.println("------------------------------------------------------------------------");

        System.out.println("Por favor, digite o valor para o seu saldo!");
        System.out.println("Saldo: ");
        String entrada = inputString.nextLine();
        conta.setSaldo(validarEConverter(entrada));
        System.out.println("------------------------------------------------------------------------");

        inputString.close();
        inputNumber.close();
        System.out.println("Processando os dados...");

        // Agendando a tarefa para ser executada após 2 segundos
        scheduler.schedule(() -> {
            System.out.printf(
                    "Olá %s, obrigado por criar uma conta em nosso banco é seja bem vindo, sua agência é %s, " +
                            "conta %d e seu saldo: R$ %.2f já está disponível para saque.",
                    conta.getNomeCliente(), conta.getAgencia(), conta.getNumero(), conta.getSaldo());
            scheduler.shutdown(); // Encerra o scheduler após a execução da tarefa
        }, 2, TimeUnit.SECONDS);

    }

}