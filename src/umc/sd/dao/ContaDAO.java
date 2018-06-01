package umc.sd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import umc.sd.model.*;
import umc.sd.util.ConectaBD;

public class ContaDAO {

	public ContaPresenter recuperarPorId(int id, int senha) {

		Connection conexao = null;
		ContaPresenter conta = new ContaPresenter();

		try {

			conexao = ConectaBD.getConexao();

			String sql = "SELECT nome, saldo FROM conta WHERE idconta = ? AND senha = ?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, senha);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				conta.setNome(rs.getString("nome"));
				conta.setSaldo(rs.getDouble("saldo"));
			}

		} catch (Exception erro) {
			System.out.println("Falha ao recuperar conta: " + erro.getMessage());
			conta = null;
		} finally {
			try {
				conexao.close();
			} catch (Exception erro) {
				erro.printStackTrace();
				conta = null;
			}
		}

		return conta;
	}

	public int abrir(String nome, int senha) {

		Connection conexao = null;
		int resultado = 0;

		try {

			conexao = ConectaBD.getConexao();

			String sql = "INSERT INTO conta (idconta, nome, senha, saldo) VALUES (NEXTVAL('sq_conta'), ?, ?, ?)";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setInt(2, senha);
			ps.setDouble(3, 0.00);

			ps.executeUpdate();

			resultado = this.recuperarId(nome, senha);

		} catch (Exception erro) {
			System.out.println("Falha ao abrir conta: " + erro.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (Exception erro) {
				erro.printStackTrace();
			}
		}

		return resultado;
	}

	public boolean fechar(int id, int senha) {

		Connection conexao = null;
		boolean resultado = false;

		try {

			conexao = ConectaBD.getConexao();

			String sql = "DELETE FROM conta WHERE idconta = ? AND senha = ?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, senha);

			int r = ps.executeUpdate();

			if (r != 0) {
				resultado = true;
			}

		} catch (Exception erro) {
			System.out.println("Falha ao fechar conta: " + erro.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (Exception erro) {
				erro.printStackTrace();
			}
		}

		return resultado;
	}

	public int recuperarId(String nome, int senha) {

		int resultado = 0;
		Connection conexao = null;

		try {

			conexao = ConectaBD.getConexao();

			String sql = "SELECT idconta FROM conta WHERE nome = ? AND senha = ?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, nome);
			ps.setInt(2, senha);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				resultado = rs.getInt("idconta");
			}

		} catch (Exception erro) {
			System.out.println("Falha ao recuperar ID: " + erro.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (Exception erro) {
				erro.printStackTrace();
			}
		}

		return resultado;
	}

	public double recuperarSaldo(int id, int senha) {

		Connection conexao = null;
		double resultado = -1;

		try {

			conexao = ConectaBD.getConexao();

			String sql = "SELECT saldo FROM conta WHERE idconta = ? AND senha = ?";

			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, senha);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				resultado = rs.getDouble("saldo");
			}

		} catch (Exception erro) {
			System.out.println("Falha ao recuperar saldo: " + erro.getMessage());
		} finally {
			try {
				conexao.close();
			} catch (Exception erro) {
				erro.printStackTrace();
			}
		}

		return resultado;
	}

	public boolean depositar(int id, int senha, double valor) {

		Connection conexao = null;
		boolean resultado = false;

		double saldoAtual = this.recuperarSaldo(id, senha);

		if (saldoAtual >= 0) {

			try {

				conexao = ConectaBD.getConexao();

				double novoSaldo = saldoAtual + valor;

				String sql = "UPDATE conta SET saldo = ? WHERE idconta = ? AND senha = ?";

				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setDouble(1, novoSaldo);
				ps.setInt(2, id);
				ps.setInt(3, senha);

				int r = ps.executeUpdate();

				if (r != 0) {
					resultado = true;
				}
			} catch (Exception erro) {
				System.out.println("Falha ao recuperar saldo: " + erro.getMessage());
			} finally {
				try {
					conexao.close();
				} catch (Exception erro) {
					erro.printStackTrace();
				}
			}
		}

		return resultado;
	}

	public boolean sacar(int id, int senha, double valor) {
		
		Connection conexao = null;
		boolean resultado = false;
		
		double saldoAtual = this.recuperarSaldo(id, senha);
		
		if (valor <= saldoAtual && saldoAtual > 0) {	
		
			try {
			
				conexao = ConectaBD.getConexao();			
				
				double novoSaldo = saldoAtual - valor;
				
				String sql = "UPDATE conta SET saldo = ? WHERE idconta = ? AND senha = ?";
				
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setDouble(1, novoSaldo);
				ps.setInt(2, id);
				ps.setInt(3, senha);
				
				int r = ps.executeUpdate();
				
				if (r != 0) {
					resultado = true;
				}
				
			} catch (Exception erro) {
				System.out.println("Falha ao recuperar saldo: " + erro.getMessage());
			} finally {
				try {
					conexao.close();
				} catch (Exception erro) {
					erro.printStackTrace();
				}
			}
		}
		
		return resultado;
	}
}
