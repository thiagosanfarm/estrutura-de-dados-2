package estruturas.lineares.dinamicas.fila;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import estruturas.lineares.IFila;

public class TestesFilaComListaDuplamenteEncadeada {

     private IFila<Integer> fila;

    @BeforeEach
    void setUp() {
        fila = new FilaComListaDuplamenteEncadeada<>();
    }

    @Nested
    class ComFilaVazia {

        @Test
        void tamanho_deveSerZero() {
            assertEquals(0, fila.tamanho());
        }

        @Test
        void estaVazia_deveSerTrue() {
            assertTrue(fila.estaVazia());
        }

        @Test
        void contem_deveRetornarFalse() {
            assertFalse(fila.contem(1));
        }

        @Test
        void obterFrente_deveLancarExcecao() {
            assertThrows(IllegalStateException.class, () -> fila.obterFrente());
        }

        @Test
        void desenfileirar_deveLancarExcecao() {
            assertThrows(IllegalStateException.class, () -> fila.desenfileirar());
        }

        @Test
        void distancia_deveRetornarMenosUm() {
            assertEquals(-1, fila.distancia(1));
        }

        @Test
        void imprimir_deveRetornarStringVazia() {
            assertEquals("", fila.imprimir());
        }

        @Test
        void iterator_naoTemProximo() {
            Iterator<Integer> it = fila.iterator();
            assertFalse(it.hasNext());
            assertThrows(NoSuchElementException.class, () -> it.next());
        }

        @Test
        void limpar_manterFilaVazia() {
            fila.limpar();
            assertTrue(fila.estaVazia());
        }
    }

    @Nested
    class ComElementos {

        @BeforeEach
        void adicionarElementos() {
            fila.enfileirar(10);
            fila.enfileirar(20);
            fila.enfileirar(30);
        }

        @Test
        void tamanho_deveSerTres() {
            assertEquals(3, fila.tamanho());
        }

        @Test
        void estaVazia_deveSerFalse() {
            assertFalse(fila.estaVazia());
        }

        @Test
        void contem_elementoExistente() {
            assertTrue(fila.contem(20));
        }

        @Test
        void contem_elementoInexistente() {
            assertFalse(fila.contem(40));
        }

        @Test
        void obterFrente_deveRetornarPrimeiroElemento() {
            assertEquals(10, fila.obterFrente());
        }

        @Test
        void enfileirar_acrescentaElemento() {
            fila.enfileirar(40);
            assertEquals(4, fila.tamanho());
            assertEquals(10, fila.obterFrente());
        }

        @Test
        void desenfileirar_removePrimeiroElemento() {
            assertEquals(10, fila.desenfileirar());
            assertEquals(2, fila.tamanho());
            assertEquals(20, fila.obterFrente());
        }

        @Test
        void distancia_elementoNaFrente() {
            assertEquals(0, fila.distancia(10));
        }

        @Test
        void distancia_elementoNoMeio() {
            assertEquals(1, fila.distancia(20));
        }

        @Test
        void distancia_elementoNoFim() {
            assertEquals(2, fila.distancia(30));
        }

        @Test
        void distancia_elementoInexistente() {
            assertEquals(-1, fila.distancia(40));
        }

        @Test
        void imprimir_deveRetornarElementos() {
            assertEquals("10 -> 20 -> 30", fila.imprimir());
        }

        @Test
        void iterator_devePercorrerElementos() {
            Iterator<Integer> it = fila.iterator();
            assertTrue(it.hasNext());
            assertEquals(10, it.next());
            assertEquals(20, it.next());
            assertEquals(30, it.next());
            assertFalse(it.hasNext());
        }

        @Test
        void limpar_deveEsvaziarFila() {
            fila.limpar();
            assertTrue(fila.estaVazia());
            assertEquals(0, fila.tamanho());
        }
    }

    @Nested
    class SequenciaOperacoes {

        @Test
        void enfileirarDesenfileirarVariados() {
            fila.enfileirar(100);
            fila.enfileirar(200);
            assertEquals(100, fila.desenfileirar());
            
            fila.enfileirar(300);
            assertEquals(200, fila.obterFrente());
            assertEquals(2, fila.tamanho());
            
            fila.desenfileirar();
            fila.desenfileirar();
            assertTrue(fila.estaVazia());
        }
    }
    
}
