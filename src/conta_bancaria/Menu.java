package conta_bancaria;

import java.util.Scanner;
import conta_bancaria.util.Cores;

public class Menu {

	public static void main(String[] args) {
		Scanner leia = new Scanner(System.in);
		int opcao;
		
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
		System.out.println("          [0] - Sair                                   ");
		System.out.println("                                                       ");
		System.out.println("*******************************************************");
		System.out.println("                                                       ");
		System.out.println("Entre com a opção desejada:                            ");
		System.out.println("                                                       "+
		Cores.TEXT_RESET);
		
		opcao = leia.nextInt();
		
		if(opcao == 0) {
			System.out.println(Cores.ANSI_BLACK_BACKGROUND+Cores.TEXT_GREEN_BOLD+
					"ByteBank sua melhor escolha!");
			sobre();
			leia.close();
			System.exit(0);
		}
		
		switch(opcao) {
			case 1:
				System.out.println("Criar conta/n");
				break;
			case 2:
				System.out.println("Listar contas/n");
				break;
			case 3:
				System.out.println("Consultar conta/n");
				break;
			case 4:
				System.out.println("Editar conta/n");
				break;
			case 5:
				System.out.println("Excluir conta/n");
				break;
			case 6:
				System.out.println("Sacar/n");
				break;
			case 7:
				System.out.println("Depositar/n");
				break;
			case 8:
				System.out.println("Transferir/n");
				break;
			default:
				System.out.println("\nOpção inválida!");
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

}
