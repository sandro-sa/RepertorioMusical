package br.com.movingtechbrasil.repertoriomusical;

public class Cantor {

    private String nomeCantor;

    private Origem origem;

    private GeneroMusical generoMusical;

    private int conjucaoMusical;

    public Cantor(String nomeCantor, Origem origem, GeneroMusical generoMusical, int conjucaoMusical) {
        this.nomeCantor = nomeCantor;
        this.origem = origem;
        this.generoMusical = generoMusical;
        this.conjucaoMusical = conjucaoMusical;
    }
    public String getNomeCantor() {
        return nomeCantor;
    }
    public void setNomeCantor(String nomeCantor) {
        this.nomeCantor = nomeCantor;
    }
    public Origem getOrigem() {
        return origem;
    }
    public void setOrigem(Origem origem) {
        this.origem = origem;
    }
    public GeneroMusical getGeneroMusical() {
        return generoMusical;
    }
    public void setGeneroMusical(GeneroMusical generoMusical) {
        this.generoMusical = generoMusical;
    }
    public int getConjucaoMusical() {
        return conjucaoMusical;
    }
    public void setConjucaoMusical(int conjucaoMusical) {
        this.conjucaoMusical = conjucaoMusical;
    }

}
