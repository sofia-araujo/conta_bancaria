package conta_bancaria;

import java.util.InputMismatchException;
import java.util.Scanner;

import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;
import conta_bancaria.controller.ContaController;

public class Menu {
	private static final Scanner leia = new Scanner(System.in);
	private static final ContaController contaController = new ContaController();
	
	public static void main(String[] args) {
		int opcao;
		criarContasTeste();
		
		while(true) {
			
			System.out.println(Cores.ANSI_BLACK_BACKGROUND+Cores.TEXT_GREEN_BOLD +
					"\n*******************************************************");
			System.out.println("	        Bem vindo(a) ao ByteBank 💲             ");
			System.out.println("*******************************************************");
			System.out.println("          [1] - Criar Conta                            ");
			System.out.println("          [2] - Listar todas as Contas                 ");
			System.out.println("          [3] - Consultar uma Conta pelo número        ");
			System.out.println("          [4] - Editar Conta                           ");
			System.out.println("          [5] - Excluir Conta                          ");
			System.out.println("          [6] - Sacar                                  ");
			System.out.println("          [7] - Depositar                              ");
			System.out.println("          [8] - Transferir                             ");
			System.out.println("          [9] - Procurar por titular da conta          ");
			System.out.println("          [0] - Sair                                   ");
			System.out.println("                                                       ");
			System.out.println("*******************************************************");
			System.out.println("                                                       ");
			System.out.println("Entre com a opção desejada:                            ");
			System.out.println("                                                       "+
			Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
				leia.nextLine();
			}catch(InputMismatchException e) {
				opcao = -1;
				System.out.println("\nDigite um número inteiro entre 0 e 8: ");
				leia.nextLine();
			}
			
			if(opcao == 0) {
				System.out.println(Cores.ANSI_BLACK_BACKGROUND+Cores.TEXT_GREEN_BOLD+
						"ByteBank sua melhor escolha!");
				sobre();
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
				case 1:
					System.out.println("Criar conta\n\n");
					cadastrarConta();
					keyPress();
					break;
				case 2:
					System.out.println("Listar contas\n\n");
					listarContas();
					keyPress();
					break;
				case 3:
					System.out.println("Consultar conta\n\n");
					procurarContaPorNumero();
					keyPress();
					break;
				case 4:
					System.out.println("Editar conta\n\n");
					atualizarConta();
					keyPress();
					break;
				case 5:
					System.out.println("Excluir conta\n\n");
					deletarConta();
					keyPress();
					break;
				case 6:
					System.out.println("Sacar\n\n");
					sacar();
					keyPress();
					break;
				case 7:
					System.out.println("Depositar\n\n");
					depositar();
					keyPress();
					break;
				case 8:
					System.out.println("Transferir\n\n");
					transferir();
					keyPress();
					break;
				case 9:
					System.out.println("Procurar por titular da conta\n\n");
					procurarPorTitular();
					keyPress();
					break;
				default:
					System.out.println("\nOpção inválida!\n");
					keyPress();
					break;
			}
		}
	}
	public static void sobre() {
		System.out.println(Cores.ANSI_BLACK_BACKGROUND+Cores.TEXT_GREEN_BOLD+
				"\n*******************************************************");
		System.out.println("Projeto Desenvolvido por:                              ");
		System.out.println("Sofia de Araújo                                        ");
		System.out.println("github.com/sofia-araujo                                ");
		System.out.println("*******************************************************");
	}
	
	public static void keyPress() {
		System.out.println(Cores.TEXT_RESET+"\n\nPressione Enter para continuar...");
		leia.nextLine();
	}
	
