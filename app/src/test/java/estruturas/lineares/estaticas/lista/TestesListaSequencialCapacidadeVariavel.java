package estruturas.lineares.estaticas.lista;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TestesListaSequencialCapacidadeVariavel {

    @Nested
    @DisplayName("[Testes] Método Construtor")
    class TestesMetodoConstrutor {

        @Nested
        @DisplayName("[Método Construtor] - Testes de Funcionalidade Básica")
        class TestesFuncionalidadeBasica {

            @Test
            @DisplayName("Deve criar lista com capacidade padrão")
            @Tag("Essencial")
            void deveCriarListaComCapacidadePadrao() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Assert
                assertNotNull(lista);
                assertTrue(lista.estaVazia());
                assertEquals(0, lista.tamanho());
                assertEquals(10, lista.capacidade()); // CAPACIDADE_PADRAO = 10
            }

            @Test
            @DisplayName("Deve criar lista com lista interna inicializada")
            void deveCriarListaComListaInternaInicializada() throws Exception {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<String> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Assert - Verificar lista interna via reflection
                Field campoLista = ListaSequencialCapacidadeVariavel.class.getDeclaredField("lista");
                campoLista.setAccessible(true);
                ListaSequencial<?> listaInterna = (ListaSequencial<?>) campoLista.get(lista);
                
                assertNotNull(listaInterna);
                assertTrue(listaInterna.estaVazia());
            }

        }

        @Nested
        @DisplayName("[Método Construtor] - Testes Edge Cases")
        class TestesEdgeCases {

            @Test
            @DisplayName("Deve criar múltiplas instâncias independentes")
            void deveCriarMultiplasInstanciasIndependentes() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista1 = new ListaSequencialCapacidadeVariavel<>();
                ListaSequencialCapacidadeVariavel<String> lista2 = new ListaSequencialCapacidadeVariavel<>();
                
                // Assert
                assertAll(
                    () -> assertNotSame(lista1, lista2),
                    () -> assertTrue(lista1.estaVazia()),
                    () -> assertTrue(lista2.estaVazia()),
                    () -> assertEquals(10, lista1.capacidade()),
                    () -> assertEquals(10, lista2.capacidade())
                );
            }

            @Test
            @DisplayName("Deve criar lista e verificar capacidade exata")
            void deveCriarListaComCapacidadeExata() throws Exception {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Assert - Verificar campos internos via reflection
                Field campoCapacidade = ListaSequencialCapacidadeVariavel.class.getDeclaredField("capacidade");
                campoCapacidade.setAccessible(true);
                int capacidade = (int) campoCapacidade.get(lista);
                
                assertEquals(10, capacidade);
                assertEquals(10, lista.capacidade());
            }

        }

        @Nested
        @DisplayName("[Método Construtor] - Testes de Invariantes")
        class TestesInvariantes {

            @Test
            @DisplayName("Invariante: lista recém-criada deve estar vazia")
            @Tag("Essencial")
            void invarianteListaNovaDeveEstarVazia() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Assert
                assertTrue(lista.estaVazia(), "Lista nova deve estar vazia");
                assertEquals(0, lista.tamanho(), "Tamanho deve ser zero");
            }

            @Test
            @DisplayName("Invariante: capacidade deve ser não-negativa após criação")
            void invarianteCapacidadeNaoNegativa() {
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                assertTrue(lista.capacidade() >= 0, "Capacidade não pode ser negativa");
            }

            @Test
            @DisplayName("Invariante: tamanho ≤ capacidade após criação")
            void invarianteTamanhoMenorOuIgualCapacidade() {
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                assertTrue(lista.tamanho() <= lista.capacidade(), 
                    "Tamanho não pode exceder capacidade");
            }

            @Test
            @DisplayName("Invariante: estado consistente entre lista interna e wrapper")
            @Tag("Essencial")
            void invarianteEstadoConsistente() throws Exception {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Assert - Verificar consistência interna
                Field campoLista = ListaSequencialCapacidadeVariavel.class.getDeclaredField("lista");
                campoLista.setAccessible(true);
                ListaSequencial<?> listaInterna = (ListaSequencial<?>) campoLista.get(lista);
                
                Field campoCapacidade = ListaSequencialCapacidadeVariavel.class.getDeclaredField("capacidade");
                campoCapacidade.setAccessible(true);
                int capacidade = (int) campoCapacidade.get(lista);
                
                assertAll(
                    () -> assertEquals(lista.tamanho(), listaInterna.tamanho()),
                    () -> assertEquals(lista.estaVazia(), listaInterna.estaVazia()),
                    () -> assertEquals(10, capacidade),
                    () -> assertTrue(listaInterna.tamanho() <= capacidade)
                );
            }

        }
       
        @Nested
        @DisplayName("[Método Construtor] - Testes de Cenários Intermediários")
        class TestesCenariosIntermediarios {

            @Test
            @DisplayName("Deve criar lista e permitir operações básicas imediatas")
            @Tag("Essencial")
            void devePermitirOperacoesBasicasImediatas() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Act - Operações básicas logo após criação
                lista.inserir(10, 0);
                int tamanho = lista.tamanho();
                boolean vazia = lista.estaVazia();
                int capacidade = lista.capacidade();
                
                // Assert
                assertAll(
                    () -> assertEquals(1, tamanho),
                    () -> assertFalse(vazia),
                    () -> assertEquals(10, capacidade),
                    () -> assertEquals(10, lista.obter(0))
                );
            }
            
            @Test
            @DisplayName("Deve criar lista com diferentes tipos genéricos")
            void deveCriarListaComDiferentesTipos() {
                // Teste com Integer
                ListaSequencialCapacidadeVariavel<Integer> listaInt = new ListaSequencialCapacidadeVariavel<>();
                listaInt.inserir(100, 0);
                
                // Teste com String
                ListaSequencialCapacidadeVariavel<String> listaStr = new ListaSequencialCapacidadeVariavel<>();
                listaStr.inserir("teste", 0);
                
                // Assert
                assertAll(
                    () -> assertEquals(100, listaInt.obter(0)),
                    () -> assertEquals("teste", listaStr.obter(0)),
                    () -> assertEquals(10, listaInt.capacidade()),
                    () -> assertEquals(10, listaStr.capacidade())
                );
            }

        }

        @Nested
        @DisplayName("[Método Construtor] - Testes de Sequências e Integração")
        class TestesSequenciasIntegracao {

            @Test
            @DisplayName("Sequência: criação → múltiplas operações → estado consistente")
            void sequenciaCriacaoMultiplasOperacoes() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Act - Sequência complexa de operações
                lista.inserir(1, 0);
                lista.inserir(3, 1);
                lista.inserir(2, 1); // Inserir no meio
                lista.remover(0);
                lista.atualizar(1, 4);
                
                // Assert - Verificar estado final e invariantes
                assertAll(
                    () -> assertFalse(lista.estaVazia()),
                    () -> assertEquals(2, lista.tamanho()),
                    () -> assertEquals(2, lista.obter(0)),
                    () -> assertEquals(4, lista.obter(1)),
                    () -> assertTrue(lista.tamanho() <= lista.capacidade())
                );
            }
            
            @Test
            @DisplayName("Sequência: criação → limpeza → reutilização")
            void sequenciaCriacaoLimpezaReutilizacao() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                lista.inserir(10, 0);
                lista.inserir(20, 1);
                
                // Act
                lista.limpar();
                
                // Assert - Deve manter capacidade mas estar vazia
                assertAll(
                    () -> assertTrue(lista.estaVazia()),
                    () -> assertEquals(0, lista.tamanho()),
                    () -> assertEquals(10, lista.capacidade()) // Capacidade mantida após limpeza
                );
                
                // Act & Assert - Reutilização
                lista.inserir(30, 0);
                assertEquals(1, lista.tamanho());
                assertEquals(30, lista.obter(0));
            }

        }

        @Nested
        @DisplayName("[Método Construtor] - Testes de Redimensionamento Futuro")
        class TestesRedimensionamentoFuturo {

            @Test
            @DisplayName("Deve criar lista pronta para redimensionamento")
            void deveCriarListaProntaParaRedimensionamento() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                int capacidadeInicial = lista.capacidade();
                
                // Act - Inserir elementos até forçar redimensionamento
                for (int i = 0; i < 15; i++) {
                    lista.inserir(i, i);
                }
                
                // Assert - Deve ter redimensionado
                assertTrue(lista.capacidade() > capacidadeInicial, "Deve ter redimensionado a capacidade");
                assertEquals(15, lista.tamanho());
                assertTrue(lista.tamanho() <= lista.capacidade());
            }

            @Test
            @DisplayName("Deve criar lista que suporta redução de capacidade")
            void deveSuportarReducaoCapacidade() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Act - Expandir e depois reduzir
                for (int i = 0; i < 20; i++) {
                    lista.inserir(i, i);
                }
                int capacidadeExpandida = lista.capacidade();
                
                // Remover elementos para forçar redução
                for (int i = 0; i < 18; i++) {
                    lista.remover(0);
                }
                
                // Assert - Deve ter reduzido a capacidade
                assertTrue(lista.capacidade() < capacidadeExpandida, "Deve ter reduzido a capacidade");
                assertTrue(lista.tamanho() <= lista.capacidade());
            }

        }

    }

    @Nested
    @DisplayName("[Testes] Método Construtor Com Capacidade")
    class TestesMetodoConstrutorComCapacidade {

        @Nested
        @DisplayName("[Método Construtor Com Capacidade] - Testes de Funcionalidade Básica")
        class TestesFuncionalidadeBasica {

            @Test
            @DisplayName("Deve criar lista com capacidade positiva específica")
            @Tag("Essencial")
            void deveCriarListaComCapacidadePositiva() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(15);
                
                // Assert
                assertNotNull(lista);
                assertTrue(lista.estaVazia());
                assertEquals(0, lista.tamanho());
                assertEquals(15, lista.capacidade());
            }
        
            @Test
            @DisplayName("Deve criar lista com capacidade zero")
            @Tag("Essencial")
            void deveCriarListaComCapacidadeZero() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<String> lista = new ListaSequencialCapacidadeVariavel<>(0);
                
                // Assert
                assertNotNull(lista);
                assertTrue(lista.estaVazia());
                assertEquals(0, lista.tamanho());
                assertEquals(0, lista.capacidade());
            }
        
            @Test
            @DisplayName("Deve criar lista com capacidade mínima (1)")
            void deveCriarListaComCapacidadeMinima() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(1);
                
                // Assert
                assertEquals(1, lista.capacidade());
                assertTrue(lista.estaVazia());
            }


        }

        @Nested
        @DisplayName("[Método Construtor Com Capacidade] - Testes Negativos")
        class TestesNegativos {
            
            @Test
            @DisplayName("Deve lançar exceção para capacidade negativa")
            @Tag("Essencial")
            void deveLancarExcecaoParaCapacidadeNegativa() {
                // Arrange, Act & Assert
                IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> new ListaSequencialCapacidadeVariavel<>(-1)
                );
                
                assertEquals("A capacidade do vetor não pode ser negativa", exception.getMessage());
            }
            
            @Test
            @DisplayName("Deve lançar exceção para capacidade negativa grande")
            void deveLancarExcecaoParaCapacidadeNegativaGrande() {
                assertThrows(IllegalArgumentException.class, 
                    () -> new ListaSequencialCapacidadeVariavel<>(-100));
            }
        }

        @Nested
        @DisplayName("[Método Construtor Com Capacidade] - Testes de Edge Cases")
        class TestesEdgeCases {
            
            @Test
            @DisplayName("Deve criar lista com capacidade grande")
            void deveCriarListaComCapacidadeGrande() {
                // Teste com capacidade considerada "grande"
                assertDoesNotThrow(() -> new ListaSequencialCapacidadeVariavel<>(1000));
                
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(1000);
                assertEquals(1000, lista.capacidade());
                assertTrue(lista.estaVazia());
            }
            
            @Test
            @DisplayName("Deve criar lista com capacidade ímpar")
            void deveCriarListaComCapacidadeImpar() {
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(7);
                assertEquals(7, lista.capacidade());
            }
            
            @Test
            @DisplayName("Deve criar múltiplas listas com capacidades diferentes")
            void deveCriarMultiplasListasComCapacidadesDiferentes() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista1 = new ListaSequencialCapacidadeVariavel<>(5);
                ListaSequencialCapacidadeVariavel<String> lista2 = new ListaSequencialCapacidadeVariavel<>(20);
                ListaSequencialCapacidadeVariavel<Double> lista3 = new ListaSequencialCapacidadeVariavel<>(1);
                
                // Assert
                assertAll(
                    () -> assertEquals(5, lista1.capacidade()),
                    () -> assertEquals(20, lista2.capacidade()),
                    () -> assertEquals(1, lista3.capacidade()),
                    () -> assertTrue(lista1.estaVazia()),
                    () -> assertTrue(lista2.estaVazia()),
                    () -> assertTrue(lista3.estaVazia())
                );
            }
        }

        @Nested
        @DisplayName("[Método Construtor Com Capacidade] - Testes de Invariantes")
        class TestesInvariantes {
            
            @Test
            @DisplayName("Invariante: capacidade especificada deve ser preservada")
            @Tag("Essencial")
            void invarianteCapacidadePreservada() throws Exception {
                // Arrange & Act
                int capacidadeEsperada = 25;
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(capacidadeEsperada);
                
                // Assert - Verificar campo interno
                Field campoCapacidade = ListaSequencialCapacidadeVariavel.class.getDeclaredField("capacidade");
                campoCapacidade.setAccessible(true);
                int capacidadeInterna = (int) campoCapacidade.get(lista);
                
                assertEquals(capacidadeEsperada, capacidadeInterna);
                assertEquals(capacidadeEsperada, lista.capacidade());
            }
            
            @Test
            @DisplayName("Invariante: lista interna deve ter mesma capacidade")
            void invarianteListaInternaMesmaCapacidade() throws Exception {
                // Arrange
                int capacidade = 8;
                
                // Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(capacidade);
                
                // Assert - Verificar lista interna
                Field campoLista = ListaSequencialCapacidadeVariavel.class.getDeclaredField("lista");
                campoLista.setAccessible(true);
                ListaSequencial<?> listaInterna = (ListaSequencial<?>) campoLista.get(lista);
                
                // A lista interna deve estar preparada para a capacidade especificada
                assertTrue(listaInterna.tamanho() <= capacidade);
            }
            
            @Test
            @DisplayName("Invariante: estado consistente para capacidade zero")
            void invarianteEstadoConsistenteCapacidadeZero() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(0);
                
                // Assert
                assertAll(
                    () -> assertEquals(0, lista.capacidade()),
                    () -> assertEquals(0, lista.tamanho()),
                    () -> assertTrue(lista.estaVazia()),
                    () -> assertTrue(lista.tamanho() <= lista.capacidade())
                );
            }
        }

        @Nested
        @DisplayName("[Método Construtor Com Capacidade] - Testes de Sequências")
        class TestesSequencias {
            
            @Test
            @DisplayName("Sequência: criação capacidade específica → operações → redimensionamento")
            @Tag("Essencial")
            void sequenciaCriacaoOperacoesRedimensionamento() {
                // Arrange
                int capacidadeInicial = 3;
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(capacidadeInicial);
                
                // Act - Inserir até forçar redimensionamento
                lista.inserir(10, 0);
                lista.inserir(20, 1);
                lista.inserir(30, 2);
                int capacidadeAntes = lista.capacidade();
                
                lista.inserir(40, 3); // Deve redimensionar
                int capacidadeDepois = lista.capacidade();
                
                // Assert
                assertAll(
                    () -> assertEquals(capacidadeInicial, capacidadeAntes),
                    () -> assertTrue(capacidadeDepois > capacidadeInicial, "Deve ter redimensionado"),
                    () -> assertEquals(4, lista.tamanho()),
                    () -> assertEquals(40, lista.obter(3))
                );
            }
            
            @Test
            @DisplayName("Sequência: criação capacidade zero → inserção → redimensionamento")
            void sequenciaCapacidadeZeroRedimensionamento() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(0);
                
                // Act - Primeira inserção deve redimensionar de 0 para 1
                lista.inserir(100, 0);
                
                // Assert
                assertAll(
                    () -> assertTrue(lista.capacidade() > 0, "Deve ter redimensionado de zero"),
                    () -> assertEquals(1, lista.tamanho()),
                    () -> assertEquals(100, lista.obter(0))
                );
            }
            
            @Test
            @DisplayName("Sequência: criação capacidade específica → limpeza → capacidade mantida")
            void sequenciaCriacaoLimpezaCapacidadeMantida() {
                // Arrange
                int capacidadeOriginal = 15;
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(capacidadeOriginal);
                
                // Act - Adicionar elementos e depois limpar
                lista.inserir(1, 0);
                lista.inserir(2, 1);
                lista.limpar();
                
                // Assert - Capacidade deve voltar ao padrão após limpeza
                assertEquals(10, lista.capacidade()); // CAPACIDADE_PADRAO = 10
                assertTrue(lista.estaVazia());
            }
        
        }

        @Nested
        @DisplayName("[Método Construtor Com Capacidade] - Testes de Integração")
        class TestesIntegracao {
            
            @Test
            @DisplayName("Integração: diferentes capacidades com diferentes tipos")
            void integracaoDiferentesCapacidadesTipos() {
                // Teste com Integer
                ListaSequencialCapacidadeVariavel<Integer> listaInt = new ListaSequencialCapacidadeVariavel<>(5);
                listaInt.inserir(100, 0);
                
                // Teste com String
                ListaSequencialCapacidadeVariavel<String> listaStr = new ListaSequencialCapacidadeVariavel<>(10);
                listaStr.inserir("teste", 0);
                
                // Teste com capacidade mínima
                ListaSequencialCapacidadeVariavel<Double> listaDbl = new ListaSequencialCapacidadeVariavel<>(1);
                listaDbl.inserir(3.14, 0);
                
                // Assert
                assertAll(
                    () -> assertEquals(5, listaInt.capacidade()),
                    () -> assertEquals(10, listaStr.capacidade()),
                    () -> assertEquals(1, listaDbl.capacidade()),
                    () -> assertEquals(100, listaInt.obter(0)),
                    () -> assertEquals("teste", listaStr.obter(0)),
                    () -> assertEquals(3.14, listaDbl.obter(0))
                );
            }
            
            @Test
            @DisplayName("Integração: capacidade específica com operações derivadas")
            void integracaoCapacidadeEspecificaOperacoesDerivadas() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(4);
                
                // Act - Usar operações derivadas
                lista.adicionarInicio(30);
                lista.adicionarInicio(20);
                lista.adicionarInicio(10);
                lista.removerFim();
                lista.remover(Integer.valueOf(20));
                System.out.println(lista.capacidade());
                
                // Assert
                assertAll(
                    () -> assertEquals(4, lista.capacidade()),
                    () -> assertEquals(1, lista.tamanho()),
                    () -> assertEquals(10, lista.obter(0))
                );
            }
        }

    }

    @Nested
    @DisplayName("[Testes] Método Capacidade")
    class TestesMetodoCapacidade {

        @Nested
        @DisplayName("[Método Capacidade] - Testes de Funcionalidade Básica")
        class TestesFuncionalidadeBasica {

            @Test
            @DisplayName("Deve retornar capacidade padrão para construtor vazio")
            void deveRetornarCapacidadePadrao() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>();
                
                // Act
                int capacidade = lista.capacidade();
                
                // Assert
                assertEquals(10, capacidade); // CAPACIDADE_PADRAO = 10
            }
            
            @Test
            @DisplayName("Deve retornar capacidade específica do construtor")
            void deveRetornarCapacidadeEspecifica() {
                // Arrange
                int capacidadeEsperada = 25;
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(capacidadeEsperada);
                
                // Act
                int capacidade = lista.capacidade();
                
                // Assert
                assertEquals(capacidadeEsperada, capacidade);
            }
            
            @Test
            @DisplayName("Deve retornar capacidade zero quando criada com zero")
            void deveRetornarCapacidadeZero() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(0);
                
                // Act & Assert
                assertEquals(0, lista.capacidade());
            }

        }

        @Nested
        @DisplayName("[Método Capacidade] - Testes de Edge Cases")
        class TestesEdgeCases {
            
            @Test
            @DisplayName("Deve retornar capacidade após múltiplas instâncias")
            void deveRetornarCapacidadeMultiplasInstancias() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista1 = new ListaSequencialCapacidadeVariavel<>(5);
                ListaSequencialCapacidadeVariavel<String> lista2 = new ListaSequencialCapacidadeVariavel<>(15);
                ListaSequencialCapacidadeVariavel<Double> lista3 = new ListaSequencialCapacidadeVariavel<>(0);
                
                // Act & Assert
                assertAll(
                    () -> assertEquals(5, lista1.capacidade()),
                    () -> assertEquals(15, lista2.capacidade()),
                    () -> assertEquals(0, lista3.capacidade())
                );
            }
            
            @Test
            @DisplayName("Deve retornar capacidade grande corretamente")
            void deveRetornarCapacidadeGrande() {
                // Arrange
                int capacidadeGrande = 10000;
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(capacidadeGrande);
                
                // Act & Assert
                assertEquals(capacidadeGrande, lista.capacidade());
            }
        }

        @Nested
        @DisplayName("[Método Capacidade] - Testes de Invariantes")
        class TestesInvariantes {
            
            @Test
            @DisplayName("Invariante: capacidade nunca negativa")
            void invarianteCapacidadeNuncaNegativa() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(10);
                
                // Act & Assert
                assertTrue(lista.capacidade() >= 0, "Capacidade nunca deve ser negativa");
            }
            
            @Test
            @DisplayName("Invariante: capacidade sempre ≥ tamanho")
            void invarianteCapacidadeMaiorOuIgualTamanho() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(5);
                
                // Act - Adicionar elementos
                lista.inserir(1, 0);
                lista.inserir(2, 1);
                lista.inserir(3, 2);
                
                // Assert
                assertTrue(lista.capacidade() >= lista.tamanho(), 
                    "Capacidade deve ser sempre maior ou igual ao tamanho");
            }
            
            @Test
            @DisplayName("Invariante: capacidade mantém consistência após múltiplas operações")
            void invarianteCapacidadeConsistente() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(3);
                int capacidadeInicial = lista.capacidade();
                
                // Act - Sequência de operações
                lista.inserir(10, 0);
                lista.inserir(20, 1);
                lista.inserir(30, 2);
                lista.inserir(40, 3); // Força redimensionamento
                
                int capacidadeAposExpansao = lista.capacidade();
                
                lista.remover(0);
                lista.remover(0);
                lista.remover(0); // Força redução
                
                int capacidadeAposReducao = lista.capacidade();
                
                // Assert
                assertAll(
                    () -> assertTrue(capacidadeAposExpansao > capacidadeInicial),
                    () -> assertTrue(capacidadeAposReducao <= capacidadeAposExpansao),
                    () -> assertTrue(capacidadeAposReducao >= lista.tamanho())
                );
            }
        }

        @Nested
        @DisplayName("[Método Capacidade] -Testes de Sequências")
        class TestesSequencias {
            
            @Test
            @DisplayName("Sequência: capacidade durante expansão automática")
            void sequenciaCapacidadeDuranteExpansao() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(2);
                int capacidadeInicial = lista.capacidade();
                
                // Act - Inserir até forçar múltiplas expansões
                lista.inserir(1, 0);
                int capacidadeAposPrimeira = lista.capacidade();
                
                lista.inserir(2, 1);
                int capacidadeAposSegunda = lista.capacidade();
                
                lista.inserir(3, 2); // Força primeira expansão
                int capacidadeAposExpansao1 = lista.capacidade();
                
                // Inserir mais para forçar nova expansão
                for (int i = 3; i <= 10; i++) {
                    lista.inserir(i, i);
                }
                int capacidadeAposExpansao2 = lista.capacidade();
                
                // Assert
                assertAll(
                    () -> assertEquals(2, capacidadeInicial),
                    () -> assertEquals(2, capacidadeAposPrimeira),
                    () -> assertEquals(2, capacidadeAposSegunda),
                    () -> assertTrue(capacidadeAposExpansao1 > capacidadeInicial),
                    () -> assertTrue(capacidadeAposExpansao2 > capacidadeAposExpansao1)
                );
            }
            
            @Test
            @DisplayName("Sequência: capacidade durante redução automática")
            void sequenciaCapacidadeDuranteReducao() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(20);
                
                // Act - Expandir primeiro
                for (int i = 0; i < 15; i++) {
                    lista.inserir(i, i);
                }
                int capacidadeExpandida = lista.capacidade();
                
                // Remover para forçar redução
                for (int i = 0; i < 12; i++) {
                    lista.remover(0);
                }
                int capacidadeAposReducao = lista.capacidade();
                
                // Assert
                assertAll(
                    () -> assertTrue(capacidadeExpandida >= 20),
                    () -> assertTrue(capacidadeAposReducao < capacidadeExpandida),
                    () -> assertTrue(capacidadeAposReducao >= lista.tamanho())
                );
            }
            
            @Test
            @DisplayName("Sequência: capacidade após limpeza")
            void sequenciaCapacidadeAposLimpeza() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(25);
                
                // Act - Adicionar elementos e depois limpar
                lista.inserir(1, 0);
                lista.inserir(2, 1);
                lista.inserir(3, 2);
                int capacidadeAntesLimpeza = lista.capacidade();
                
                lista.limpar();
                int capacidadeAposLimpeza = lista.capacidade();
                
                // Assert
                assertAll(
                    () -> assertEquals(25, capacidadeAntesLimpeza),
                    () -> assertEquals(10, capacidadeAposLimpeza), // Volta para CAPACIDADE_PADRAO
                    () -> assertEquals(0, lista.tamanho()),
                    () -> assertTrue(lista.estaVazia())
                );
            }
       
        }

        @Nested
        @DisplayName("Método Capacidade] - Testes de Cenários Intermediários")
        class TestesCenariosIntermediarios {
            
            @Test
            @DisplayName("Deve retornar capacidade correta após operações mistas")
            void capacidadeAposOperacoesMistas() {
                // Arrange
                ListaSequencialCapacidadeVariavel<Integer> lista = new ListaSequencialCapacidadeVariavel<>(5);
                
                // Act - Operações mistas
                lista.adicionarInicio(10);
                lista.inserir(20, 1);
                lista.inserir(30, 2);
                lista.remover(Integer.valueOf(20));
                lista.atualizar(1, 40);
                
                int capacidade = lista.capacidade();
                int tamanho = lista.tamanho();
                
                // Assert
                assertAll(
                    () -> assertTrue(capacidade >= tamanho),
                    () -> assertEquals(5, capacidade), // Não expandiu ainda
                    () -> assertEquals(2, tamanho)
                );
            }
            
            @Test
            @DisplayName("Deve manter capacidade consistente com diferentes tipos")
            void capacidadeConsistenteDiferentesTipos() {
                // Arrange & Act
                ListaSequencialCapacidadeVariavel<Integer> listaInt = new ListaSequencialCapacidadeVariavel<>(8);
                ListaSequencialCapacidadeVariavel<String> listaStr = new ListaSequencialCapacidadeVariavel<>(12);
                ListaSequencialCapacidadeVariavel<Object> listaObj = new ListaSequencialCapacidadeVariavel<>(4);
                
                // Assert
                assertAll(
                    () -> assertEquals(8, listaInt.capacidade()),
                    () -> assertEquals(12, listaStr.capacidade()),
                    () -> assertEquals(4, listaObj.capacidade())
                );
            }
        }

    }

}
