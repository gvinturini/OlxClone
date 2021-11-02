package giovanni.aindroid.olx.com.model;

import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;
import java.util.List;

import giovanni.aindroid.olx.com.helper.ConfiguracaoFirebase;

public class Anuncio implements Serializable {

    private String id;
    private String estado;
    private String categoria;
    private String titulo;
    private String valor;
    private String telefone;
    private String descricao;
    private List<String> fotos;

    public Anuncio() {
        DatabaseReference anuncioref = ConfiguracaoFirebase.getFirebase().child("meus_anuncios");
        setId(anuncioref.push().getKey());
    }

    public void salvar(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase().child("meus_anuncios");
        anuncioRef.child(idUsuario).child(getId()).setValue(this);

        salvarAnuncioPublico();

    }

    public void salvarAnuncioPublico(){

        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase().child("anuncios");
        anuncioRef.child(getEstado()).child((getCategoria())).child(getId()).setValue(this);

    }

    public void remover(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios")
                .child(idUsuario)
                .child(getId());

        anuncioRef.removeValue();
        removerAnuncioPublico();

    }

    public void removerAnuncioPublico(){

        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("anuncios")
                .child(getEstado())
                .child(getCategoria())
                .child(getId());

        anuncioRef.removeValue();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

}
