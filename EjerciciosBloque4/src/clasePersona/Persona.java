package clasePersona;

import java.util.Random;

public class Persona {

    private final static Sexo SEXO_DEF = Sexo.H;

    // Constantes para los resultados del IMC
    public final static int BAJO_PESO = -1;
    public final static int PESO_IDEAL = 0;
    public final static int SOBREPESO = 1;

    // Atributos
    private String nombre;
    private int edad;
    private String DNI;
    private Sexo sexo;
    private float peso;
    private float altura;

    // --- CONSTRUCTORES ---

    public Persona() {
        this("", 0, SEXO_DEF, 0, 0);
    }

    public Persona(String nombre, int edad, Sexo sexo) {
        this(nombre, edad, sexo, 0, 0);
    }

    public Persona(String nombre, int edad, Sexo sexo, float peso, float altura) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = (sexo == null) ? SEXO_DEF : sexo;
        this.peso = peso;
        this.altura = altura;
        generaDNI();
    }


    public int calcularIMC() {
        // Fórmula: peso / altura^2
        double imc = peso / (Math.pow(altura, 2));

        if (imc < 20) {
            return BAJO_PESO;
        } else if (imc >= 20 && imc <= 25) {
            return PESO_IDEAL;
        } else {
            return SOBREPESO;
        }
    }

    public boolean esMayorDeEdad() {
        return edad >= 18;
    }

    private void comprobarSexo(Sexo sexo) {
        if (sexo != Sexo.H && sexo != Sexo.M) {
            this.sexo = SEXO_DEF;
        }
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
        comprobarSexo(this.sexo);
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }


    public String getNombre() {
        return nombre;
    }

    public Sexo getSexo() {
        return sexo;
    }


    private void generaDNI() {
        Random rnd = new Random();
        int numeroDNI = rnd.nextInt(90000000) + 10000000;
        char letras[] = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        this.DNI = String.valueOf(numeroDNI) + letras[numeroDNI % 23];
    }

    @Override
    public String toString() {
        return "Persona[nombre=" + nombre + ", edad=" + edad + ", DNI=" + DNI +
                ", sexo=" + sexo + ", peso=" + peso + ", altura=" + altura + "]";
    }
}