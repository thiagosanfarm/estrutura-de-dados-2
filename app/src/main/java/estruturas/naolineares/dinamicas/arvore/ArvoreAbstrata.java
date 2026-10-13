package estruturas.naolineares.dinamicas.arvore;

import java.util.Objects;

public abstract class ArvoreAbstrata<T, N extends INoArvore<T>> implements IArvore<T, N> {

    private N noRaiz;

    @Override
    public N obterNoRaiz() {
        return this.noRaiz;
    }

    @Override
    public void definirNoRaiz(N noRaiz) {
        this.noRaiz = noRaiz;
    }

    protected abstract Iterable<N> filhosDe(N no);

    @Override
    public int tamanho() {
        return this.estaVazia() ? 0 : this.obterNoRaiz().tamanho();
    }
    
    @Override
    public int altura() {
        return this.estaVazia() ? -1 : this.obterNoRaiz().altura();
    }

    @Override
    public int grau() {
        if (this.estaVazia()) return -1;
        return grau(this.noRaiz);
    }

    private int grau(N no) {
        if (no == null) return -1;
        int grau = no.grau();
        for (N filho : this.filhosDe(no)) {
            grau = Math.max(grau, this.grau(filho));
        }
        return grau;
    }

    @Override
    public int arestas() {
        return this.estaVazia() ? -1 : this.tamanho() - 1;
    }

    @Override
    public int nivelNo(T elemento) {
        return nivelNo(this.noRaiz, elemento, 0);
    }

    private int nivelNo(N no, T elemento, int nivelAtual) {
        if (no == null) return -1;
        if (Objects.equals(no.obterElemento(), elemento)) {
            return nivelAtual;
        }
        for (N filho : this.filhosDe(no)) {
            int nivel = this.nivelNo(filho, elemento, nivelAtual + 1);
            if (nivel != -1) return nivel;
        }
        return -1;
    }

    @Override
    public Iterable<N> recuperarIrmaosDe(T elemento) {
        throw new UnsupportedOperationException("Método não Implementado 'recuperarIrmaosDe'"); 
    }

}