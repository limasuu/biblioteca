package bibli.modelo;

public class Funcionario extends Usuario{
		
	private static int totalFuncionariosJaCadastrados= 0;
	private String matricula;
	private double salario;
	private String cargo;
	
	public Funcionario(String nome, String endereco, 
			String telefone, String email, 
			double salario, String cargo) {
		
		super(nome, endereco, telefone, email);
		totalFuncionariosJaCadastrados++;
		this.matricula= "F" + String.valueOf(totalFuncionariosJaCadastrados);
		this.salario= salario;
		this.cargo= cargo;
	}
	
	public Funcionario(String matricula, String nome, String endereco, 
			String telefone, String email, 
			double salario, String cargo) {
		
		super(nome, endereco, telefone, email);
		this.matricula= matricula;
		this.salario= salario;
		this.cargo= cargo;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (obj instanceof Funcionario) {
			Funcionario outroFuncionario= (Funcionario) obj;
			
			if(!super.equals(outroFuncionario) ||
					outroFuncionario.getMatricula() == null || 
					outroFuncionario.getSalario() <= 0 ||
					outroFuncionario.getCargo() == null)
				return false;
			
			if(super.equals(outroFuncionario) &&
					outroFuncionario.getMatricula().equals(this.matricula) &&
					outroFuncionario.getSalario() == this.salario &&
					outroFuncionario.getCargo().equals(this.cargo))
				return true;
		}		
		return false;
	}
	
	@Override
	public String toString() {
		return "Matrícula: " + matricula + " | " + super.toString() +
				"\nCargo: " + cargo + " | Salário: " + salario;
	}
}
