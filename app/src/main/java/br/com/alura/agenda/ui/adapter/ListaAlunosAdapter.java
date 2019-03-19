package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

/**
 * BaseAdapter: permite a minha criação do adapter. E já fornece os principais
 * métodos para serem sobrescritos, são eles:
 * getCount() -> representa a quantidade de elementos do adapter;
 * getItem() -> Retorna o elemento pela posição;
 * getItemId() -> retornar o id do elemento pela posição;
 * getView() -> cria a view para cada elemento.
 **/
public class ListaAlunosAdapter extends BaseAdapter {
    // List<Aluno> alunos: é o dataset do meu adapter. permite realizar as operações base, como: getCount()
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    // para cada instancia precisamos que seja passado o contexto de criação
    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    /*
     * getView: é a view apresentada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        /* from: de onde a view será inflada (CONTEXTO).
         * inflate:
         * - R.layout.item_aluno: o arquivo que será inflado; e
         * - viewGroup: é a view group (lista) onde o item_aluno vai entrar
         * - false: em um adapterView é preciso delegar a função de inflar ao adapter e não ao root,
         *  por padrão o inflate é responsabilidade da view pai (root) e para funcionar em um adapter
         *  precisamos passar o parametro false para delegar essa função ao nosso adapter.
         **/
        View viewCriada = criaView(viewGroup);

        // para cada aluno da lista estou retornando um aluno para pegar as informações
        Aluno alunoDevolvido = alunos.get(position);

        vincula(viewCriada, alunoDevolvido);

        // retorna a view personalizada do nosso adapter
        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        // linkar apartir da viewCriada os componentes dessa view (view filhas)
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        // a partir do componente linkado, settar as informações do alunoDevolvido
        nome.setText(aluno.getNome());
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(aluno.getTelefone());
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup,false);
    }

//    private void clear() {
//        alunos.clear();
//    }
//
//    private void addAll(List<Aluno> alunos) {
//        this.alunos.addAll(alunos);
//    }

    public void atualiza (List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        /* notifyDataSetChanged(): notifica o adapter que houve uma alteração no nosso dataset (lista).
         * recomendado usar esse método sempre que for realizado alguma alteração no dataset, como:
         * add, remove, delete e etc.
         **/
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        // notifyDataSetChanged(): notifica o adapter que houve uma alteração no nosso dataset (lista)
        notifyDataSetChanged();
    }
}
