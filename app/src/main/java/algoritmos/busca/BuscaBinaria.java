package algoritmos.busca;

public class BuscaBinaria {
    
    // Versão Iterativa 

    public static int buscaBinariaIterativa(int[] vetor, int valor) {
        if (vetor == null || vetor.length == 0) { 
            return -1; 
        }
        int limiteInferior = 0; //Primeiro índice do vetor
        int limiteSuperior = vetor.length - 1;
        while (limiteInferior <= limiteSuperior) {
            int meio = ((limiteInferior + limiteSuperior) / 2);
            if (valor == vetor[meio]) {
                 return meio;
            }
            if (valor < vetor[meio]) { 
                limiteSuperior = meio - 1;
            }
            else {
                 limiteInferior  = meio + 1; 
            }
        }
        return -1;
    }

    // Versão Recursiva
    
    public static int buscaBinariaRecursiva(int[] vetor, int valor) {
       if (vetor == null || vetor.length == 0) { 
            return -1; 
        }
        return buscaBinariaRecursiva(vetor, valor, 0, vetor.length - 1);
    }

    private static int buscaBinariaRecursiva(int[] vetor, int valor, int limiteInferior, int limiteSuperior) {
         if (limiteInferior > limiteSuperior) {
            return -1;
        }
        int meio = ((limiteInferior + limiteSuperior) / 2);
        if (vetor[meio] == valor) { 
            return meio; 
        }
        if (valor < vetor[meio]) {
            return buscaBinariaRecursiva(vetor, valor, limiteInferior, meio - 1);
        }
        else { 
            return buscaBinariaRecursiva(vetor, valor, meio + 1, limiteSuperior);
        }
    }

}
