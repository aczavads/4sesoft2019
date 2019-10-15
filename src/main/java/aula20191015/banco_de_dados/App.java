package aula20191015.banco_de_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class App {
	
	
	public static void main(String[] args) throws Exception {
		
		Connection conexão = DriverManager.getConnection("jdbc:postgresql://localhost:5432/4sesoft2019", "postgres", "unicesumar");
		
		Statement selectDataAtual = conexão.createStatement();
		ResultSet resultadoDataAtual =  selectDataAtual.executeQuery("select now() as dataAtual");
		if (resultadoDataAtual.next()) {
			Timestamp dataAtual = resultadoDataAtual.getTimestamp("dataAtual");
			System.out.println("A data atual do servidor de banco é: " + dataAtual.toLocaleString());
		}
		
		
		conexão.close();		
		System.out.println("Ulha, foi!");
		
	}
	
	
	

}
