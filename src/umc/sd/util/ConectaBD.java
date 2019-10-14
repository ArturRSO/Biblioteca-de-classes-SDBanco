package umc.sd.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBD {
	
	public static Connection getConexao(){
		
		Connection conexao = null;
		
		try {
			
			String url = "jdbc:postgresql://localhost:5432/db_test";
			String usuario = "test";
			String senha = "Test_666";
			
			Class.forName("org.postgresql.Driver");
			
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (Exception erro) {
			System.out.println("Falha na conexao com o banco de dados: " + erro.getMessage());
		}
		
		return conexao;
	}

}
