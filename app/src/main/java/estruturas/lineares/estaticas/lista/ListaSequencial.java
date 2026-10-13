package estruturas.lineares.estaticas.lista;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import estruturas.lineares.IListaLinear;

public class ListaSequencial<T> implements IListaLinear<T>, Iterable<T> {

    /////////////
    //Atributos//
    /////////////

    private int tamanho;    //Quantidade de elementos na lista
    private T[] elementos;  //Lista de elementos

    //////////////
    //Construtor//
    //////////////

    @SuppressWarnings("unchecked")
    public ListaSequencial(int capacidade) {
        if (capacidade < 0) {
            throw new IllegalArgumentException("A capacidade do vetor não pode ser negativa");
        }
        this.tamanho = 0;
        this.elementos = (T[]) new Object[capacidade];
    }

    ////////////////////////
    //Operações Auxiliares//
    ////////////////////////

    //Verifica se a posicao/indice passado como parâmetro está fora dos limites do vetor (IndexOutOfBounds)
    private void verificarPosicao(int posicao, int minimo, int maximo) {
        if (posicao < minimo || posicao > maximo) {
            throw new IndexOutOfBoundsException(
                String.format("Índice: %d, Tamanho: %d", posicao, this.tamanho)
            );
        }
    }

    //Verifica a capacidade do vetor
    private void verificarCapacidade() {
        if (this.tamanho == this.elementos.length) {
            throw new IllegalStateException("Capacidade máxima atingida.");
        }
    }

    ////////////////////////////////////////////
    //Operações de Consulta (Query Operations)//
    ////////////////////////////////////////////

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    @Override
    public int posicao(T elemento) {
        for (int indice = 0; indice < this.tamanho; indice++) {
            if (Objects.equals(elemento, this.elementos[indice])) {
                return indice;
            }
        }
        return -1;
    }

    @Override
    public boolean contem(T elemento) {
       return this.posicao(elemento) != -1;
    }

    @Override
    public String imprimir() {
        return Arrays.toString(Arrays.copyOf(this.elementos, this.tamanho));
    }

    @Override
    public T obter(int posicao) {
       this.verificarPosicao(posicao, 0, this.tamanho - 1);
       return (T) this.elementos[posicao];
    }

    ////////////////////////////////////////////////////////////
    //Operações Básicas de Mutação (Basic Mutation Operations)//
    ////////////////////////////////////////////////////////////

    @Override
    public void inserir(T elemento, int posicao) {
        this.verificarPosicao(posicao, 0, this.tamanho);
        this.verificarCapacidade();

        //Desloca elementos para a direita
        //Pode ser substituido por System.ArrayCopy
        for (int i = this.tamanho; i > posicao; i--) {
            this.elementos[i] = this.elementos[i - 1];
        }

        this.elementos[posicao] = elemento;
        this.tamanho++;
    }

    @Override
    public void atualizar(int posicao, T elemento) {
        this.verificarPosicao(posicao, 0, this.tamanho - 1);
        this.elementos[posicao] = elemento;
    }

    @Override
    public T remover(int posicao) {
        this.verificarPosicao(posicao, 0, this.tamanho - 1);
        T elementoRemovido = (T) this.elementos[posicao];
        
        //Desloca elementos para a esquerda
        //Pode ser substituido por System.ArrayCopy
        for (int i = posicao; i < this.tamanho - 1; i++) {
            this.elementos[i] = this.elementos[i + 1];
        }

        this.elementos[this.tamanho - 1] = null;
        this.tamanho--;
        return elementoRemovido;
    }

    @Override
    public void limpar() {
        Arrays.fill(this.elementos, 0, this.tamanho, null);
        this.tamanho = 0;
    }

    ////////////////////////////////////////////////////////////////
    //Operações Derivadas de Mutação (Derived Mutation Operations)//
    ////////////////////////////////////////////////////////////////
    
    public void adicionarInicio(T elemento) {
        this.inserir(elemento, 0);
    }

    public void adicionarFim(T elemento) {
        this.inserir(elemento, this.tamanho());
    }
    
    public void remover(T elemento) {
        if (this.contem(elemento)) {
            int posicao = this.posicao(elemento);
            this.remover(posicao);
        }
    }

    public void removerInicio() {
        this.remover(0);
    }

    public void removerFim() {
        this.remover(this.tamanho - 1);
    }

    ////////////////////////////////////////////////
    //Operações de Iteração (Iteration Operations)//
    ////////////////////////////////////////////////

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int posicao = 0;

            @Override
            public boolean hasNext() {
                return posicao < tamanho; 
            }

            @Override
            public T next() throws NoSuchElementException  {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elementos[posicao++]; 
            }
        };
    }

}