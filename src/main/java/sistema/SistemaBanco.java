package sistema;

import cliente.Cliente;
import cliente.enumeration.TipoVinculo;
import conta.ContaBancaria;
import conta.ContaCorrente;
import conta.ContaPoupanca;

import java.util.ArrayList;
import java.util.Random;

public class SistemaBanco {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = SistemaBanco.criarClientes();
        System.out.println(clientes);
    }

    private static ArrayList<Cliente> criarClientes() {
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Random random = new Random();

        // Valores gerados utilizando ferramentas do site: https://www.4devs.com.br/
        String[] nomeClientes = {"Hogauco", "Veurl", "Leybir", "Zaysa", "Rudon", "Duxol", "Cobaon", "Cefai", "Siexaon", "Hiuxe"};
        String[] identificacaoClientes = {"39.736.415/0001-63", "27.756.577/0001-84", "12.302.295/0001-30", "21.085.892/0001-40", "41.362.215/0001-30", "603.955.490-86", "261.362.970-35", "528.441.280-09", "899.456.390-30", "246.575.610-90"};

        for (int i = 0; i < nomeClientes.length; i++) {
            String nome = nomeClientes[i];
            String identificacao = identificacaoClientes[i];
            Integer numeroConta = random.nextInt(9999);
            Integer numeroAgencia = random.nextInt(9999);
            TipoVinculo vinculo;
            ContaBancaria conta;

            if (identificacao.length() > 14) {
                vinculo = TipoVinculo.CNPJ;
                conta = new ContaCorrente(numeroConta, numeroAgencia);
            } else {
                vinculo = TipoVinculo.CPF;
                conta = new ContaPoupanca(numeroConta, numeroAgencia);
            }

            Cliente clienteGerado = new Cliente(nome, vinculo, identificacao);
            clienteGerado.setConta(conta);
            clientes.add(clienteGerado);
        }

        return clientes;
    }
}
