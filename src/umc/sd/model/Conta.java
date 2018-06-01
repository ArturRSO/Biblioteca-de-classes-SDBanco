package umc.sd.model;

public class Conta {

	private int id;
	private String nome;
	private int senha;
	private double saldo;

	public Conta() {}

	public Conta(int id, String nome, int senha, double saldo) {
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
}