package br.edu.faculdade.agenda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.edu.faculdade.agenda.dao.ClienteDAO;
import br.edu.faculdade.agenda.model.Cliente;

public class ListaClientesActivity extends AppCompatActivity {

    //Atributos
    private ListView listaClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        listaClientes = (ListView) findViewById(R.id.listaClientes);

        listaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente cli = (Cliente) listaClientes.getItemAtPosition(position);
                Intent abrirFormManut = new Intent(ListaClientesActivity.this, FormularioCliente.class);
                abrirFormManut.putExtra("Cliente", cli);
                startActivity(abrirFormManut);
            }
        });

        //String [] clientes = {"Antonio Carlos","Marcos Antonio","Oscar Rodrigues",
        //  "Pablo Selton","Antonio Carlos","Marcos Antonio","Oscar Rodrigues","Pablo Selton"};

        Button btnNovo = (Button) findViewById(R.id.listacliente_btnnovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaFormulario = new Intent(ListaClientesActivity.this, FormularioCliente.class);
                startActivity(irParaFormulario);
            }
        });
        registerForContextMenu(listaClientes);
    }

    private void carregarListaClientes() {
        ClienteDAO dao = new ClienteDAO(this);
        List<Cliente> clientes = dao.buscarClientes();
        dao.close();

        ArrayAdapter<Cliente> adapter =
                new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        listaClientes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarListaClientes();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Cliente cli = (Cliente) listaClientes.getItemAtPosition(info.position);

                //Instanciar um objeto ClienteDAO
                ClienteDAO dao = new ClienteDAO(ListaClientesActivity.this);
                dao.excluir(cli);
                dao.close();
                carregarListaClientes();

                Toast.makeText(ListaClientesActivity.this, "Excluiu o cliente" + cli.getNome(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
