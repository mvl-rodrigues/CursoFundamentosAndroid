package br.com.alura.agenda.ui;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDao dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDao();
    }

    public void confirmaRemocao(final MenuItem item) {
        /* AlertDialog: cria uma tela de mensagem sobreposta a activity
         * .Builder(this): permite e configuração de varios elementos no momento da construção da instancia
         * .show(): sem esse método o Dialog criado não é mostrado
         * .setPositiveButton("Sim", null):
         * - argumento 1: texto do botão; e
         * - argumento 2: listener que executara algum comportamento.*/
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //getMenuInfo: nos da acesso a mais informação sobre o menu clicado, exemplo a posição do aluno na lista
                        //AdapterContextMenuInfo: é a implementação especifica para adapters receberem as info do getMenuInfo
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                        //adapter.getItem: retorna um aluno pela posição
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        //remove o aluno escolhido da lista
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }


    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Aluno alunoEscolhido) {
        dao.remover(alunoEscolhido);
        // utilizando o metodo remove do adapter para atualizar a lista apos remover no DAO
        adapter.remove(alunoEscolhido);
    }

    public void configuraAdapter(ListView listaAlunos) {
        /* trasforma o parametro do setAdapter em uma variável para poder utilizar melhor
         * as funcionalidades do adapter, como: adapter.remove() para atualizar a lista.*/

        // uso do layout padrão do android: android.R.layout.simple_list_item_1
        // uso do layout personalizado: R.layout.item_aluno


        listaAlunos.setAdapter(adapter);

    }

}
