package br.com.alura.agenda.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDao {

    private static final List<Aluno> alunos = new ArrayList<>();
    /**
     * simula o auto incremento do bando de dados para id.
     * static: permite propagar o incremento do contador para todas as instancias desse classe
     */
    private static int contadorDeIds = 1;


    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
//      incremento do contador para ids
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita (Aluno aluno){
        Aluno alunoEncontrado = buscaAlunoPeloId(aluno);

//      settar as auterações na lista
        if (alunoEncontrado != null){
            int posiçãoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posiçãoDoAluno, aluno);
        }

    }

    private Aluno buscaAlunoPeloId(Aluno aluno) {
        Aluno alunoEncontrado = null;
//      procura o aluno na lista
        for (Aluno a : alunos){
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remover(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);

        if (alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}
