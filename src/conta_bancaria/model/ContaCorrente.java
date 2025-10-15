package conta_bancaria.model;

// Classe ContaCorrente herda(faz extensão) da classe Conta
public class ContaCorrente extends Conta{
	
	private float limite;
	
	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		// Pega as infos da SuperClasse e passa para a SubClasse
		super(numero, agencia, tipo, titular, saldo);
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	@Override
	public boolean sacar(float valor) {
		if(this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\nSaldo insuficiente!");
			return false;
		}
		
		this.setSaldo(this.getSaldo() - valor);
		
		return true;
	}
	
	// Polimorfismo sobreescreve o metodo da superclasse adicionando as infos da subclasse
	@Override
	public void visualizar() {
		super.visualizar();
		System.out.printf("Limite da conta: R$ %.2f%n", this.limite);
	}
	

}
