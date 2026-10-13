package estruturas.lineares.dinamicas.lista;

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

public class TestesListaDuplamenteEncadeada {

    private IListaDuplamenteEncadeada<Integer> lista;

    @Nested
    class ListaVazia  {

        @BeforeEach
        void setUp() {
            lista = new ListaDuplamenteEncadeada<>();
        }

        @Test
        void obterPrimeiroNo_retornaNull() {
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void obterUltimoNo_retornaNull() {
            assertNull(lista.obterUltimoNo());
        }

        @Test
        void limpar_naoAlteraEstado() {
            lista.limpar();
            assertEquals(0, lista.tamanho());
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void tamanho_retornaZero() {
            assertEquals(0, lista.tamanho());
        }

        @Test
        void contem_retornaFalse() {
            assertFalse(lista.contem(10));
        }

        @Test
        void estaVazia_retornaTrue() {
            assertTrue(lista.estaVazia());
        }

        @Test
        void obterNo_lancaExcecao() {
            assertThrows(IndexOutOfBoundsException.class, () -> lista.obterNoDuplamenteEncadeado(0));
        }

        @Test
        void adicionar_incluiPrimeiroElemento() {
            lista.adicionarInicio(5);
            assertEquals(1, lista.tamanho());
            assertEquals(5, lista.obterPrimeiroNo().obterElemento());
            assertEquals(5, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void adicionarFim_incluiPrimeiroElemento() {
            lista.adicionarFim(5);
            assertEquals(1, lista.tamanho());
            assertEquals(5, lista.obterPrimeiroNo().obterElemento());
            assertEquals(5, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void adicionarComPosicao_incluiPrimeiroElemento() {
            lista.inserir(5, 0);
            assertEquals(1, lista.tamanho());
            assertEquals(5, lista.obterPrimeiroNo().obterElemento());
        }

        @Test
        void removerInicio_lancaExcecao() {
            assertThrows(NoSuchElementException.class, () -> lista.removerInicio());
        }

        @Test
        void removerFim_lancaExcecao() {
            assertThrows(NoSuchElementException.class, () -> lista.removerFim());
        }

        @Test
        void removerPosicao_lancaExcecao() {
            assertThrows(IndexOutOfBoundsException.class, () -> lista.remover(0));
        }

        @Test
        void imprimir_retornaStringVazia() {
            assertEquals("", lista.imprimir());
        }

        @Test
        void iterator_hasNextRetornaFalse() {
            Iterator<Integer> it = lista.iterator();
            assertFalse(it.hasNext());
            assertThrows(NoSuchElementException.class, () -> it.next());
        }
        

    }

    @Nested
    class ListaComUmElemento {

        @BeforeEach
        void setUp() {
            lista = new ListaDuplamenteEncadeada<>();
            lista.adicionarInicio(10);
        }

        @Test
        void obterPrimeiroNo_retornaElemento() {
            assertEquals(10, lista.obterPrimeiroNo().obterElemento());
        }

        @Test
        void obterUltimoNo_retornaElemento() {
            assertEquals(10, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void limpar_esvaziaLista() {
            lista.limpar();
            assertEquals(0, lista.tamanho());
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void tamanho_retornaUm() {
            assertEquals(1, lista.tamanho());
        }

        @Test
        void contem_retornaTrueParaExistente() {
            assertTrue(lista.contem(10));
        }

        @Test
        void contem_retornaFalseParaInexistente() {
            assertFalse(lista.contem(20));
        }

        @Test
        void estaVazia_retornaFalse() {
            assertFalse(lista.estaVazia());
        }

        @Test
        void obterNo_retornaElementoPosicaoZero() {
            assertEquals(10, lista.obterNoDuplamenteEncadeado(0).obterElemento());
        }

        @Test
        void adicionar_incluiNoInicio() {
            lista.adicionarInicio(5);
            assertEquals(5, lista.obterPrimeiroNo().obterElemento());
            assertEquals(10, lista.obterNoDuplamenteEncadeado(1).obterElemento());
            assertEquals(2, lista.tamanho());
        }

        @Test
        void adicionarFim_incluiNoFinal() {
            lista.adicionarFim(15);
            assertEquals(10, lista.obterPrimeiroNo().obterElemento());
            assertEquals(15, lista.obterUltimoNo().obterElemento());
            assertEquals(2, lista.tamanho());
        }

        @Test
        void adicionarComPosicao_incluiNoInicio() {
            lista.inserir(5, 0);
            assertEquals(5, lista.obterPrimeiroNo().obterElemento());
            assertEquals(10, lista.obterNoDuplamenteEncadeado(1).obterElemento());
        }

        @Test
        void adicionarComPosicao_incluiNoFim() {
            lista.inserir(15, 1);
            assertEquals(10, lista.obterPrimeiroNo().obterElemento());
            assertEquals(15, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void removerInicio_esvaziaLista() {
            lista.removerInicio();
            assertTrue(lista.estaVazia());
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void removerFim_esvaziaLista() {
            lista.removerFim();
            assertTrue(lista.estaVazia());
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void removerPosicao_removeElemento() {
            lista.remover(0);
            assertTrue(lista.estaVazia());
        }

        @Test
        void imprimir_retornarStringCorreta() {
            assertEquals("10", lista.imprimir());
        }

        @Test
        void iterator_percorreElementos() {
            Iterator<Integer> it = lista.iterator();
            assertTrue(it.hasNext());
            assertEquals(10, it.next());
            assertFalse(it.hasNext());
        }

    }

    @Nested
    class ListaComMultiplosElementos {

        @BeforeEach
        void setUp() {
            lista = new ListaDuplamenteEncadeada<>();
            lista.adicionarInicio(30);
            lista.adicionarInicio(20);
            lista.adicionarInicio(10); // Lista: 10 -> 20 -> 30
        }

        @Test
        void obterPrimeiroNo_retornaPrimeiro() {
            assertEquals(10, lista.obterPrimeiroNo().obterElemento());
        }

        @Test
        void obterUltimoNo_retornaUltimo() {
            assertEquals(30, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void limpar_esvaziaListaCompleta() {
            lista.limpar();
            assertEquals(0, lista.tamanho());
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void tamanho_retornaTres() {
            assertEquals(3, lista.tamanho());
        }

        @Test
        void contem_retornaTrueParaExistente() {
            assertTrue(lista.contem(20));
        }

        @Test
        void contem_retornaFalseParaInexistente() {
            assertFalse(lista.contem(40));
        }

        @Test
        void estaVazia_retornaFalse() {
            assertFalse(lista.estaVazia());
        }

        @Test
        void obterNo_retornaElementoMeio() {
            assertEquals(20, lista.obterNoDuplamenteEncadeado(1).obterElemento());
        }

        @Test
        void obterNo_retornaElementoInicio() {
            assertEquals(10, lista.obterNoDuplamenteEncadeado(0).obterElemento());
        }

        @Test
        void obterNo_retornaElementoFim() {
            assertEquals(30, lista.obterNoDuplamenteEncadeado(2).obterElemento());
        }

        @Test
        void obterNo_posicaoInvalidaLancaExcecao() {
            assertThrows(IndexOutOfBoundsException.class, () -> lista.obterNoDuplamenteEncadeado(3));
        }

        @Test
        void adicionar_incluiNoInicio() {
            lista.adicionarInicio(5);
            assertEquals(5, lista.obterPrimeiroNo().obterElemento());
            assertEquals(4, lista.tamanho());
        }

        @Test
        void adicionarFim_incluiNoFinal() {
            lista.adicionarFim(40);
            assertEquals(40, lista.obterUltimoNo().obterElemento());
            assertEquals(4, lista.tamanho());
        }

        @Test
        void adicionarComPosicao_incluiNoMeio() {
            lista.inserir(25, 2); // Inserir entre 20 e 30
            assertEquals(25, lista.obterNoDuplamenteEncadeado(2).obterElemento());
            assertEquals(4, lista.tamanho());
        }

        @Test
        void removerInicio_removePrimeiroElemento() {
            lista.removerInicio();
            assertEquals(20, lista.obterPrimeiroNo().obterElemento());
            assertEquals(2, lista.tamanho());
        }

        @Test
        void removerFim_removeUltimoElemento() {
            lista.removerFim();
            assertEquals(20, lista.obterUltimoNo().obterElemento());
            assertEquals(2, lista.tamanho());
        }

        @Test
        void removerPosicao_removeElementoMeio() {
            lista.remover(1); // Remove 20
            assertEquals(10, lista.obterNoDuplamenteEncadeado(0).obterElemento());
            assertEquals(30, lista.obterNoDuplamenteEncadeado(1).obterElemento());
            assertEquals(2, lista.tamanho());
        }

        @Test
        void imprimir_retornarRepresentacaoCorreta() {
            assertEquals("10 -> 20 -> 30", lista.imprimir());
        }

        @Test
        void iterator_percorreTodosElementos() {
            Iterator<Integer> it = lista.iterator();
            assertEquals(10, it.next());
            assertEquals(20, it.next());
            assertEquals(30, it.next());
            assertFalse(it.hasNext());
        }

    }

}
