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

public class PessoaRepositoryTablePerClass implements PessoaRepository {
	private Connection conn;

	public PessoaRepositoryTablePerClass(Connection conn) {
		this.conn = conn;
		this.createTable();
	}

	private void createTable() {
		final String createTableFisica = "create table if not exists fisica (" 
				+ " id varchar(36) not null primary key,"
				+ " nome varchar(255) not null," 
				+ " rg varchar(25) not null,"
				+ " cpf varchar(25) not null unique," 
				+ " telefone_fixo varchar(25)," 
				+ " telefone_movel varchar(25)"
				+ ")";
		
		final String createTableJuridica = "create table if not exists juridica (" 
				+ " id varchar(36) not null primary key,"
				+ " nome varchar(255) not null,"  
				+ " telefone_fixo varchar(25)," 
				+ " telefone_movel varchar(25),"
				+ " razao_social varchar(255) not null unique," 
				+ " cnpj varchar(25) not null unique," 
				+ " inscricao_estadual varchar(25) not null" 
				+ ")";
		try {
			conn.createStatement().executeUpdate(createTableFisica);
			conn.createStatement().executeUpdate(createTableJuridica);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void salvar(Pessoa p) {
		try {
			conn.setAutoCommit(false);
			if (p instanceof Fisica) {
				final String insert = "insert into fisica (id, nome, rg, cpf, telefone_fixo, telefone_movel) values (?,?,?,?,?,?)";
				final PreparedStatement ps = conn.prepareStatement(insert);

				ps.setString(1, p.getId());
				ps.setString(2, p.getNome());

				Fisica auxFisica = (Fisica) p;
				ps.setString(3, auxFisica.getRg().getValor());
				ps.setString(4, auxFisica.getCpf().getValor());
				ps.setString(5, auxFisica.getTelefoneFixo());
				ps.setString(6, auxFisica.getTelefoneMovel());

				ps.executeUpdate();
				ps.close();
			} else {
				final String insert = "insert into juridica (id, nome, cnpj, inscricao_estadual, razao_social, telefone_fixo, telefone_movel) values (?,?,?,?,?,?,?)";
				final PreparedStatement ps = conn.prepareStatement(insert);

				ps.setString(1, p.getId());
				ps.setString(2, p.getNome());

				Juridica auxJuridica = (Juridica) p;
				ps.setString(3, auxJuridica.getCnpj().getValor());
				ps.setString(4, auxJuridica.getInscricaoEstadual().getValor());
				ps.setString(5, auxJuridica.getRazaoSocial());
				ps.setString(6, auxJuridica.getTelefoneFixo());
				ps.setString(7, auxJuridica.getTelefoneMovel());

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
			this.conn.createStatement().execute("truncate table fisica; truncate table juridica;");
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
			final String query = "select discriminator from ("
					+ "select 'fisica' as discriminator from fisica where fisica.id = ? "
					+ "union all "
					+ "select 'juridica' as discriminator from juridica where juridica.id = ?"
					+ ") unidas";
			final PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, id);

			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				final String discriminator = rs.getString("discriminator");
				if (discriminator.equalsIgnoreCase("FISICA")) {
					PreparedStatement psFisica = conn.prepareStatement("select * from fisica where id = ?");
					psFisica.setString(1, id);
					ResultSet rsFisica = psFisica.executeQuery();
					if (rsFisica.next()) {
						Fisica recuperada = new Fisica(rsFisica.getString("id"), 
								rsFisica.getString("nome"),
								rsFisica.getString("telefone_movel"), 
								rsFisica.getString("telefone_fixo"), 
								new CPF(rsFisica.getString("cpf")),
								new RG(rsFisica.getString("rg")));
						return recuperada;
					}
					return null;
				} else if (discriminator.equalsIgnoreCase("JURIDICA")) {
					PreparedStatement psJuridica = conn.prepareStatement("select * from juridica where id = ?");
					psJuridica.setString(1, id);
					ResultSet rsJuridica = psJuridica.executeQuery();
					if (rsJuridica.next()) {
						Juridica recuperada = new Juridica(rsJuridica.getString("id"), 
								rsJuridica.getString("nome"), 
								rsJuridica.getString("telefone_movel"),
								rsJuridica.getString("telefone_fixo"), 
								new CNPJ(rsJuridica.getString("cnpj")), 
								new InscricaoEstadual(rsJuridica.getString("inscricao_estadual")),
								rsJuridica.getString("razao_social"));
						return recuperada;
					}
					return null;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
