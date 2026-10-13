package estruturas.naolineares.dinamicas.arvore;

public interface INoArvoreNaria<T, N extends INoArvoreNaria<T, N>> extends INoArvore<T> {
    
    //Retorna os filhos do nó
    public Iterable<N> obterFilhos();

    //Adiciona um nó filho
    public void adicionarFilho(N filho);
}
