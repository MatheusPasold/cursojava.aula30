package aula30;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CadastroProduto {
	
	ControllerProduto controller = new ControllerProduto();
	public void mostrarMenu(Scanner teclado) { 
		int opcao = 0;
		
		do{
			System.out.println("******************MENU Principal************************");
			System.out.println("1 - Cadastrar Produto");
			System.out.println("2 - Listar Produtos");
			System.out.println("3 - Listar Produtos por Faixa de Preço");
			System.out.println("4 - Listar Produtos por Nome");
			System.out.println("5 - Editar Produto");
			System.out.println("6 - Remover Produto");
			System.out.println("7 - Sair");
			opcao = Integer.parseInt(teclado.nextLine());
			tratarOpcao(opcao,teclado);
		}while(opcao != 7);
		
	}

	private void tratarOpcao(int opcao, Scanner teclado) {
		switch (opcao) {
		case 1:
			cadastrarProduto(teclado);
			break;
		case 2:
			listarProdutos();
			break;
		case 3:
			listarProdutosPorFaixaPreco(teclado);
			break;
		case 4:
			listarProdutosPorNome(teclado);
			break;
		case 5:
			editarProduto(teclado);
			break;
		case 6:
			removerProduto(teclado);
		case 7:
			System.out.println("Obrigado por usar nosso sistema");
			break;
		default:
			System.out.println("Opção Inválida");
			break;
		}
		
	}

	private void cadastrarProduto(Scanner teclado) {
		System.out.println("(((((((((((((((((CADASTRAR PRODUTO)))))))))))))))))");
		String nome;
		do {
			System.out.println("Digite o nome do produto:");
			nome = teclado.nextLine();
		}while(!controller.isNomeValido(nome));
		System.out.println("Digite o preço do produto: ");
		double preco = 0;
		preco = Double.parseDouble(teclado.nextLine());
		
		boolean resultado = controller.adicionarProduto(nome, preco);
		if(resultado) {
			System.out.println("Produto adicionado com sucesso!");
		}else {
			System.out.println("Erro ao adicionar produto");
		}
	}

	private void listarProdutos() {
		System.out.println("_______________________________");
		System.out.println("       LISTA DE CONTATOS       ");
		System.out.println("-------------------------------");
		List<Produto> lista = controller.buscarTodos();
		for(Produto produto : lista) {
			System.out.printf("id: %d \n nome: %s \n preço: %d \n", produto.getId(), produto.getNome(), produto.getPreco());
		}
	}

	private void listarProdutosPorFaixaPreco(Scanner teclado) {
		System.out.println("<<<<<<<<<<<<<<< FILTRAR POR FAIXA E NOME>>>>>>>>>>>>>>>");
		System.out.println("Valor Mínimo: ");
		double minimo = Double.parseDouble(teclado.nextLine());
		System.out.println("Valor Máximo: ");
		double maximo = Double.parseDouble(teclado.nextLine());
		List<Produto> lista = controller.listarProdutoFaixaPreco(minimo, maximo);
		for (Produto produto : lista) {
			System.out.printf("%d - %s - %s - %s \n",produto.getId(),produto.getNome(),produto.getPreco());
		}
	}

	private void listarProdutosPorNome(Scanner teclado) {
		System.out.println("======== FILTRAR POR NOME =============");
		System.out.println("Nome:");
		String nome = teclado.nextLine();
		List<Produto> lista = controller.listarProdutoPorNome(nome);
		for (Produto produto : lista) {
			System.out.printf("%d - %s - %s - %s \n",produto.getId(),produto.getNome(),produto.getPreco());
		}
		
	}

	private void editarProduto(Scanner teclado) {
		Produto produto = new Produto();
		System.out.println("########### ATUALIZAR PRODUTO ##############");
		controller.buscarTodos();
		System.out.println("Digite o id do produto que deseja editar: ");
		int id = Integer.parseInt(teclado.nextLine());
		System.out.println("Digite o novo nome do produto: ");
		String nome = teclado.nextLine();
		System.out.println("Digite o novo preco do produto: ");
		double preco = Double.parseDouble(teclado.nextLine());
		controller.atualizar(produto);
	}

	private void removerProduto(Scanner teclado) {
		System.out.println("ooooooooooooo REMOVER PRODUTO ooooooooooooo");
		System.out.println("digite o id do produto que deseja remover");
		int id = Integer.parseInt(teclado.nextLine());
		controller.remover(id);
		
	}
}
