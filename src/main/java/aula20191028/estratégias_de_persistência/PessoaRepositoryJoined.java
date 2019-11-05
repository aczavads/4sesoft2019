package aula20191028.estratégias_de_persistência;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import aula20191028.estratégias_de_persistência.repository.PessoaRepository;

public class PessoaRepositoryJoined implements PessoaRepository {
	private Connection conn;

	public PessoaRepositoryJoined(Connection conn) {
		this.conn = conn;
		this.createTable();
	}

	private void createTable() {
		final String createTablePessoa = "create table if not exists pessoa_joined (" 
				+ " id varchar(36) not null primary key,"
				+ " nome varchar(255) not null," 
				+ " telefone_fixo varchar(25)," 
				+ " telefone_movel varchar(25)"
				+ ")";

		final String createTableFisica = "create table if not exists fisica_joined (" 
				+ " id varchar(36) not null primary key,"
				+ " rg varchar(25) not null,"
				+ " cpf varchar(25) not null unique,"
				+ " foreign key (id) references pessoa_joined(id)" 
				+ ")";
	
		final String createTableJuridica = "create table if not exists juridica_joined (" 
				+ " id varchar(36) not null primary key,"
				+ " razao_social varchar(255) not null unique," 
				+ " cnpj varchar(25) not null unique," 
				+ " inscricao_estadual varchar(25) not null,"
				+ " foreign key (id) references pessoa_joined(id)" 
				+ ")";
		try {
			conn.createStatement().executeUpdate(createTablePessoa);
			conn.createStatement().executeUpdate(createTableFisica);
			conn.createStatement().executeUpdate(createTableJuridica);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Pessoa p) {
		System.out.println(p.getId());
		try {
			final PreparedStatement psPessoa = conn.prepareStatement("insert into pessoa_joined (id, nome, telefone_fixo, telefone_movel) values (?,?,?,?)");
			psPessoa.clearParameters();
			psPessoa.setString(1, p.getId());
			psPessoa.setString(2, p.getNome());
			psPessoa.setString(3, p.getTelefoneFixo());
			psPessoa.setString(4, p.getTelefoneMovel());
			psPessoa.executeUpdate();
			
			conn.setAutoCommit(false);
			for (int i = 0; i < 1_000_000; i++) {
				psPessoa.clearParameters();
				psPessoa.setString(1, UUID.randomUUID().toString());
				psPessoa.setString(2, p.getNome() + " Jr " + i);
				psPessoa.setString(3, p.getTelefoneFixo());
				psPessoa.setString(4, p.getTelefoneMovel());
				psPessoa.executeUpdate();
			}
			psPessoa.close();
			conn.commit();
			
			conn.setAutoCommit(false);
			if (p instanceof Fisica) {
				final String insert = "insert into fisica_joined (id, rg, cpf) values (?,?,?)";
				final PreparedStatement ps = conn.prepareStatement(insert);
				
				Fisica auxFisica = (Fisica) p;				
				ps.setString(1, auxFisica.getId());
				ps.setString(2, auxFisica.getRg().getValor());
				ps.setString(3, auxFisica.getCpf().getValor());

				ps.executeUpdate();
				ps.close();
			} else {
				final String insert = "insert into juridica_joined (id, cnpj, inscricao_estadual, razao_social) values (?,?,?,?)";
				final PreparedStatement ps = conn.prepareStatement(insert);


				Juridica auxJuridica = (Juridica) p;
				ps.setString(1, auxJuridica.getId());
				ps.setString(2, auxJuridica.getCnpj().getValor());
				ps.setString(3, auxJuridica.getInscricaoEstadual().getValor());
				ps.setString(4, auxJuridica.getRazaoSocial());

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
			this.conn.createStatement().execute("delete from fisica_joined");
			this.conn.createStatement().execute("delete from juridica_joined");
			this.conn.createStatement().execute("delete from pessoa_joined");
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
		// TODO Auto-generated method stub
		return null;
	}

}
