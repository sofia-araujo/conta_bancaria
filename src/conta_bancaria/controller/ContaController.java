package conta_bancaria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import conta_bancaria.model.Conta;
import conta_bancaria.repository.ContaRepository;
import conta_bancaria.util.Cores;

//Implementa todos os metodos da interface 
public class ContaController implements ContaRepository{

	private List<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;
	
	@Override
	public void listarTodas() {
		for(var conta : listaContas) {
			conta.visualizar();
		}
	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println(Cores.TEXT_GREEN_BOLD+"\n🎉 Conta cadastrada com sucesso! ");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscarConta = buscarNaCollection(conta.getNumero());
			
		if(buscarConta != null) {
			listaContas.set(listaContas.indexOf(buscarConta), conta);
			System.out.printf(Cores.TEXT_GREEN_BOLD+"%n A Conta número: %d foi atualizada com sucesso!%n", conta.getNumero());
		}else {
			System.out.printf(Cores.TEXT_YELLOW_BOLD+"%n❓ A Conta número: %d não foi encontrada!%n", conta.getNumero());
		}
		
	}

	@Override
	public void procurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			conta.visualizar();
		}else {
			System.out.printf(Cores.TEXT_YELLOW_BOLD+"%n❓ A Conta número: %d não foi encontrada!%n", numero);
		}
		
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);
		 
		if (conta != null) {
			if (listaContas.remove(conta) == true) {
				System.out.printf("\nConta %d foi encerrada com sucesso!%n", numero);
			}
		}else {
			System.out.printf(Cores.TEXT_YELLOW_BOLD+"%n❓ A Conta número: %d não foi encontrada!%n", numero);
		}
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if(conta != null) {
			if(conta.sacar(valor)) {
				System.out.printf("\nO saque do valor %.2f na conta número: %d foi efetuado com sucesso!%n", valor, numero);
			}
		}else {
			System.out.printf(Cores.TEXT_YELLOW_BOLD+"%n❓ A Conta número: %d não foi encontrada!%n", numero);
		}
		
	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		if(conta != null) {
			conta.depositar(valor);
			System.out.printf("\nO deposito do valor %.2f na conta número: %d foi efetuado com sucesso!%n", valor, numero);
			
		}else {
			System.out.printf(Cores.TEXT_YELLOW_BOLD+"%n❓ A Conta número: %d não foi encontrada!%n", numero);
		}
	}

	@Override
	public void transferir(int origem, int destino, float valor) {
		var contaOrigem = buscarNaCollection(origem);
		var contaDestino = buscarNaCollection(destino);
		
		if(contaOrigem != null && contaDestino != null) {
			if(contaOrigem.sacar(valor)){
				contaDestino.depositar(valor);
				System.out.printf("\nA transferência do valor %.2f da conta número %d para conta número %d foi efetuado com sucesso!%n", valor, origem, destino);
			}
		}else {
			System.out.println(Cores.TEXT_YELLOW_BOLD+"\n❓ A Conta de origem e/ou conta destino não foi encontrada!\n");
		}
		
		
	}
	
	@Override
	public void listarPorTitular(String titular) {
		List<Conta> listaTitulares = listaContas.stream()
				.filter(c -> c.getTitular().toUpperCase().contains(titular.toUpperCase()))
				.collect(Collectors.toList());
		
		if(listaTitulares.isEmpty()) {
			System.out.printf("\nNenhuma conta foi encontrada para titulares que possuam o nome %s\n", titular);
		}else {
			for(var conta: listaTitulares) {
				conta.visualizar();
			}
		}
	}
	
	public int gerarNumero() {
		return ++ numero;
	}
	
	public Conta buscarNaCollection(int numero) {
		for(var conta : listaContas) {
			if(conta.getNumero() == numero) {
				return conta;
			}
		}
		return null;
	}
}
