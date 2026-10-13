package estruturas.naolineares.dinamicas.arvoregeral;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AppArvoreGeral {

    public static void main(String[] args) {
       
        NoArvoreGeral<String> NoA = new NoArvoreGeral<>("A");
        NoArvoreGeral<String> NoB = new NoArvoreGeral<>("B");
        NoArvoreGeral<String> NoC = new NoArvoreGeral<>("C");
        NoArvoreGeral<String> NoD = new NoArvoreGeral<>("D");
        NoArvoreGeral<String> NoE = new NoArvoreGeral<>("E");
        NoArvoreGeral<String> NoF = new NoArvoreGeral<>("F");
        NoArvoreGeral<String> NoG = new NoArvoreGeral<>("G");

        ArvoreGeral<String> arvoregeral = new ArvoreGeral<>(NoA);

        NoA.adicionarFilho(NoB);
        NoA.adicionarFilho(NoC);
        NoA.adicionarFilho(NoD);

        NoB.adicionarFilho(NoE);
        NoB.adicionarFilho(NoG);
        
        NoD.adicionarFilho(NoF);

        System.out.println("Tipo de Estrutura dos Filhos do No Raiz: " + arvoregeral.obterNoRaiz().obterFilhos());
        System.out.println("Filhos do Nó Raiz: " + StreamSupport.stream(NoA.obterFilhos().spliterator(), false).map(filho -> String.valueOf(filho.obterElemento())).collect(Collectors.joining(", ")));
        System.out.println("Grau máximo da árvore: " + arvoregeral.grau()); 
        System.out.println("Tamanho da árvore: " + arvoregeral.tamanho()); 
        System.out.println("Altura da árvore: " + arvoregeral.altura()); 
        System.out.println("Arestas da árvore: " + arvoregeral.arestas()); 
        System.out.println("Nível do Nó A: " + arvoregeral.nivelNo(NoA.obterElemento()));


    }

}
