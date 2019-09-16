package aula20190916.aep;

public class Conceito {

	private int id;
	private Nota nota;
	private Aluno aluno;
	private Disciplina disciplina;

	public Conceito(int id, Nota nota, Aluno aluno, Disciplina disciplina) {
		if (nota == null) {
			throw new NotaRequeridaException();
		}
		this.id = id;
		this.nota = nota;
		this.aluno = aluno;
		this.disciplina = disciplina;
	}

	public Nota getNota() {
		return nota;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public int getId() {
		return id;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}

}
