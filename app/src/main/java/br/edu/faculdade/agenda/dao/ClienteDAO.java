package br.edu.faculdade.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdade.agenda.model.Cliente;

public class ClienteDAO extends SQLiteOpenHelper {


    public ClienteDAO(Context context) {
        super(context, "Clientes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Clientes (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT, " +
                "bairro TEXT, " +
                "cidade TEXT, " +
                "estado TEXT, " +
                "fone TEXT, " +
                "email TEXT, " +
                "nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Clientes";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(cliente);
        db.insert("Clientes", null, dados);
    }

    public void excluir(Cliente cli) {
        SQLiteDatabase db  =  getWritableDatabase();

        String [] params  =  {cli.getId().toString()};

        db.delete("Clientes", " id=?",params);
    }

    public List<Cliente> buscarClientes() {
        String sql = "SELECT * FROM Clientes;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);
        List<Cliente> clientes = new ArrayList<Cliente>();
        while (c.moveToNext()) {
            Cliente cliente = new Cliente();
            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cliente.setBairro(c.getString(c.getColumnIndex("bairro")));
            cliente.setCidade(c.getString(c.getColumnIndex("cidade")));
            cliente.setEstado(c.getString(c.getColumnIndex("estado")));
            cliente.setFone(c.getString(c.getColumnIndex("fone")));
            cliente.setEmail(c.getString(c.getColumnIndex("email")));
            cliente.setNota(c.getDouble(c.getColumnIndex("nota")));
            clientes.add(cliente);
        }
        c.close();
        return clientes;
    }

    public void alterar(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValues(cliente);
        String[] params = {dados.get("id").toString()};
        db.update("Clientes", dados, "id = ?", params);
    }

    @NonNull
    private ContentValues getContentValues(Cliente cliente) {
        ContentValues dados = new ContentValues();
        dados.put("id", cliente.getId());
        dados.put("nome", cliente.getNome());
        dados.put("endereco", cliente.getEndereco());
        dados.put("bairro", cliente.getBairro());
        dados.put("cidade", cliente.getCidade());
        dados.put("estado", cliente.getEstado());
        dados.put("fone", cliente.getFone());
        dados.put("email", cliente.getEmail());
        dados.put("nota", cliente.getNota());
        return dados;
    }

}
