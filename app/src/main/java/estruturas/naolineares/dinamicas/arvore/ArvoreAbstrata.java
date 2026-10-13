package estruturas.naolineares.dinamicas.arvore;

import java.util.Objects;
import estruturas.lineares.dinamicas.lista.ListaSimplesmenteEncadeada;

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
        ListaSimplesmenteEncadeada<N> irmaos = new ListaSimplesmenteEncadeada<>();
        if (this.estaVazia() || elemento == null) {
            return irmaos;
        }
        if (Objects.equals(this.obterNoRaiz().obterElemento(), elemento)) {
            return irmaos;
        }

        N pai = this.localizarPai(this.obterNoRaiz(), elemento);
        if (pai == null) {
            return irmaos;
        }

        Iterable<N> filhosDoPai = this.filhosDe(pai);
        if (filhosDoPai != null) {
            boolean elementoIgnorado = false;
            for (N filho : filhosDoPai) {
                if (filho != null) {
                    if (!elementoIgnorado && Objects.equals(filho.obterElemento(), elemento)) {
                        elementoIgnorado = true;
                    } else {
                        irmaos.adicionarFim(filho);
                    }
                }
            }
        }
        return irmaos;
    }

    private N localizarPai(N noAtual, T elemento) {
        if (noAtual == null) {
            return null;
        }
        Iterable<N> filhos = this.filhosDe(noAtual);
        if (filhos != null) {
            for (N filho : filhos) {
                if (filho != null && Objects.equals(filho.obterElemento(), elemento)) {
                    return noAtual;
                }
            }
            for (N filho : filhos) {
                if (filho != null) {
                    N pai = this.localizarPai(filho, elemento);
                    if (pai != null) {
                        return pai;
                    }
                }
            }
        }
        return null;
    }

}