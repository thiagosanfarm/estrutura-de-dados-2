package algoritmos.busca;

import java.util.Arrays;

public class AppBusca {


    public static void main(String[] args) {

        int valor = 5; //Valor a ser buscado nos arrays

        //Busca Sequencial

        int[] dados = {7, 3, 9, 1, 4, 5, 8, 2};
        System.out.println("Array: " + Arrays.toString(dados));

        int posicao = BuscaSequencial.buscaSequencial(dados, valor);
        if (posicao >= 0) {
            System.out.printf("Valor %d Encontrado na posição %d: \n", valor, posicao);
        } else {
            System.out.println("Não encontrado");
        }

        //Busca Binária Iterativa

        Arrays.sort(dados); //Ordenação do array
        System.out.println("Array: " + Arrays.toString(dados));

        posicao = BuscaBinaria.buscaBinariaIterativa(dados, valor);
        if (posicao >= 0) {
            System.out.printf("Valor %d Encontrado na posição %d: \n", valor, posicao);
        } else {
            System.out.println("Não encontrado");
        }

        posicao = BuscaBinaria.buscaBinariaRecursiva(dados, valor);
        if (posicao >= 0) {
            System.out.printf("Valor %d Encontrado na posição %d: \n", valor, posicao);
        } else {
            System.out.println("Não encontrado");
        }

    }

}
