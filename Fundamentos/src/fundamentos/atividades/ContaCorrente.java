package fundamentos.atividades;

import java.time.LocalDate;

public class ContaCorrente
{
    private String nomeCliente;
    private Integer numeroConta;
    private Integer numeroAgencia;
    private Double saldoDisponivel;
    private Boolean contaCancelada;

    public static void main(String [] arguments)
    {
        ContaCorrente cliente1 = new ContaCorrente("Diego", 123, 456);
        ContaCorrente cliente2 = new ContaCorrente("Joao", 321, 654);
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusMonths(1);

        cliente1.depositar(1000.0);
        cliente1.sacar(500.0);
        cliente1.transferir(500.0, cliente2);
        cliente1.consultarExtrato(dataInicial, dataFinal);
        cliente1.cancelarConta("To sem dinheiro :(");
    }

    public ContaCorrente(String nomeCliente, Integer numeroConta, Integer numeroAgencia)
    {
        this.nomeCliente = nomeCliente;
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
        this.saldoDisponivel = 0.0;
        this.contaCancelada = false;
    }

    public boolean sacar(Double valor)
    {
        // Validar se o saldo é suficiente e exibir erro
        if (valor > this.saldoDisponivel)
        {
            System.out.println("Saldo insuficiente para sacar");
            System.out.println("Disponivel: " + this.saldoDisponivel);
            System.out.println("Valor: " + valor + "\n");
            return false;
        }

        // Efetuar o saque e exibir mensagem
        System.out.println("Saque efetuado com sucesso da conta de " + this.nomeCliente);
        System.out.println("Valor: " + valor + "\n");

        this.saldoDisponivel -= valor;
        return true;
    }

    public boolean depositar(Double valor)
    {
        // Validar o valor de depósito e exibir erro
        if (valor <= 0)
        {
            System.out.println("O valor de deposito deve ser superior a zero");
            System.out.println("Valor: " + valor + "\n");
            return false;
        }

        // Efetuar o depósito e exibir mensagem
        System.out.println("O deposito foi efetuado com sucesso na conta de " + this.nomeCliente);
        System.out.println("Valor: " + valor + "\n");

        this.saldoDisponivel += valor;
        return true;
    }

    public boolean transferir(Double valor, ContaCorrente destinatario)
    {
        // Validar se a transferência é válida e exibir erro
        if (valor <= 0)
        {
            System.out.println("O valor de tranferencia deve ser superior a zero");
            System.out.println("Valor: " + valor + "\n");
            return false;
        }

        if (valor > this.saldoDisponivel)
        {
            System.out.println("Saldo insuficiente para transferir");
            System.out.println("Disponivel: " + this.saldoDisponivel);
            System.out.println("Valor: " + valor + "\n");
            return false;
        }

        // Efetuar a transferência e exibir mensagem
        System.out.println("Tranferencia realizada com sucesso");
        System.out.println("De: " + this.nomeCliente);
        System.out.println("Para: " + destinatario.nomeCliente);
        System.out.println("Valor: " + valor + "\n");

        this.saldoDisponivel -= valor;
        destinatario.saldoDisponivel += valor;
        return true;
    }

    public boolean cancelarConta(String justificativa)
    {
        // Verificar a justificativa e exibir erro
        if (justificativa.isBlank())
        {
            System.out.println("Nao foi possivel cancelar a conta. E necessario uma justificativa\n");
            return false;
        }

        // Cancelar conta e exibir mensagem
        System.out.println("Conta cancelada com sucesso.");
        System.out.println("Nome: " + this.nomeCliente + "\n");
        this.contaCancelada = true;
        return true;
    }

    public boolean consultarExtrato(LocalDate dataInicial, LocalDate dataFinal)
    {
        // Verificar se a data inicial é posterior a final e exibir erro
        if (dataInicial.isAfter(dataFinal))
        {
            System.out.println("Nao foi possivel consultar o extrato. A data inicial deve ser anterior a final");
            System.out.println("Data inicial: " + dataInicial.toString());
            System.out.println("Data final: " + dataFinal.toString() + "\n");
            return false;
        }

        // Exibir mensagem consultando extratos
        System.out.println("Consultando extratos: ");
        System.out.println("De: " + dataInicial.toString());
        System.out.println("Ate: " + dataFinal.toString() + "\n");
        return true;
    }
}
