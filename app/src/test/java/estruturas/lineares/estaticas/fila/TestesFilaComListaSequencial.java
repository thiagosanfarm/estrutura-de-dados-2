package estruturas.lineares.estaticas.fila;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import estruturas.lineares.IFila;


public class TestesFilaComListaSequencial {

    private IFila<Integer> fila;

    @Nested
    class ComFilaVazia {

        @BeforeEach
        void setUp() {
            fila = new FilaComListaSequencial<>();
        }

        @Test
        void tamanhoEhZero() {
            assertEquals(0, fila.tamanho());
        }

        @Test
        void estaVaziaRetornaTrue() {
            assertTrue(fila.estaVazia());
        }

        @Test
        void obterFrenteLancaExcecao() {
            assertThrows(IllegalStateException.class, () -> fila.obterFrente());
        }

        @Test
        void desenfileirarLancaExcecao() {
            assertThrows(IllegalStateException.class, () -> fila.desenfileirar());
        }

        @Test
        void contemRetornaFalse() {
            assertFalse(fila.contem(10));
        }

        @Test
        void distanciaRetornaMenosUm() {
            assertEquals(-1, fila.distancia(10));
        }

        @Test
        void imprimirRetornoCorreto() {
            assertEquals("[]", fila.imprimir());
        }

        @Test
        void iteratorNaoTemProximo() {
            Iterator<Integer> it = fila.iterator();
            assertFalse(it.hasNext());
            assertThrows(NoSuchElementException.class, () -> it.next());
        }

        @Test
        void limparMantemFilaVazia() {
            fila.limpar();
            assertTrue(fila.estaVazia());
        }
    }

    @Nested
    class OperacoesBasicas {

        @BeforeEach
        void setUp() {
            fila = new FilaComListaSequencial<>();
            fila.enfileirar(10);
            fila.enfileirar(20);
        }

        @Test
        void enfileirarAumentaTamanho() {
            fila.enfileirar(30);
            assertEquals(3, fila.tamanho());
        }

        @Test
        void desenfileirarRetornaFrenteEReduzTamanho() {
            int removido = fila.desenfileirar();
            assertEquals(10, removido);
            assertEquals(1, fila.tamanho());
        }

        @Test
        void obterFrenteSemRemover() {
            assertEquals(10, fila.obterFrente());
            assertEquals(2, fila.tamanho());
        }

        @Test
        void limparEsvaziaFila() {
            fila.limpar();
            assertTrue(fila.estaVazia());
        }

        @Test
        void contemElementoExistente() {
            assertTrue(fila.contem(20));
        }

        @Test
        void naoContemElementoInexistente() {
            assertFalse(fila.contem(30));
        }

        @Test
        void distanciaElementoExistente() {
            assertEquals(1, fila.distancia(20));
        }

        @Test
        void distanciaElementoInexistente() {
            assertEquals(-1, fila.distancia(30));
        }

        @Test
        void imprimirFormatoCorreto() {
            assertEquals("[10, 20]", fila.imprimir());
        }
    }

    @Nested
    class ComCapacidadeDefinida {

        @Test
        void filaComCapacidadeMaior() {
            FilaComListaSequencial<Integer> filaCapacidade = new FilaComListaSequencial<>(4);
            filaCapacidade.enfileirar(10);
            filaCapacidade.enfileirar(20);
            filaCapacidade.enfileirar(30);
            filaCapacidade.enfileirar(50);
            assertEquals(4, filaCapacidade.tamanho());
        }
    }

    @Nested
    class ComMultiplasOperacoes {

        @BeforeEach
        void setUp() {
            fila = new FilaComListaSequencial<>();
            fila.enfileirar(10);
            fila.enfileirar(20);
            fila.enfileirar(30);
        }

        @Test
        void desenfileirarTodosElementos() {
            assertEquals(10, fila.desenfileirar());
            assertEquals(20, fila.desenfileirar());
            assertEquals(30, fila.desenfileirar());
            assertTrue(fila.estaVazia());
        }

        @Test
        void distanciaElementoFinal() {
            assertEquals(2, fila.distancia(30));
        }

        @Test
        void distanciaElementoRepetido() {
            fila.enfileirar(10); // Adiciona repetido
            assertEquals(0, fila.distancia(10)); // Primeira ocorrência
        }

        @Test
        void iteradorPercorreElementos() {
            Iterator<Integer> it = fila.iterator();
            assertTrue(it.hasNext());
            assertEquals(10, it.next());
            assertEquals(20, it.next());
            assertEquals(30, it.next());
            assertFalse(it.hasNext());
        }
    }

    @Nested
    class ComElementosNulos {

        @Test
        void enfileirarElementoNulo() {
            fila = new FilaComListaSequencial<>();
            fila.enfileirar(null);
            assertTrue(fila.contem(null));
            assertEquals(0, fila.distancia(null));
            assertNull(fila.desenfileirar());
        }
    }


}
