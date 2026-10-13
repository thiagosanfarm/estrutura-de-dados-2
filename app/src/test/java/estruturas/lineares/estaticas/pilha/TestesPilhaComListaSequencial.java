package estruturas.lineares.estaticas.pilha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import estruturas.lineares.IPilha;

public class TestesPilhaComListaSequencial {

     @Nested
    class NovaPilha {
        IPilha<Integer> pilha;

        @BeforeEach
        void setup() {
            pilha = new PilhaComListaSequencial<>();
        }

        @Test
        void tamanhoEhZero() {
            assertEquals(0, pilha.tamanho());
        }

        @Test
        void estaVaziaEhTrue() {
            assertTrue(pilha.estaVazia());
        }

        @Test
        void obterTopoLancaExcecao() {
            assertThrows(IllegalStateException.class, () -> pilha.obterTopo());
        }

        @Test
        void desempilharLancaExcecao() {
            assertThrows(IllegalStateException.class, () -> pilha.desempilhar());
        }

        @Test
        void contemRetornaFalse() {
            assertFalse(pilha.contem(10));
        }

        @Test
        void distanciaRetornaMenosUm() {
            assertEquals(-1, pilha.distancia(10));
        }

        @Test
        void imprimirRetornaRepresentacaoVazia() {
            assertEquals("[]", pilha.imprimir());
        }

        @Test
        void iteratorNaoTemProximo() {
            Iterator<Integer> it = pilha.iterator();
            assertFalse(it.hasNext());
        }

        @Test
        void limparPermaneceVazia() {
            pilha.limpar();
            assertEquals(0, pilha.tamanho());
            assertTrue(pilha.estaVazia());
        }

        @Test
        void construtorComCapacidadeCriaPilhaVazia() {
            PilhaComListaSequencial<Integer> pilhaCapacidade = new PilhaComListaSequencial<>(5);
            assertEquals(0, pilhaCapacidade.tamanho());
            assertTrue(pilhaCapacidade.estaVazia());
        }
    }

    @Nested
    class PilhaComUmElemento {
        PilhaComListaSequencial<Integer> pilha;
        final int ELEMENTO = 42;

        @BeforeEach
        void setup() {
            pilha = new PilhaComListaSequencial<>();
            pilha.empilhar(ELEMENTO);
        }

        @Test
        void tamanhoEhUm() {
            assertEquals(1, pilha.tamanho());
        }

        @Test
        void estaVaziaEhFalse() {
            assertFalse(pilha.estaVazia());
        }

        @Test
        void obterTopoRetornaElemento() {
            assertEquals(ELEMENTO, pilha.obterTopo());
        }

        @Test
        void desempilharRetornaElemento() {
            assertEquals(ELEMENTO, pilha.desempilhar());
            assertTrue(pilha.estaVazia());
        }

        @Test
        void contemElementoExistente() {
            assertTrue(pilha.contem(ELEMENTO));
        }

        @Test
        void contemElementoInexistente() {
            assertFalse(pilha.contem(99));
        }

        @Test
        void distanciaElementoTopo() {
            assertEquals(0, pilha.distancia(ELEMENTO));
        }

        @Test
        void distanciaElementoInexistente() {
            assertEquals(-1, pilha.distancia(99));
        }

        @Test
        void imprimirRetornaElemento() {
            assertEquals("[42]", pilha.imprimir());
        }

        @Test
        void iteratorPercorreElemento() {
            Iterator<Integer> it = pilha.iterator();
            assertTrue(it.hasNext());
            assertEquals(ELEMENTO, it.next());
            assertFalse(it.hasNext());
        }

        @Test
        void limparEsvaziaPilha() {
            pilha.limpar();
            assertTrue(pilha.estaVazia());
            assertEquals(0, pilha.tamanho());
        }
    }

    @Nested
    class PilhaComMultiplosElementos {
        PilhaComListaSequencial<Integer> pilha;
        final int[] ELEMENTOS = {10, 20, 30, 40};

        @BeforeEach
        void setup() {
            pilha = new PilhaComListaSequencial<>();
            for (int elemento : ELEMENTOS) {
                pilha.empilhar(elemento);
            }
        }

        @Test
        void tamanhoCorreto() {
            assertEquals(ELEMENTOS.length, pilha.tamanho());
        }

        @Test
        void estaVaziaEhFalse() {
            assertFalse(pilha.estaVazia());
        }

        @Test
        void obterTopoUltimoElemento() {
            assertEquals(40, pilha.obterTopo());
        }

        @Test
        void desempilharOrdemLIFO() {
            assertEquals(40, pilha.desempilhar());
            assertEquals(30, pilha.desempilhar());
            assertEquals(20, pilha.desempilhar());
            assertEquals(10, pilha.desempilhar());
            assertTrue(pilha.estaVazia());
        }

        @Test
        void contemElementosExistentes() {
            assertTrue(pilha.contem(10));
            assertTrue(pilha.contem(30));
        }

        @Test
        void contemElementoInexistente() {
            assertFalse(pilha.contem(99));
        }

        @Test
        void distanciaElementos() {
            assertEquals(0, pilha.distancia(40)); // Topo
            assertEquals(1, pilha.distancia(30)); 
            assertEquals(2, pilha.distancia(20)); 
            assertEquals(3, pilha.distancia(10)); // Base
        }

        @Test
        void distanciaElementoInexistente() {
            assertEquals(-1, pilha.distancia(99));
        }

        @Test
        void imprimirFormatoCorreto() {
            assertEquals("[10, 20, 30, 40]", pilha.imprimir());
        }

        @Test
        void iteratorPercorreBaseParaTopo() {
            Iterator<Integer> it = pilha.iterator();
            for (int elemento : ELEMENTOS) {
                assertTrue(it.hasNext());
                assertEquals(elemento, it.next());
            }
            assertFalse(it.hasNext());
        }

        @Test
        void limparRemoveTodosElementos() {
            pilha.limpar();
            assertEquals(0, pilha.tamanho());
            assertTrue(pilha.estaVazia());
        }
    }

    @Nested
    class PilhaAposDesempilhar {
        PilhaComListaSequencial<Integer> pilha;

        @BeforeEach
        void setup() {
            pilha = new PilhaComListaSequencial<>();
            pilha.empilhar(10);
            pilha.empilhar(20);
            pilha.desempilhar();
        }

        @Test
        void topoAtualizado() {
            assertEquals(10, pilha.obterTopo());
        }

        @Test
        void tamanhoAtualizado() {
            assertEquals(1, pilha.tamanho());
        }

        @Test
        void contemElementoRestante() {
            assertTrue(pilha.contem(10));
        }

        @Test
        void naoContemElementoRemovido() {
            assertFalse(pilha.contem(20));
        }
    }

    @Nested
    class PilhaComElementosNulos {
        PilhaComListaSequencial<Integer> pilha;

        @BeforeEach
        void setup() {
            pilha = new PilhaComListaSequencial<>();
            pilha.empilhar(null);
            pilha.empilhar(23);
            pilha.empilhar(42);
        }

        @Test
        void contemElementoNulo() {
            assertTrue(pilha.contem(null));
        }

        @Test
        void distanciaElementoNulo() {
            assertEquals(2, pilha.distancia(null));
        }

        @Test
        void obterTopoIgnoraNulo() {
            assertEquals(42, pilha.obterTopo());
        }
    }   

}