	private static void criarContasTeste() {
		contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), 456, 1, "Igor Marcelino", 1000000.00f, 100000.00f));
		contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), 456, 2, "Sofia Araújo", 1000000.00f, 26));
	}
	
	private static void listarContas() {
		contaController.listarTodas();
	}
	
	private static void cadastrarConta() {
		System.out.print("Digite o número da agência: ");
		int agencia = leia.nextInt();
		
		System.out.print("Digite o nome do titular: ");
		leia.skip("\\R");
		String titular = leia.nextLine();
		
		System.out.print("Digite o tipo da conta (1 - CC | 2 - CP): ");
		int tipo = leia.nextInt();
		
		System.out.print("Digite o saldo inicial: ");
		float saldo = leia.nextFloat();
		
		switch(tipo) {
			case 1 -> {
				System.out.print("Digite o limite inicial: ");
				float limite = leia.nextFloat();
				leia.nextLine();
				contaController.cadastrar(new ContaCorrente(contaController.gerarNumero(), agencia, tipo, titular, saldo, limite));
			}
			case 2 -> {
				System.out.print("Digite o dia do aniversário da conta: ");
				int aniversario = leia.nextInt();
				leia.nextLine();
				contaController.cadastrar(new ContaPoupanca(contaController.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
			}
			default -> System.out.print(Cores.TEXT_RED+"\n❌ Esse tipo de conta não existe!"+Cores.TEXT_RESET);
		}
	}
	
	private static void procurarContaPorNumero() {
		System.out.println("Digite o número da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		contaController.procurarPorNumero(numero);
	
	}
	
	private static void deletarConta() {
		System.out.println("Digite o número da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		Conta conta = contaController.buscarNaCollection(numero);
		
		if(conta != null) {
			System.out.println("\nTem certeza que deseja excluir esta conta? (S/N): ");
			String confirmacao = leia.nextLine();
			
			if(confirmacao.equalsIgnoreCase("S")) {
				contaController.deletar(numero);
			}else {
				System.out.println("\nOperação cancelada!");
			}
			
		}else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
	}
	
	private static void atualizarConta() {
		System.out.println("Digite o número da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		Conta conta = contaController.buscarNaCollection(numero);
		
		if(conta != null) {
			int agencia = conta.getAgencia();
			String titular = conta.getTitular();
			int tipo = conta.getTipo();
			float saldo = conta.getSaldo();
			
			System.out.printf("A agência atual é %d%nNova agência (Pressione ENTER para manter valor atual): ", agencia);
			String entrada = leia.nextLine();
			agencia = entrada.isEmpty() ? agencia : Integer.parseInt(entrada);
			
			System.out.printf("O nome do titular atual é %s%nNovo titular (Pressione ENTER para manter valor atual): ", titular);
			entrada = leia.nextLine();
			titular = entrada.isEmpty() ? titular : entrada;
			
			System.out.printf("O saldo atual é %.2f%nNovo saldo (Pressione ENTER para manter valor atual): ", saldo);
			entrada = leia.nextLine();
			saldo = entrada.isEmpty() ? saldo : Float.parseFloat(entrada);
			
			switch(tipo) {
			case 1 -> {
				// Casting
				float limite = ((ContaCorrente) conta).getLimite();
				
				System.out.printf("O seu limite atual é %.2f%nNovo limite (Pressione ENTER para manter valor atual): ", limite);
				entrada = leia.nextLine();
				limite = entrada.isEmpty() ? limite : Float.parseFloat(entrada);

				contaController.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
			}
			case 2 -> {
				int aniversario = ((ContaPoupanca) conta).getAniversario();
				
				System.out.printf("O atual dia do aniversário da conta é %d%n Novo aniversário (Pressione ENTER para manter valor atual): ", aniversario);
				entrada = leia.nextLine();
				aniversario = entrada.isEmpty() ? aniversario : Integer.parseInt(entrada);
				
				contaController.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
			}
			default -> System.out.print(Cores.TEXT_RED+"\n❌ Esse tipo de conta não existe!"+Cores.TEXT_RESET);
		}
			
			
		}else {
			System.out.printf("\nA conta número %d não foi encontrada!", numero);
		}
	}
	
	private static void sacar() {
		System.out.println("Digite o número da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		System.out.println("Digite o valor do saque: ");
		float valor = leia.nextFloat();
		leia.nextLine();
		
		contaController.sacar(numero, valor);
	}
	
	private static void depositar() {
		System.out.println("Digite o número da conta: ");
		int numero = leia.nextInt();
		leia.nextLine();
		
		System.out.println("Digite o valor do deposito: ");
		float valor = leia.nextFloat();
		leia.nextLine();
		
		contaController.depositar(numero, valor);
	}
	
	private static void transferir() {
		System.out.println("Digite o número da conta de origem: ");
		int origem = leia.nextInt();
		leia.nextLine();
		
		System.out.println("Digite o número da conta de destino: ");
		int destino = leia.nextInt();
		leia.nextLine();
		
		System.out.println("Digite o valor da transferência: ");
		float valor = leia.nextFloat();
		leia.nextLine();
		
		contaController.transferir(origem, destino, valor);
	}
	
	private static void procurarPorTitular() {
		System.out.println("Digite o titular da conta: ");
		String titular = leia.nextLine();
		
		contaController.listarPorTitular(titular);
	}
}
