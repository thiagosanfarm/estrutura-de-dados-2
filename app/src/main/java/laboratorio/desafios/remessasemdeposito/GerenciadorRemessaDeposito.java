package laboratorio.desafios.remessasemdeposito;

import java.util.LinkedHashMap;
import java.util.Map;

import estruturas.lineares.IFila;
import estruturas.lineares.IPilha;
import estruturas.lineares.dinamicas.fila.FilaComListaDuplamenteEncadeada;
import estruturas.lineares.dinamicas.pilha.PilhaComListaSimplesmenteEncadeada;

public class GerenciadorRemessaDeposito implements IGerenciadorRemessaDeposito {

    //Essa classe concrete gerencia todo o sistema de processamento do deposito.

    private final IFila<IItemEstoque[]> remessasRecebidas;
    private final Map<String, IPilha<IItemEstoque>> armazenamentoItemEstoque;

    public GerenciadorRemessaDeposito() {
        this.remessasRecebidas = new FilaComListaDuplamenteEncadeada<>();
        this.armazenamentoItemEstoque = new LinkedHashMap<>();
    }

    @Override
    public void receberRemessa(IItemEstoque[] remessa) {
        this.remessasRecebidas.enfileirar(remessa);
    }

    @Override
    public void processarTodasRemessas() {
        while (!this.remessasRecebidas.estaVazia()) {
            IItemEstoque[] remessa = this.remessasRecebidas.desenfileirar();
            processarUmaUnicaRemessa(remessa);
        }
    }

    @Override
    public void processarUmaUnicaRemessa(IItemEstoque[] remessa) {
        for (IItemEstoque novoItem : remessa) {
            String ID = novoItem.obterID();
            
            IPilha<IItemEstoque> armazenamentoAtual = this.armazenamentoItemEstoque.getOrDefault(ID, new PilhaComListaSimplesmenteEncadeada<>());
            
            IPilha<IItemEstoque> armazenamentoTemporario = new PilhaComListaSimplesmenteEncadeada<>();
            
            while (!armazenamentoAtual.estaVazia() && armazenamentoAtual.obterTopo().obterDataValidade().isBefore(novoItem.obterDataValidade())) {
                armazenamentoTemporario.empilhar(armazenamentoAtual.desempilhar());
            }
            
            armazenamentoAtual.empilhar(novoItem);
            
            while (!armazenamentoTemporario.estaVazia()) {
                armazenamentoAtual.empilhar(armazenamentoTemporario.desempilhar());
            }
            
            this.armazenamentoItemEstoque.put(ID, armazenamentoAtual);
        }
    }

    @Override
    public String obterStatusArmazenamento() {
        StringBuilder status = new StringBuilder();
        for (Map.Entry<String, IPilha<IItemEstoque>> entradas : this.armazenamentoItemEstoque.entrySet()) {
            status.append(entradas.getKey()).append(": ").append(entradas.getValue().imprimir()).append("\n");
        }
        return status.toString();
    }

    @Override
    public IItemEstoque recuperarProximoItemDaRemessa(String ID) {
        if (!this.armazenamentoItemEstoque.containsKey(ID) || this.armazenamentoItemEstoque.get(ID).estaVazia()) {
            return null;
        }
        return this.armazenamentoItemEstoque.get(ID).desempilhar();
    }

    @Override
    public boolean temRemessasRecebidasPendentesDeProcessamento() {
        return !this.remessasRecebidas.estaVazia();
    }

    
}
