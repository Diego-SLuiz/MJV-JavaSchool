package fundamentos.atividades;

public class TratandoExcecao
{
    public static void main(String [] arguments)
    {
        int primeiroNumero = 0;
        int segundoNumero = 11;

        try
        {
            TratandoExcecao.somar(primeiroNumero, segundoNumero);
        }
        catch (IllegalArgumentException error)
        {
            System.out.println(error.getMessage());
        }
    }

    public static int somar(int primeiroNumero, int segundoNumero) throws IllegalArgumentException
    {
        if (primeiroNumero < 0)
            throw new IllegalArgumentException("O primeiro numero precisa ser maior ou igual a zero");
        if (segundoNumero > 10)
            throw new IllegalArgumentException("O segundo numero precisa ser menor que 10");

        return Math.max(primeiroNumero, segundoNumero) - Math.min(primeiroNumero, segundoNumero);
    }
}
