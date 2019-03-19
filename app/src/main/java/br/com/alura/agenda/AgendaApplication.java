package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        criaAlunosDeTeste();

    }

    private void criaAlunosDeTeste() {
        AlunoDao dao = new AlunoDao();
        dao.salvar(new Aluno("victor","11232323233","victor"));
        dao.salvar(new Aluno("fran","11232323233","victor"));
        dao.salvar(new Aluno("joao","11232323233","victor"));
    }
}
