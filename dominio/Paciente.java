package dominio;

public class Paciente {
	
	private int numero;
	private double peso;
	private double altura;
	
	// Construtora:
	
	public Paciente(int numero, double peso, double altura) {
		this.numero = numero;
		this.peso = peso;
		this.altura = altura;
	}
	
	// Getters
	
	public int getNumero() {
		return numero;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public double getAltura() {
		return altura;
	}
	
	// Setters
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	// Outros métodos
	
	// Listar paciente:
	public void listarPacientes() {
		System.out.println("Número: " +numero +", Peso: " +peso +", Altura: " +altura);
	}
	
}
