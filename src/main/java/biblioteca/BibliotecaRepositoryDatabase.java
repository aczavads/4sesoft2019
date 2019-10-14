package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaRepositoryDatabase implements BibliotecaRepository {
	private Connection conn;

	public BibliotecaRepositoryDatabase() {
		//System.out.println("Inicializando um repository DATABASE!");
		try {
			// java database connectivity
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/4sesoft2019", "postgres",
					"unicesumar");
			conn.createStatement().executeUpdate(
					"create table if not exists biblioteca (" + "nome varchar(255) not null primary key" + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void truncate() {
		try {
			this.conn.createStatement().executeUpdate("truncate table biblioteca");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Biblioteca biblioteca) {
		try {
			// final String insert = "insert into biblioteca (nome) values (" +
			// biblioteca.getNome() + ")";
			final String insert = "insert into biblioteca (nome) values (?)";
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setString(1, biblioteca.getNome());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Biblioteca> encontrarTodas() {
		final List<Biblioteca> todas = new ArrayList<>();
		try {
			ResultSet rs = conn.createStatement().executeQuery("select nome from biblioteca");
			while (rs.next()) {
				final String nome = rs.getString("nome");
				final Biblioteca recuperada = new Biblioteca(nome);
				todas.add(recuperada);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todas;
	}

	@Override
	public void excluirPeloNome(String nome) {
		try {
			final String delete = "delete from biblioteca where nome = ?";
			PreparedStatement ps = conn.prepareStatement(delete);
			ps.setString(1, nome);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}






