package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    private Context context;

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

    /**
     * getView: é a view apresentada
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        /**
         * from: de onde a view será inflada (CONTEXTO).
         * inflate:
         * - R.layout.item_aluno: o arquivo que será inflado; e
         * - viewGroup: é a view group (lista) onde o item_aluno vai entrar
         * - false: em um adapterView é preciso delegar a função de inflar ao adapter e não ao root,
         *  por padrão o inflate é responsabilidade da view pai (root) e para funcionar em um adapter
         *  precisamos passar o parametro false para delegar essa função ao nosso adapter.
         **/
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, viewGroup,false);

        // para cada aluno da lista estou retornando um aluno para pegar as informações
        Aluno alunoDevolvido = alunos.get(position);

        // linkar apartir da viewCriada os componentes dessa view (view filhas)
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        // a partir do componente linkado, settar as informações do alunoDevolvido
        nome.setText(alunoDevolvido.getNome());
        TextView telefone = viewCriada.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());

        // retorna a view personalizada do nosso adapter
        return viewCriada;
    }

    public void clear() {
        alunos.clear();
    }

    public void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
    }
}
