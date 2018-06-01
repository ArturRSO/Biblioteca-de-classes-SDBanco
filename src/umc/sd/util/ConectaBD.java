package umc.sd.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaBD {
	
	public static Connection getConexao(){
		
		Connection conexao = null;
		
		try {
			
			String url = "jdbc:postgresql://localhost:5432/dbbanco";
			String usuario = "postgres";
			String senha = "4N4rqu14_1313";
			
			Class.forName("org.postgresql.Driver");
			
			conexao = DriverManager.getConnection(url, usuario, senha);
		} catch (Exception erro) {
			System.out.println("Falha na conexao com o banco de dados: " + erro.getMessage());
		}
		
		return conexao;
	}

}