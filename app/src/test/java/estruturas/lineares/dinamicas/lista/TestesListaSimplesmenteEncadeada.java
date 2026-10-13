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

public class TestesListaSimplesmenteEncadeada {

    private IListaSimplesmenteEncadeada<Integer> lista;

    @BeforeEach
    void setUp() {
        lista = new ListaSimplesmenteEncadeada<>();
    }

    @Nested
    class EstadoInicial {
        @Test
        void listaVaziaAposCriacao() {
            assertTrue(lista.estaVazia());
            assertEquals(0, lista.tamanho());
            assertNull(lista.obterPrimeiroNo());
            assertNull(lista.obterUltimoNo());
        }

        @Test
        void limparListaVazia() {
            lista.limpar();
            assertTrue(lista.estaVazia());
            assertEquals(0, lista.tamanho());
        }
    }

    @Nested
    class MetodosAdicao {

        @Test
        void adicionarInicioListaVazia() {
            lista.adicionarInicio(10);
            assertEquals(1, lista.tamanho());
            assertEquals(10, lista.obterPrimeiroNo().obterElemento());
            assertEquals(10, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void adicionarInicioListaComElementos() {
            lista.adicionarInicio(20);
            lista.adicionarInicio(10);
            assertEquals(2, lista.tamanho());
            assertEquals(10, lista.obterPrimeiroNo().obterElemento());
            assertEquals(20, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void adicionarFimListaVazia() {
            lista.adicionarFim(30);
            assertEquals(1, lista.tamanho());
            assertEquals(30, lista.obterPrimeiroNo().obterElemento());
            assertEquals(30, lista.obterUltimoNo().obterElemento());
        }

        @Test
        void adicionarFimListaComElementos() {
            lista.adicionarInicio(20);
            lista.adicionarFim(30);
            assertEquals(2, lista.tamanho());
            assertEquals(20, lista.obterPrimeiroNo().obterElemento());
            assertEquals(30, lista.obterUltimoNo().obterElemento());
        }

        
        @Nested
        class AdicionarPosicao {

            @Test
            void posicaoInvalida() {
                assertThrows(IndexOutOfBoundsException.class, () -> lista.inserir(10, -1));
                assertThrows(IndexOutOfBoundsException.class, () -> lista.inserir(10, 1));
            }

            @Test
            void posicaoZeroListaVazia() {
                lista.inserir(10, 0);
                assertEquals(1, lista.tamanho());
                assertEquals(10, lista.obterPrimeiroNo().obterElemento());
            }

            @Test
            void posicaoZeroListaComElementos() {
                lista.adicionarInicio(20);
                lista.inserir(10, 0);
                assertEquals(2, lista.tamanho());
                assertEquals(10, lista.obterPrimeiroNo().obterElemento());
            }

            @Test
            void posicaoFinal() {
                lista.adicionarInicio(10);
                lista.inserir(30, 1);
                assertEquals(2, lista.tamanho());
                assertEquals(30, lista.obterUltimoNo().obterElemento());
            }

            @Test
            void posicaoMeio() {
                lista.adicionarInicio(30);
                lista.adicionarInicio(10);
                lista.inserir(20, 1);
                assertEquals(3, lista.tamanho());
                assertEquals(20, lista.obterPrimeiroNo().obterProximoNo().obterElemento());
            }

        }

    }

    @Nested
    class MetodosRemocao {

        @BeforeEach
        void adicionarElementos() {
            lista.adicionarInicio(30);
            lista.adicionarInicio(20);
            lista.adicionarInicio(10);
        }

        @Test
        void removerInicioListaUnitaria() {
            lista.limpar();
            lista.adicionarInicio(10);
            lista.removerInicio();
            assertTrue(lista.estaVazia());
            assertNull(lista.obterPrimeiroNo());
            assertNull(lista.obterUltimoNo());
        }

        @Test
        void removerInicioListaComElementos() {
            lista.removerInicio();
            assertEquals(2, lista.tamanho());
            assertEquals(20, lista.obterPrimeiroNo().obterElemento());
        }

        @Test
        void removerFimListaUnitaria() {
            lista.limpar();
            lista.adicionarInicio(10);
            lista.removerFim();
            assertTrue(lista.estaVazia());
            assertNull(lista.obterPrimeiroNo());
        }

        @Test
        void removerFimListaComElementos() {
            lista.removerFim();
            assertEquals(2, lista.tamanho());
            assertEquals(20, lista.obterUltimoNo().obterElemento());
        }

        @Nested
        class RemoverElemento {

            @Test
            void elementoInexistente() {
                assertThrows(IndexOutOfBoundsException.class, () ->  lista.remover(Integer.valueOf(40)));
                assertEquals(3, lista.tamanho());
            }

            @Test
            void elementoInicio() {
                lista.remover(Integer.valueOf(10));
                assertEquals(2, lista.tamanho());
                assertEquals(20, lista.obterPrimeiroNo().obterElemento());
            }

            @Test
            void elementoFim() {
                lista.remover(Integer.valueOf(30));
                assertEquals(2, lista.tamanho());
                assertEquals(20, lista.obterUltimoNo().obterElemento());
            }

            @Test
            void elementoMeio() {
                lista.remover(Integer.valueOf(20));
                assertEquals(2, lista.tamanho());
                assertEquals(10, lista.obterPrimeiroNo().obterElemento());
                assertEquals(30, lista.obterUltimoNo().obterElemento());
                assertEquals(30, lista.obterPrimeiroNo().obterProximoNo().obterElemento());
            }
        }

    }

    @Nested
    class MetodosConsulta {
        @BeforeEach
        void adicionarElementos() {
            lista.adicionarInicio(30);
            lista.adicionarInicio(20);
            lista.adicionarInicio(10);
        }

        @Test
        void contemElemento() {
            assertTrue(lista.contem(10));
            assertTrue(lista.contem(20));
            assertTrue(lista.contem(30));
            assertFalse(lista.contem(40));
        }

        @Test
        void posicaoElemento() {
            assertEquals(0, lista.posicao(10));
            assertEquals(1, lista.posicao(20));
            assertEquals(2, lista.posicao(30));
            assertEquals(-1, lista.posicao(40));
        }

        @Test
        void estaVazia() {
            assertFalse(lista.estaVazia());
            lista.limpar();
            assertTrue(lista.estaVazia());
        }

        @Test
        void tamanhoLista() {
            assertEquals(3, lista.tamanho());
            lista.removerInicio();
            assertEquals(2, lista.tamanho());
        }

        @Test
        void imprimirLista() {
            assertEquals("10 -> 20 -> 30", lista.imprimir());
            lista.limpar();
            assertEquals("", lista.imprimir());
        }
    }
    
    @Nested
    class Iterador {
        
        @Test
        void iteradorListaVazia() {
            Iterator<Integer> it = lista.iterator();
            assertFalse(it.hasNext());
            assertThrows(NoSuchElementException.class, it::next);
        }

        @Test
        void iteradorListaComElementos() {
            lista.adicionarInicio(30);
            lista.adicionarInicio(20);
            lista.adicionarInicio(10);
            Iterator<Integer> it = lista.iterator();
            
            assertTrue(it.hasNext());
            assertEquals(10, it.next());
            assertEquals(20, it.next());
            assertEquals(30, it.next());
            assertThrows(NoSuchElementException.class, it::next);
            
        }

       
    }
}
