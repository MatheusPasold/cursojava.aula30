package aula30;

import java.util.List;

public class ControllerProduto {
	
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	
	public List<Produto> buscarTodos() {
		return produtoDAO.buscarTodos();
	}
	
	public boolean isNomeValido(String nome) {
		boolean valido = nome !=null && !"".equals(nome.trim()) && nome.length() >=3;
		return valido;
	}

	public boolean adicionarProduto(String nome, double preco) {
		Produto produto = new Produto(null, nome, preco);
		if(nome != null && preco > 0.01) {
			produtoDAO.inserir(produto);			
			return true;
		}else {
			return false;
		}
	}

	public List<Produto> listarProdutoPorNome(String nome) {
		return produtoDAO.buscarPorNome(nome);
	}

	public List<Produto> listarProdutoFaixaPreco(double minimo, double maximo) {
		return produtoDAO.buscarPorPreco(minimo, maximo);
	}

	public boolean remover(int id) {
		boolean resultado = false;
		produtoDAO.remover(id);
		return resultado;
	}

	public void atualizar(Produto produto) {
		produtoDAO.atualizar(produto);
	}
	
	
}
