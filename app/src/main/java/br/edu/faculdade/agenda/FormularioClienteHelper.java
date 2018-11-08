package br.edu.faculdade.agenda;

import android.widget.EditText;
import android.widget.RatingBar;
import br.edu.faculdade.agenda.model.Cliente;

public class FormularioClienteHelper {

    //Atributos
    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoBairro;
    private EditText campoCidade;
    private EditText campoEstado;
    private EditText campoFone;
    private EditText campoEmail;
    private RatingBar campoNota;
    private Cliente cliente;

    //Construtor
    public FormularioClienteHelper(FormularioCliente activity) {
         campoNome = (EditText) activity.findViewById(R.id.txtnome);
         campoEndereco = (EditText) activity.findViewById(R.id.txtendereco);
         campoBairro = (EditText) activity.findViewById(R.id.txtbairro);
         campoCidade = (EditText) activity.findViewById(R.id.txtcidade);
         campoEstado = (EditText) activity.findViewById(R.id.txtestado);
         campoFone = (EditText) activity.findViewById(R.id.txtfone);
         campoEmail = (EditText) activity.findViewById(R.id.txtemail);
         campoNota = (RatingBar) activity.findViewById(R.id.ratingnota);
    }

    public Cliente pegarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(campoNome.getText().toString());
        cliente.setEndereco(campoEndereco.getText().toString());
        cliente.setBairro(campoBairro.getText().toString());
        cliente.setCidade(campoCidade.getText().toString());
        cliente.setEstado(campoEstado.getText().toString());
        cliente.setFone(campoFone.getText().toString());
        cliente.setEmail(campoEmail.getText().toString());
        cliente.setNota(Double.valueOf(campoNota.getProgress()));
        return cliente;
    }

    public void preencherFormulario(Cliente cli) {
        this.cliente = cli;
        campoNome.setText(cli.getNome());
        campoEndereco.setText(cli.getEndereco());
        campoBairro.setText(cli.getBairro());
        campoCidade.setText(cli.getCidade());
        campoEstado.setText(cli.getEstado());
        campoFone.setText(cli.getFone());
        campoEmail.setText(cli.getEmail());
        campoNota.setProgress(cli.getNota().intValue());

    }
    }
