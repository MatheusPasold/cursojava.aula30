package aula30;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements RepositorioProduto {

	private Produto carregarProduto(ResultSet cursor) throws SQLException {
		Integer id = cursor.getInt("id");
		String nome = cursor.getString("nome");
		double preco = cursor.getDouble("preco");
		return new Produto(id, nome, preco);
	}
	
	@Override
	public List<Produto> buscarTodos() {
		List<Produto> produtos = new ArrayList<>();
		try {
			Connection connection = Main.getConnection();
			PreparedStatement comando = connection.prepareStatement("select * from produtos");
			ResultSet cursor = comando.executeQuery();
			while (cursor.next()) {
				produtos.add(carregarProduto(cursor));
			}
			comando.close();
			cursor.close();
		} catch (SQLException e) {
			System.out.println("Não possível buscar no banco de dados!");
		}
		return produtos;
	}


	@Override
	public List<Produto> buscarPorNome(String nome) {
		List<Produto> produtos = new ArrayList<>();
		if(nome != null) {
		try {
			Connection connection = Main.getConnection();
			PreparedStatement comando = connection.prepareStatement("select * from produtos where upper(nome) like upper(?)");
			comando.setString(1,"%"+ nome+ "%");
			ResultSet cursor = comando.executeQuery();
			while(cursor.next()) {
				produtos.add(carregarProduto(cursor));
			}
			comando.close();
			cursor.close();
		} catch (SQLException e) {
			System.out.println("Não possível buscar o nome no banco de dados!");
		}
		}
		return produtos;
	}

	@Override
	public List<Produto> buscarPorPreco(double minimo, double maximo) {
		List<Produto> produtos = new ArrayList<>();
		try {
			Connection connection = Main.getConnection();
			PreparedStatement comando = connection.prepareStatement("select * from produtos where preco < ? AND preco > ?");
			comando.setDouble(1, minimo);
			comando.setDouble(2, maximo);
			ResultSet cursor = comando.executeQuery();
			while(cursor.next()) {
				produtos.add(carregarProduto(cursor));
			}
			comando.close();
			cursor.close();
		} catch (SQLException e) {
			System.out.println("Não possível buscar os preços no banco de dados!");
		}
		return produtos;
	}

	@Override
	public Produto buscarPorId(Integer id) {
		Produto produtos = null;
		try {
			Connection connection = Main.getConnection();
			PreparedStatement comando = connection.prepareStatement("select * from produtos where id = ?");
			comando.setInt(1, id);
			ResultSet cursor = comando.executeQuery();
			if(cursor.next()) {
				produtos = carregarProduto(cursor);
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível buscar por id no banco de dados");
		}
		return produtos;
	}

	@Override
	public void inserir(Produto produto) {
		try {
			Connection connection = Main.getConnection();
			PreparedStatement comando = connection.prepareStatement("insert into produtos(nome, preco) values (?,?)", Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, produto.getNome());
			comando.setDouble(2, produto.getPreco());
			comando.execute();
			ResultSet cursor = comando.getGeneratedKeys();
			if(cursor.next()) {
				Integer id = cursor.getInt("id");
				produto.setId(id);
			}
			comando.close();
			cursor.close();
		} catch (SQLException e) {
			System.out.println("Não foi possível inserir o Produto");
		}
	}

	@Override
	public void atualizar(Produto produto) {
		if(produto != null && produto.getId() != null) {
			try {
				Connection connection = Main.getConnection();
				PreparedStatement comando = connection.prepareStatement("update produtos set nome = ? where preco = ?");
				comando.setString(1, produto.getNome());
				comando.setDouble(2, produto.getPreco());
				comando.execute();
				comando.close();
			} catch (SQLException e) {
				System.out.println("Não foi possível atualizar os produtos");
			}
		}
	}

	@Override
	public void remover(Integer id) {
		if(id != null) {
			try {
				Connection conexao = Main.getConnection();
				PreparedStatement comando = conexao.prepareStatement("delete from produtos where id = ?");
				comando.setInt(1, id);
				comando.execute();
				comando.close();
			} catch (SQLException e) {
				System.out.println("Não foi possível remover os dados da Pessoa");
			}
		}

	}

	@Override
	public int contar() {
		int quantidade = 0;
		try {
			Connection connection = Main.getConnection();
			PreparedStatement comando = connection.prepareStatement("select count(*) as quantidade from produtos");
			ResultSet cursor = comando.executeQuery();
			if(cursor.next()) {
				quantidade = cursor.getInt("quantidade");
			}
			cursor.close();
			comando.close();
		}catch (SQLException e) {
			System.out.println("Não foi possível contar a quantidade de contatos");
		}
		
		return quantidade;
	}
	

}
