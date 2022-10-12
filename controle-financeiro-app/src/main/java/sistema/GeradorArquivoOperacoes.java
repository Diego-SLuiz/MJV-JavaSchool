package sistema;

import cliente.Cliente;
import conta.ContaCorrente;
import conta.Operacao;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class GeradorArquivoOperacoes
{
    public static void gerarArquivo(ContaCorrente conta)
    {
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusMonths(1);
        ArrayList<Operacao> extrato = conta.consultarExtrato(dataInicial, dataFinal);
        StringBuilder resultado = new StringBuilder();
        Cliente cliente = conta.getCliente();

        for (Operacao x : extrato)
        {
            String data = String.format("%8.8s",  x.getData().toString().replaceAll("-", ""));
            String cpf = "000".concat(cliente.getCpf().replaceAll("-|\\.", ""));
            String nome = String.format("%30.30s", cliente.getNome()).toUpperCase();
            String valor =  String.format("%010d", Integer.parseInt(x.getValor().toString().replaceAll(",|\\.", "")));
            String estornado = false ? "1" : "0";
            char tipo = x.getTipo().getSigla();

            resultado.append(data);
            resultado.append(cpf);
            resultado.append(nome);
            resultado.append(valor);
            resultado.append(tipo);
            resultado.append(estornado);
            resultado.append("\n");
        }

        String nomeArquivo = dataInicial + "_lancamentos.txt";
        File diretorio = new File(".\\src\\main\\resources\\lancamentos\\");

        if (!diretorio.exists())
            diretorio.mkdirs();

        File arquivo = new File(diretorio, nomeArquivo);
        Path path = arquivo.toPath();

        try
        {
            Files.write(path, resultado.toString().getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException error)
        {
            throw new RuntimeException(error);
        }
    }
}
