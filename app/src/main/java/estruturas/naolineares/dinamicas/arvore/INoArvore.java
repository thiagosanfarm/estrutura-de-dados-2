package estruturas.naolineares.dinamicas.arvore;

public interface INoArvore<T> {

    //Retorna o elemento armazenado no nó
    public T obterElemento();

    //Define o elemento a ser armazenado no nó 
    public void definirElemento(T elemento);

    //Retorna o tamanho da subárvore com raiz neste nó
    //O tamanho é definido como o número de nós na subárvore, incluindo este nó
    public int tamanho();

    //Retorna a altura da subárvore com raiz neste nó
    //A altura é definida como o número de arestas no caminho mais longo deste nó até um nó folha em sua subárvore 
    //A altura de um nó folha (sem filhos) é 0
    //A altura de um nó inexistente (nulo) é -1
    public int altura();

    //Retorna o grau deste nó (número de filhos diretos)
    //O grau é definido como o número de filhos diretos (subnós) que este nó possui atualmente
    public int grau();
}

