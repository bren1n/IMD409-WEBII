package com.jeanlima.springrestapiapp.rest.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jeanlima.springrestapiapp.helpers.patchHandler;
import com.jeanlima.springrestapiapp.model.ItemPedido;
import com.jeanlima.springrestapiapp.model.Pedido;
import com.jeanlima.springrestapiapp.rest.dto.InformacaoClientePedidoDTO;
import com.jeanlima.springrestapiapp.rest.dto.InformacaoItemPedidoDTO;
import com.jeanlima.springrestapiapp.rest.dto.InformacoesPedidoReducedDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapiapp.model.Cliente;
import com.jeanlima.springrestapiapp.repository.ClienteRepository;



@RequestMapping("/api/clientes")
@RestController //anotação especializadas de controller - todos já anotados com response body!
public class ClienteController {

    @Autowired
    private ClienteRepository clientes;

    @GetMapping("{id}")
    public Cliente getClienteById( @PathVariable Integer id ){
        return clientes
                .findById(id)
                .orElseThrow(() -> //se nao achar lança o erro!
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    private InformacaoClientePedidoDTO converterClienteParaClientePedido(Cliente cliente) {
        return InformacaoClientePedidoDTO.
                builder().
                id(cliente.getId()).
                nome(cliente.getNome()).
                cpf(cliente.getCpf()).
                pedidos(converterPedidoParaPedidoReduced(cliente.getPedidos())).build();
    }

    private List<InformacoesPedidoReducedDTO> converterPedidoParaPedidoReduced(Set<Pedido> pedidos) {
        if(CollectionUtils.isEmpty(pedidos)){
            return Collections.emptyList();
        }

        return pedidos.stream().map(
                pedido -> InformacoesPedidoReducedDTO
                        .builder()
                        .id(pedido.getId())
                        .valorTotal(pedido.getTotal())
                        .itens(converter(pedido.getItens()))
                        .build()
        ).collect(Collectors.toList());
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

    @GetMapping("{id}/pedidos")
    public InformacaoClientePedidoDTO getClienteWithPedidosById(@PathVariable Integer id) {
        return clientes
                .findById(id).map(c -> converterClienteParaClientePedido(c))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save( @RequestBody Cliente cliente ){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){
        clientes.findById(id)
                .map( cliente -> {
                    clientes.delete(cliente );
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado") );

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id,
                        @RequestBody Cliente cliente ){
        clientes
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cliente não encontrado") );
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void partialUpdate(@PathVariable Integer id, @RequestBody Cliente cliente) {
        clientes.findById(id).map(c -> {
            BeanUtils.copyProperties(cliente, c, patchHandler.getNullProperties(cliente));
            clientes.save(c);
            return c;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado."));
    }


    @GetMapping
    public List<Cliente> find( Cliente filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

}
