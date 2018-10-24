package aula30;

import java.util.Scanner;

public class AppProduto {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		CadastroProduto cad = new CadastroProduto();
		cad.mostrarMenu(teclado);
		teclado.close();
	}
}
