package estruturas.lineares.estaticas.lista;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TestesListaSequencial {

    private ListaSequencial<Integer> lista;

    @BeforeEach
    void setUp() {
        lista = new ListaSequencial<>(5);
    }

    @Nested
    @DisplayName("Testes de Inicialização")
    class TestesInicializacao {

        @Test
        void deveCriarListaComCapacidadeValida() {
            assertDoesNotThrow(() -> new ListaSequencial<>(10));
        }

        @Test
        void deveLancarExcecaoParaCapacidadeNegativa() {
            assertThrows(IllegalArgumentException.class, 
                () -> new ListaSequencial<>(-1));
        }

        @Test
        void listaNovaDeveEstarVazia() {
            assertTrue(lista.estaVazia());
            assertEquals(0, lista.tamanho());
        }

    }

    @Nested
    @DisplayName("Testes de Operações de Consulta")
    class TestesOperacoesConsulta {

        @BeforeEach
        void setUp() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.inserir(30, 2);
        }

        @Test
        void deveRetornarTamanhoCorreto() {
            assertEquals(3, lista.tamanho());
        }

        @Test
        void deveRetornarFalsoParaListaNaoVazia() {
            assertFalse(lista.estaVazia());
        }

        @Test
        void deveEncontrarElementoExistente() {
            assertTrue(lista.contem(20));
            assertEquals(1, lista.posicao(20));
        }

        @Test
        void deveRetornarMenosUmParaElementoInexistente() {
            assertEquals(-1, lista.posicao(99));
            assertFalse(lista.contem(99));
        }

        @Test
        void deveObterElementoNaPosicao() {
            assertEquals(10, lista.obter(0));
            assertEquals(20, lista.obter(1));
            assertEquals(30, lista.obter(2));
        }

        @Test
        void imprimirDeveRetornarFormatoCorreto() {
            String resultado = lista.imprimir();
            assertTrue(resultado.contains("10") && resultado.contains("20") && resultado.contains("30"));
        }

    }

    @Nested
    @DisplayName("Testes de Operações de Inserção")
    class TestesOperacoesInsercao {

        @Test
        void deveInserirNoInicioListaVazia() {
            lista.inserir(100, 0);
            assertEquals(1, lista.tamanho());
            assertEquals(100, lista.obter(0));
        }

        @Test
        void deveInserirNoFim() {
            lista.inserir(100, 0);
            lista.inserir(200, 1);
            assertEquals(200, lista.obter(1));
        }

        @Test
        void deveInserirNoMeio() {
            lista.inserir(100, 0);
            lista.inserir(300, 1);
            lista.inserir(200, 1); // Insere no meio
            
            assertEquals(100, lista.obter(0));
            assertEquals(200, lista.obter(1));
            assertEquals(300, lista.obter(2));
        }

        @Test
        void deveAceitarElementoNull() {
            assertDoesNotThrow(() -> lista.inserir(null, 0));
            assertTrue(lista.contem(null));
        }

    }

    @Nested
    @DisplayName("Testes de Operações de Atualização")
    class TestesOperacoesAtualizacao {

        @BeforeEach
        void setUp() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
        }

        @Test
        void deveAtualizarElementoExistente() {
            lista.atualizar(1, 200);
            assertEquals(200, lista.obter(1));
        }

        @Test
        void deveAtualizarPrimeiroElemento() {
            lista.atualizar(0, 100);
            assertEquals(100, lista.obter(0));
        }

        @Test
        void deveAtualizarComNull() {
            assertDoesNotThrow(() -> lista.atualizar(0, null));
            assertNull(lista.obter(0));
        }

    }

    @Nested
    @DisplayName("Testes de Operações de Remoção")
    class TestesOperacoesRemocao {

        @BeforeEach
        void setUp() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.inserir(30, 2);
        }

        @Test
        void deveRemoverPrimeiroElemento() {
            Integer removido = lista.remover(0);
            assertEquals(10, removido);
            assertEquals(2, lista.tamanho());
            assertEquals(20, lista.obter(0));
        }

        @Test
        void deveRemoverUltimoElemento() {
            Integer removido = lista.remover(2);
            assertEquals(30, removido);
            assertEquals(2, lista.tamanho());
            assertFalse(lista.contem(30));
        }

        @Test
        void deveRemoverElementoMeio() {
            Integer removido = lista.remover(1);
            assertEquals(20, removido);
            assertEquals(2, lista.tamanho());
            assertEquals(10, lista.obter(0));
            assertEquals(30, lista.obter(1));
        }

        @Test
        void deveRemoverUnicoElemento() {
            ListaSequencial<Integer> listaUnica = new ListaSequencial<>(5);
            listaUnica.inserir(100, 0);
            Integer removido = listaUnica.remover(0);
            assertEquals(100, removido);
            assertTrue(listaUnica.estaVazia());
        }

    }

    @Nested
    @DisplayName("Testes de Operações de Limpeza")
    class TestesOperacoesLimpeza {
        
        @Test
        void deveLimparListaVazia() {
            assertDoesNotThrow(() -> lista.limpar());
            assertTrue(lista.estaVazia());
        }

        @Test
        void deveLimparListaComElementos() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.limpar();
            assertTrue(lista.estaVazia());
            assertEquals(0, lista.tamanho());
        }

        @Test
        void devePermitirReutilizacaoAposLimpeza() {
            lista.inserir(10, 0);
            lista.limpar();
            lista.inserir(20, 0);
            assertEquals(1, lista.tamanho());
            assertEquals(20, lista.obter(0));
        }

    }

    @Nested
    @DisplayName("Testes de Iteração")
    class TestesIteracao {

        @BeforeEach
        void setUp() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.inserir(30, 2);
        }

        @Test
        void iteratorDevePercorrerTodosElementos() {
            Iterator<Integer> it = lista.iterator();
            assertTrue(it.hasNext());
            assertEquals(10, it.next());
            assertEquals(20, it.next());
            assertEquals(30, it.next());
            assertFalse(it.hasNext());
        }

        @Test
        void iteratorDeveLancarExcecaoQuandoNaoHaProximo() {
            Iterator<Integer> it = lista.iterator();
            it.next(); // 10
            it.next(); // 20
            it.next(); // 30
            assertThrows(NoSuchElementException.class, it::next);
        }

        @Test
        void iteratorListaVaziaNaoTemProximo() {
            ListaSequencial<Integer> listaVazia = new ListaSequencial<>(5);
            Iterator<Integer> it = listaVazia.iterator();
            assertFalse(it.hasNext());
        }
    
        @Test
        void deveFuncionarComForEach() {
            int soma = 0;
            int count = 0;
            for (Integer elemento : lista) {
                soma += elemento;
                count++;
            }
            assertEquals(60, soma);
            assertEquals(3, count);
        }
    
    }

    @Nested
    @DisplayName("Testes de Validação dos Indices")
    class TestesValidacaoIndices {

        @Test
        void deveLancarExcecaoAoObterPosicaoInvalida() {
            lista.inserir(10, 0);
            assertThrows(IndexOutOfBoundsException.class, () -> lista.obter(1));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.obter(-1));
        }

        @Test
        void deveLancarExcecaoAoInserirPosicaoInvalida() {
            assertThrows(IndexOutOfBoundsException.class, () -> lista.inserir(10, 1));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.inserir(10, -1));
        }

        @Test
        void deveLancarExcecaoAoAtualizarPosicaoInvalida() {
            assertThrows(IndexOutOfBoundsException.class, () -> lista.atualizar(0, 10));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.atualizar(-1, 10));
        }

        @Test
        void deveLancarExcecaoAoRemoverPosicaoInvalida() {
            assertThrows(IndexOutOfBoundsException.class, () -> lista.remover(0));
            assertThrows(IndexOutOfBoundsException.class, () -> lista.remover(-1));
        }

    }

    @Nested
    @DisplayName("Testes de Capacidade dos Limites")
    class TestesCapacidadeLimites {

        @Test
        void deveLancarExcecaoTerCapacidadeExcedida() {
            ListaSequencial<Integer> listaPequena = new ListaSequencial<>(2);
            listaPequena.inserir(1, 0);
            listaPequena.inserir(2, 1);
            assertThrows(IllegalStateException.class, () -> listaPequena.inserir(3, 2));
        }

        @Test
        void deveAceitarCapacidadeZero() {
            assertDoesNotThrow(() -> new ListaSequencial<>(0));
            ListaSequencial<Integer> listaZero = new ListaSequencial<>(0);
            assertThrows(IllegalStateException.class, () -> listaZero.inserir(1, 0));
        }

        @Test
        void deveManterEstadoConsistenteAposMultiplasOperacoes() {
            // Teste de estresse simples
            for (int i = 0; i < 5; i++) {
                lista.inserir(i, i);
            }
            assertEquals(5, lista.tamanho());
            
            lista.remover(2); // Remove elemento do meio
            assertEquals(4, lista.tamanho());
            
            lista.atualizar(1, 100);
            assertEquals(100, lista.obter(1));
            
            lista.limpar();
            assertTrue(lista.estaVazia());
        }

    }

    @Nested
    @DisplayName("Testes de Comportamento de Ordenação")
    class TestesComportamentoOrdenacao {

        @Test
        void deveManterOrdemCorretaAposInsercaoNoMeio() {
            lista.inserir(10, 0); // [10]
            lista.inserir(30, 1); // [10, 30]
            lista.inserir(20, 1); // [10, 20, 30]
            
            assertEquals(10, lista.obter(0));
            assertEquals(20, lista.obter(1));
            assertEquals(30, lista.obter(2));
        }
        
        @Test
        void deveManterOrdemCorretaAposRemocaoDoMeio() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.inserir(30, 2);
            
            lista.remover(1); // Remove o 20
            
            assertEquals(10, lista.obter(0));
            assertEquals(30, lista.obter(1));
            assertEquals(2, lista.tamanho());
        }
        
        @Test
        void deveEncontrarPrimeiraOcorrenciaEmElementosDuplicados() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.inserir(10, 2); // Duplicado
            
            assertEquals(0, lista.posicao(10)); // Deve retornar a primeira ocorrência
        }

    }

    @Nested
    @DisplayName("Testes de Operações Derivadas de Mutação")
    class TestesOperacoesDerivadasMutacao {

        @BeforeEach
        void setUp() {
            lista.inserir(10, 0);
            lista.inserir(20, 1);
            lista.inserir(30, 2);
        }

        @Nested
        @DisplayName("Testes de Operação de Inserção no Início")
        class TestesOperacaoInsercaoNoInicio {

            @Test
            void inserirInicioDeveAdicionarNoComeco() {
                lista.adicionarInicio(5);
                
                assertEquals(4, lista.tamanho());
                assertEquals(5, lista.obter(0));
                assertEquals(10, lista.obter(1));
                assertEquals(20, lista.obter(2));
                assertEquals(30, lista.obter(3));
            }

            @Test
            void inserirInicioEmListaVazia() {
                ListaSequencial<Integer> listaVazia = new ListaSequencial<>(5);
                listaVazia.adicionarInicio(100);
                
                assertEquals(1, listaVazia.tamanho());
                assertEquals(100, listaVazia.obter(0));
            }

            @Test
            void inserirInicioComElementoNull() {
                assertDoesNotThrow(() -> lista.adicionarInicio(null));
                assertTrue(lista.contem(null));
                assertEquals(0, lista.posicao(null));
            }

        }

        @Nested
        @DisplayName("Testes de Operação de Remoção Por Elemento")
        class TestesOperacaoRemocaoPorElemento {

            @Test
            void removerPorElementoExistente() {
                lista.remover(Integer.valueOf(20));
                
                assertEquals(2, lista.tamanho());
                assertFalse(lista.contem(20));
                assertEquals(10, lista.obter(0));
                assertEquals(30, lista.obter(1));
            }

            @Test
            void removerPorElementoPrimeiraOcorrencia() {
                lista.inserir(20, 3); // Adiciona outra ocorrência do 20
                lista.remover(Integer.valueOf(20)); // Deve remover apenas a primeira ocorrência
                
                assertEquals(3, lista.tamanho());
                assertEquals(10, lista.obter(0));
                assertEquals(30, lista.obter(1));
                assertEquals(20, lista.obter(2)); // A segunda ocorrência ainda deve existir
            }
        
            @Test
            void removerPorElementoInexistenteNaoAlteraLista() {
                int tamanhoOriginal = lista.tamanho();
                lista.remover(Integer.valueOf(99)); // Elemento que não existe
                
                assertEquals(tamanhoOriginal, lista.tamanho());
                assertTrue(lista.contem(10));
                assertTrue(lista.contem(20));
                assertTrue(lista.contem(30));
            }
        
            @Test
            void removerPorElementoNull() {
                lista.inserir(null, 1);
                lista.remover(null);
                
                assertFalse(lista.contem(null));
                assertEquals(3, lista.tamanho()); // Os outros 3 elementos permanecem
            }

        }
        
        @Nested
        @DisplayName("Testes de Operação de Remoção no Início")
        class TestesOperacaoRemocaoNoInicio {
            
            @Test
            void removerInicioDeveRemoverPrimeiroElemento() {
                lista.removerInicio();
                
                assertEquals(2, lista.tamanho());
                assertEquals(20, lista.obter(0));
                assertEquals(30, lista.obter(1));
            }
            
            @Test
            void removerInicioEmListaComUmElemento() {
                ListaSequencial<Integer> listaUnica = new ListaSequencial<>(5);
                listaUnica.inserir(100, 0);
                listaUnica.removerInicio();
                
                assertTrue(listaUnica.estaVazia());
                assertEquals(0, listaUnica.tamanho());
            }
            
            @Test
            void removerInicioEmListaVaziaDeveLancarExcecao() {
                ListaSequencial<Integer> listaVazia = new ListaSequencial<>(5);
                assertThrows(IndexOutOfBoundsException.class, listaVazia::removerInicio);
            }

        }
      
        @Nested
        @DisplayName("Testes de Operação de Remoção no Fim")
        class TestesOperacaoRemocaoNoFim {

            @Test
            void removerFimDeveRemoverUltimoElemento() {
                lista.removerFim();
                
                assertEquals(2, lista.tamanho());
                assertEquals(10, lista.obter(0));
                assertEquals(20, lista.obter(1));
                assertFalse(lista.contem(30));
            }
            
            @Test
            void removerFimEmListaComUmElemento() {
                ListaSequencial<Integer> listaUnica = new ListaSequencial<>(5);
                listaUnica.inserir(100, 0);
                listaUnica.removerFim();
                
                assertTrue(listaUnica.estaVazia());
                assertEquals(0, listaUnica.tamanho());
            }
            
            @Test
            void removerFimEmListaVaziaDeveLancarExcecao() {
                ListaSequencial<Integer> listaVazia = new ListaSequencial<>(5);
                assertThrows(IndexOutOfBoundsException.class, listaVazia::removerFim);
            }

        }
        
        @Nested
        @DisplayName("Testes de Sequencia de Multiplas Operações Derivadas")
        class TestesSequenciaMultiplasOperacoesDerivadas {

            @Test
            void sequenciaOperacoesMutacaoAdicionais() {
                // Teste integrado com várias operações
                lista.adicionarInicio(5);
                assertEquals(5, lista.obter(0));
                
                lista.remover(Integer.valueOf(20));;
                assertFalse(lista.contem(20));
                
                lista.removerInicio();
                assertEquals(10, lista.obter(0));
                
                lista.removerFim();
                assertEquals(1, lista.tamanho());
                assertEquals(10, lista.obter(0));
            }

            @Test
            void removerPorElementoAposMultiplasOperacoes() {
                lista.adicionarInicio(5);
                lista.inserir(25, 2);
                
                // Lista: [5, 10, 25, 20, 30]
                lista.remover(Integer.valueOf(25));
                
                assertEquals(4, lista.tamanho());
                assertEquals(5, lista.obter(0));
                assertEquals(10, lista.obter(1));
                assertEquals(20, lista.obter(2));
                assertEquals(30, lista.obter(3));
            }

        }

        @Nested
        @DisplayName("Testes de Validação dos Indices das Operações Derivadas de Mutação")
        class TestesValidacaoIndicesOperacoesDerivadasMutacao {

            @Test
            void removerInicioListaVaziaDeveLancarExcecao() {
                ListaSequencial<Integer> listaVazia = new ListaSequencial<>(5);
                assertThrows(IndexOutOfBoundsException.class, listaVazia::removerInicio);
            }
            
            @Test
            void removerFimListaVaziaDeveLancarExcecao() {
                ListaSequencial<Integer> listaVazia = new ListaSequencial<>(5);
                assertThrows(IndexOutOfBoundsException.class, listaVazia::removerFim);
            }


        }
    
        @Nested
        @DisplayName("Testes de Capacidade dos Limitres das Operações Derivadas de Mutação")
        class TestesCapacidadeLimitesOperacoesDerivadasMutacao {

            @Test
            void inserirInicioComCapacidadeMaximaDeveLancarExcecao() {
                ListaSequencial<Integer> listaCheia = new ListaSequencial<>(3);
                listaCheia.inserir(1, 0);
                listaCheia.inserir(2, 1);
                listaCheia.inserir(3, 2);
                
                assertThrows(IllegalStateException.class, () -> listaCheia.adicionarInicio(0));
            }

        }
        
        @Nested
        @DisplayName("Testes de Comportamento de Ordenação das Operações Derivadas de Mutação")
        class TestesComportamentoOrdenacaoOperacoesDerivadasMutacao {

            @Test
            void removerPorElementoDevePreservarOrdemDosElementosRestantes() {
                lista.inserir(40, 3);
                lista.inserir(50, 4);
                // Lista: [10, 20, 30, 40, 50]
                
                lista.remover(Integer.valueOf(30));; // Remove elemento do meio
                
                assertEquals(4, lista.tamanho());
                assertEquals(10, lista.obter(0));
                assertEquals(20, lista.obter(1));
                assertEquals(40, lista.obter(2));
                assertEquals(50, lista.obter(3));
            }

            @Test
            void inserirInicioDeveDeslocarTodosElementos() {
                lista.adicionarInicio(5);
                
                assertEquals(5, lista.obter(0));
                assertEquals(10, lista.obter(1));
                assertEquals(20, lista.obter(2));
                assertEquals(30, lista.obter(3));
            }

        }
    
    }

}