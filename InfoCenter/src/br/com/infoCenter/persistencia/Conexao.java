package br.com.infoCenter.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static Connection con;

	public static Connection getConexao() {
		if (con == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/infocenter" , "root", "root");
				System.out.println("Conectou");
				
			} catch (Exception e) {
				try{
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/infocenter" , "postgres", "postgres");
				} catch (Exception ex) {
					e.printStackTrace();
					System.out.println("Nao conectou");
				}
			}
		}

		return con;
	}

	public static void main(String[] args) {
		getConexao();
	}
}