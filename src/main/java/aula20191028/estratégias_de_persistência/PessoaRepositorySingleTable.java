package aula20191028.estratégias_de_persistência;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import aula20191028.estratégias_de_persistência.repository.PessoaRepository;
import aula20191028.estratégias_de_persistência.vo.CNPJ;
import aula20191028.estratégias_de_persistência.vo.CPF;
import aula20191028.estratégias_de_persistência.vo.InscricaoEstadual;
import aula20191028.estratégias_de_persistência.vo.RG;

public class PessoaRepositorySingleTable implements PessoaRepository {
	private Connection conn;

	public PessoaRepositorySingleTable(Connection conn) {
		this.conn = conn;
		this.createTable();
	}

	private void createTable() {
		final String createTable = "create table if not exists pessoa_single_table (" + " id varchar(36) not null primary key,"
				+ " discriminator varchar(36) not null," + " nome varchar(255) not null," + " rg varchar(25),"
				+ " cpf varchar(25)," + " telefone_fixo varchar(25)," + " telefone_movel varchar(25),"
				+ " razao_social varchar(255)," + " cnpj varchar(25)," + " inscricao_estadual varchar(25)" + ")";
		try {
			conn.createStatement().execute(createTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Pessoa p) {
		try {
			conn.setAutoCommit(false);
			if (p instanceof Fisica) {
				final String insert = "insert into pessoa_single_table (id, discriminator, nome, rg, cpf, telefone_fixo, telefone_movel) values (?,?,?,?,?,?,?)";
				final PreparedStatement ps = conn.prepareStatement(insert);

				ps.setString(1, p.getId());
				ps.setString(2, "FISICA");
				ps.setString(3, p.getNome());

				Fisica auxFisica = (Fisica) p;
				ps.setString(4, auxFisica.getRg().getValor());
				ps.setString(5, auxFisica.getCpf().getValor());
				ps.setString(6, auxFisica.getTelefoneFixo());
				ps.setString(7, auxFisica.getTelefoneMovel());

				ps.executeUpdate();
				ps.close();
			} else {
				final String insert = "insert into pessoa_single_table (id, discriminator, nome, cnpj, inscricao_estadual, razao_social, telefone_fixo, telefone_movel) values (?,?,?,?,?,?,?,?)";
				final PreparedStatement ps = conn.prepareStatement(insert);

				ps.setString(1, p.getId());
				ps.setString(2, "JURIDICA");
				ps.setString(3, p.getNome());

				Juridica auxJuridica = (Juridica) p;
				ps.setString(4, auxJuridica.getCnpj().getValor());
				ps.setString(5, auxJuridica.getInscricaoEstadual().getValor());
				ps.setString(6, auxJuridica.getRazaoSocial());
				ps.setString(7, auxJuridica.getTelefoneFixo());
				ps.setString(8, auxJuridica.getTelefoneMovel());

				ps.executeUpdate();
				ps.close();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
			} catch (SQLException ex2) {
				ex2.printStackTrace();
			}
		}
	}

	@Override
	public List<Pessoa> recuperarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void truncate() {
		try {
			this.conn.createStatement().execute("truncate table pessoa_single_table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluirPeloId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pessoa encontrarPeloId(String id) {
		try {
			final String query = "select * from pessoa_single_table where id = ?";
			final PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);

			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				final String discriminator = rs.getString("discriminator");
				if (discriminator.equalsIgnoreCase("FISICA")) {
					Fisica recuperada = new Fisica(rs.getString("id"), 
							rs.getString("nome"),
							rs.getString("telefone_movel"), 
							rs.getString("telefone_fixo"), 
							new CPF(rs.getString("cpf")),
							new RG(rs.getString("rg")));
					return recuperada;
				} else if (discriminator.equalsIgnoreCase("JURIDICA")) {
					Juridica recuperada = new Juridica(rs.getString("id"), 
							rs.getString("nome"), 
							rs.getString("telefone_movel"),
							rs.getString("telefone_fixo"), 
							new CNPJ(rs.getString("cnpj")), 
							new InscricaoEstadual(rs.getString("inscricao_estadual")),
							rs.getString("razao_social"));
					return recuperada;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
