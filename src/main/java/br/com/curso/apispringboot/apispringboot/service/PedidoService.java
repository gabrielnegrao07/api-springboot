package br.com.curso.apispringboot.apispringboot.service;

import br.com.curso.apispringboot.apispringboot.domain.ItemPedido;
import br.com.curso.apispringboot.apispringboot.domain.PagamentoComBoleto;
import br.com.curso.apispringboot.apispringboot.domain.Pedido;
import br.com.curso.apispringboot.apispringboot.domain.enums.EstadoPagamento;
import br.com.curso.apispringboot.apispringboot.repositories.ItemPedidoRepository;
import br.com.curso.apispringboot.apispringboot.repositories.PagamentoRepository;
import br.com.curso.apispringboot.apispringboot.repositories.PedidoRepository;
import br.com.curso.apispringboot.apispringboot.service.Exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    ProdutoService produtoService;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    @Autowired
    ClienteService clienteService;
    @Autowired
    EmailService emailService;

    public Pedido find(Integer id){
        return pedidoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido pedido){
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);
        if (pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());
        for (ItemPedido ip : pedido.getItens()){
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(pedido);
        }
        itemPedidoRepository.saveAll(pedido.getItens());
        emailService.sendOrderConfirmationEmail(pedido);
        return pedido;
    }
}
