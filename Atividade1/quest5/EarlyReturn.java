public class EarlyReturn {

    public static void main(String[] args) {
        int resultado = calcularResultado(5, 0);
        System.out.println("Resultado: " + resultado);
    }

    public static int calcularResultado(int a, int b) {
        if (b == 0) {
            System.out.println("Divisão por zero não é permitida.");
            return 0;
        }
        int resultado = a / b;
        return resultado;
    }
}