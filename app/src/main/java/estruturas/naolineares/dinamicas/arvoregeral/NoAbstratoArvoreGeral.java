package estruturas.naolineares.dinamicas.arvoregeral;

import estruturas.lineares.dinamicas.lista.ListaSimplesmenteEncadeada;
import estruturas.naolineares.dinamicas.arvore.INoArvoreNaria;

public abstract class NoAbstratoArvoreGeral <T, N extends INoArvoreNaria<T, N>> implements INoArvoreNaria<T, N> {

    private T elemento;
    private final ListaSimplesmenteEncadeada<N> filhos;

    protected NoAbstratoArvoreGeral(T elemento) {
        this.elemento = elemento;
        this.filhos = new ListaSimplesmenteEncadeada<>();
    }

     @Override
    public T obterElemento() {
        return this.elemento;
    }

    @Override
    public void definirElemento(T elemento) {
        this.elemento = elemento;
    }

    @Override
    public Iterable<N> obterFilhos() {
        return this.filhos;
    }

    @Override
    public void adicionarFilho(N filho) {
        this.filhos.adicionarFim(filho);
    }

    @Override
    public int tamanho() {
        int tamanho = 1;
        for (N filho : this.filhos) {
            tamanho += filho.tamanho();
        }
        return tamanho;
    }

    @Override
    public int altura() {
        int alturaMaxima = -1;
        for (N filho : this.filhos) {
            alturaMaxima = Math.max(alturaMaxima, filho.altura());
        }
        return alturaMaxima + 1;
    }

    @Override
    public int grau() {
        return this.filhos.tamanho();
    }
        
}
