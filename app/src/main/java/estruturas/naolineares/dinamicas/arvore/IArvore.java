package estruturas.naolineares.dinamicas.arvore;

public interface IArvore<T, N extends INoArvore<T>> {

    //Retorna o nó raiz da árvore
    public N obterNoRaiz();
    
    //Define o nó raiz da árvore
    public void definirNoRaiz(N noRaiz);

    //Verifica se a árvore está vazia
    default boolean estaVazia() {
        return this.obterNoRaiz() == null;
    }

    //Retorna o número/quantidade de nós da árvore
    public int tamanho();

    //Retorna a altura da árvore
    //Retorna -1 para uma árvore vazia, 0 para uma árvore com um único nó
    public int altura();

    //Retorna o grau da árvore - grau máximo verificado para seus nós
    //Igual ao grau do nó que apresenta mais filhos
    //Retorna -1 para uma árvore vazia
    public int grau();

    //Retorna o número de arestas da árvore
    //Retorna -1 para uma árvore vazia, 0 para uma árvore com um único nó
    public int arestas();

    //Retorna o nível do nó que contém o elemento passado por parametro
    //A raiz está no nível 0, seus filhos no nível 1, e assim por diante
    //Retorna -1 se o elemento não for encontrado
    //@param elemento a ser pesquisado ​​na árvore
    public int nivelNo(T elemento);

    //Recupera os nós irmãos de um determinado elemento passado por parâmetro
    //Se o elemento for a raiz, não existir, ou não tiver irmãos, retorna uma coleção vazia
    //@param elemento usado para recuperar seus irmãos
    public Iterable<N> recuperarIrmaosDe(T elemento);

}
