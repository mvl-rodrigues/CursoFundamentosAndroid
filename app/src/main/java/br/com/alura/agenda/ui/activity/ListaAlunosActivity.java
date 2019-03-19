package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.ListaAlunosView;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

/**
 * AppCompatActivity: herda de Activity (tela do android);
 * da suporte (em relação ao comportamento) as versões mais antigas do android;
 * da acesso ao app bar
 */
public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de aluno";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    /**
     * onCreate: para poder iniciar o app é preciso sobrescrever esse método.
     * dentro dele também será codificado o comportamento da activity
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//      obrigatorio chamar o oncreate da classe pai para executar corretamente o ciclo de vida da activity
        super.onCreate(savedInstanceState);
//      método que renderiza o conteudo definido no arquivo xml activity_main
//      classe R: mapeia os recursos disponiveis no diretorio "res" onde temos nossa pasta "layout" com o nosso arquivo visual "activity_main"
        setContentView(R.layout.activity_lista_alunos);
//      permite alterar o titulo no appbar, graças a AppCompatActivity
        setTitle(TITULO_APPBAR);

        configuraFabNovoAluno();

        configuraLista();

//      Toda activity herda de um context. this: indica de onde ta vindo a ação.
//      Toast.makeText(this, "Victor Rodrigues", Toast.LENGTH_SHORT).show();
    }

    /**
     * onCreateContextMenu: permite a implementação de um menu de contexto
     * é acionado a partir de um clique longo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //adicionando uma opção do menu
        //menu.add("Remover");

        //"Infla" o menu de contexto
        //parametros: endereço do menu (R.menu.activity_lista_alunos_menu) e o menu de contexto (menu)
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);

    }

    /**
     * onContextItemSelected: é executado semṕre de um menu de contexto for clicado.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //pegando o id do botão de menu clicado
        int itemId = item.getItemId();

        if (itemId == R.id.activity_lista_alunos_menu_remover){
            listaAlunosView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton butaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        butaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        listaAlunosView.atualizaAlunos();

    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);

//      ArrayAdapter: implementa um adapter simples para a visualização da lista (simple_list_item_1: arquivo padrão do android).
        listaAlunosView.configuraAdapter(listaAlunos);

        configuraListenerDeCliquePorItem(listaAlunos);

        //configuraListenerDeCliqueLongoPorItem(listaAlunos);

        //insere uma view dentro da outra. neste caso um menu dentro do listview
        registerForContextMenu(listaAlunos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//              pegando da lista o aluno que foi clickado
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
//              redirecionando para o formulário
                abreFormularioModoEditaAluno(alunoEscolhido);

                /*
                 * imprime mensagem de info(i) no logcat
                 * Log.i("Possição clicada: ", "" + position + " id: " + id);
                 * temos tambem: Log.e (error), Log.w (warn) e etc.
                 */
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioActivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
//              incluindo as informações do aluno na Intent. recebe uma chave e um valor
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
//              inicia a Activity da minha Intent
        startActivity(vaiParaFormularioActivity);
    }

}

//    private void configuraListenerDeCliqueLongoPorItem(ListView listaAlunos) {
//        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
//
//                remove(alunoEscolhido);
//                //retorno true para não deixar passar a execução para o listener de click normal
//                return false;
//            }
//        });
//    }