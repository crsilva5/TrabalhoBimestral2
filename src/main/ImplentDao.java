package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enums.EstadoCivil;


public class ImplentDao {

	private SqlGenImpl impl;
	
	private SqlGenImpl getImp() {
		if (impl == null) {
			try {
				setImp(new SqlGenImpl());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return impl;
	}
	
	private void setImp(SqlGenImpl impl) {
		this.impl = impl;
	}
	public void salvar(Object t) {
		PreparedStatement insert = getImp().getSqlInsert(getImp().getCon(), t);

		System.out.println("INCLUINDO REGISTRO");

		Cliente cliente = (Cliente) t;
		try {
			insert.setInt(1, cliente.getId());
			insert.setString(2, cliente.getNome());
			insert.setString(3, cliente.getEndereco());
			insert.setString(4, cliente.getTelefone());
			insert.setInt(5, cliente.getEstadoCivil().ordinal());
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		try {
			insert.executeUpdate();
			System.out.println("Registro Incluso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		public Object buscar(Object k) {
			
			PreparedStatement buscar = impl.getSqlSelectById(impl.getCon(), k);

			ResultSet exibir;
			System.out.println("BUSCANDO REGISTRO");
			try {
				exibir = buscar.executeQuery();
				while (exibir.next()) {
					System.out.print("\nID: " + exibir.getInt("CL_ID"));
					System.out.print("\nNome: " + exibir.getString("CL_NOME"));
					System.out.print("\nEndereco: " + exibir.getString("CL_ENDERECO"));
					System.out.print("\nTelefone: " + exibir.getString("CL_TELEFONE"));
					System.out.print("\nEstadoCivil: " + EstadoCivil.values()[exibir.getInt("CL_ESTADOCIVIL")]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("Fim de Registro!");
			return null;
		}
			
	}

