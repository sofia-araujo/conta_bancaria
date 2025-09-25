package conta_bancaria.model;

public abstract class Conta {
	//Atributos da Classe
	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;
		
	//Metodo Construtor
	public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
	}
		
	//Metodos Get e Set
	public int getNumero() {
		return numero;
	}
	 
	public void setNumero(int numero) {
		this.numero = numero;
	}
	 
	public int getAgencia() {
		return agencia;
	}
	 
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	 
	public int getTipo() {
		return tipo;
	}
	 
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	 
	public String getTitular() {
		return titular;
	}
	 
	public void setTitular(String titular) {
		this.titular = titular;
	}
	 
	public float getSaldo() {
		return saldo;
	}
	 
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public boolean sacar(float valor) {
		if(this.saldo < valor) {
			System.out.println("\nSaldo insuficiente");
			return false;
		}
		
		this.saldo -= valor;
		
		return true;
	}
	
	public void depositar(float valor) {
		this.saldo += valor;
	}
	
	public void visualizar() {
		String tipo = " ";
		
		switch(this.tipo) {
			case 1 -> tipo = "Conta corrente (CC)";
			case 2 -> tipo = "Conta poupança (CP)";
			default -> tipo = "Desconhecido";
		}
		
		System.out.println("\n**************************************");
		System.out.println("Dados da conta");
		System.out.println("**************************************");
		System.out.printf("Número da conta: %d%n", this.numero);
		System.out.printf("Agencia da conta: %d%n", this.agencia);
		System.out.printf("Tipo da conta: %s%n", tipo);
		System.out.printf("Titular da conta: %s%n", this.titular);
		System.out.printf("Saldo da conta: R$ %.2f%n", this.saldo);
	}
}
