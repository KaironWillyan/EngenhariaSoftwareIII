public class StringManipulation {

    public String removerEspacos(String texto) {
        return texto.replaceAll("\\s+", texto);
    }
    public String[] quebrarEmPalavras(String texto) {
        return texto.split("");
    }
}
