    //Polimorfismo
    
    interface Forma {
        double calcularArea();
    }
    
    class Retangulo implements Forma {
        private double comprimento;
        private double largura;
    
        public Retangulo(double comprimento, double largura) {
            this.comprimento = comprimento;
            this.largura = largura;
        }
    
        @Override
        public double calcularArea() {
            return comprimento * largura;
        }
    }
    
    class Circulo implements Forma {
        private double raio;
    
        public Circulo(double raio) {
            this.raio = raio;
        }
    
        @Override
        public double calcularArea() {
            return Math.PI * raio * raio;
        }
    }
    
    public class CalculadoraArea {
        public double calcularAreaDaForma(Forma forma) {
            return forma.calcularArea();
        }
    }
