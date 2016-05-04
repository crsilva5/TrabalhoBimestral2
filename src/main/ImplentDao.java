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
		public void atualizar(Object t) {
				PreparedStatement alterar = impl.getSqlUpdateById(impl.getCon(), t);

				Cliente cliente = (Cliente) t;

				int exibir = 0;
				
				System.out.println("Alterando Registro");
				try {
					alterar.setString(1, cliente.getNome());
					alterar.setString(2, cliente.getEndereco());
					alterar.setString(3, cliente.getTelefone());
					alterar.setInt(4, cliente.getEstadoCivil().ordinal());
					alterar.setInt(5, cliente.getId());
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					exibir = alterar.executeUpdate();
					System.out.print("\nNovo nome: " + cliente.getNome());
					System.out.print("\nNovo endere�o: " + cliente.getEndereco());
					System.out.print("\nNovo telefone : " + cliente.getTelefone());
					System.out.print("\nNovo estado civil : " + cliente.getEstadoCivil());
					System.out.print("\n " + exibir + " Registro(s) alterados!");
				} catch (SQLException e) {
					e.printStackTrace();
			}
		}
				public void excluir(Object t) {
					PreparedStatement excluir = impl.getSqlDeleteById(impl.getCon(), t);

					Cliente cliente = (Cliente) t;

					int exibir = 0;

					System.out.println("EXCLUINDO REGISTRO");

					try {
						excluir.setInt(1, cliente.getId());
					} catch (SQLException e) {
						e.printStackTrace();
					}

					try {
						exibir = excluir.executeUpdate();
						System.out.println(exibir + " Registro(s) excluido(s)!");
						System.out.println("  Novo nome..........: " + cliente.getNome());
						System.out.println("  Novo endere�o......: " + cliente.getEndereco());
						System.out.println("  Novo telefone......: " + cliente.getTelefone());
						System.out.println("  Novo Estado civil..: " + cliente.getEstadoCivil());
						System.out.println("  Excluidos para o ID: " + cliente.getId());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Registro Excluido");
		}
	}
	

