package main;


import enums.EstadoCivil;

public class Principal extends ImplentDao {

	public Principal(){
		Cliente c1 = new Cliente(1, "Cleiton", "Rua: Carijos","99181317", EstadoCivil.Solteiro);
		apagarTabela(c1);
		criarTabela(c1);
		salvar(c1);
		
		Cliente c2 = new Cliente(1, "jose", "Rua: tapajos","99181315", EstadoCivil.Solteiro);
		

		salvar(c2);
		
		Cliente c3 = new Cliente(1, "Maria", "Rua: xavante","99181311", EstadoCivil.Solteiro);
		

		salvar(c3);
		
		listarTodos(new Cliente());	

		buscar(c1);

		c3.setNome("NOME 3");
		c3.setEndereco("ENDERECO 3");
		c3.setTelefone("TELEFONE 3");
		c3.setEstadoCivil(EstadoCivil.Viuvo);

		atualizar(c3);

		excluir(c2);

		listarTodos(new Cliente());	
}
public static void main(String[] args) {
	new Principal();
	}
}