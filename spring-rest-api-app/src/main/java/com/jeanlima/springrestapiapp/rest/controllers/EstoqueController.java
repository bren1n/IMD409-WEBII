package com.jeanlima.springrestapiapp.rest.controllers;

import com.jeanlima.springrestapiapp.exception.PedidoNaoEncontradoException;
import com.jeanlima.springrestapiapp.exception.RegraNegocioException;
import com.jeanlima.springrestapiapp.model.Estoque;
import com.jeanlima.springrestapiapp.model.Produto;
import com.jeanlima.springrestapiapp.repository.EstoqueRepository;
import com.jeanlima.springrestapiapp.repository.ProdutoRepository;
import com.jeanlima.springrestapiapp.rest.dto.AtualizacaoQtdProdutoEstoque;
import com.jeanlima.springrestapiapp.rest.dto.ProdutoEstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Estoque> getAllEstoques(@RequestParam(required = false) String nomeProduto) {
        if (nomeProduto != null) {
            return estoqueRepository.findAllByProdutoNome(nomeProduto);
        }

        return estoqueRepository.findAll();
    }

    @GetMapping("{id}")
    public Estoque getEstoqueById(@PathVariable Integer id) {
        return estoqueRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estoque createEstoque(@RequestBody ProdutoEstoqueDTO dto) {
        Integer idProduto = dto.getProduto();
        Produto produto = produtoRepository
                .findById(idProduto)
                .orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));

        Estoque estoque = new Estoque(produto, dto.getQuantidade());
        return estoqueRepository.save(estoque);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEstoque(@PathVariable Integer id, @RequestBody ProdutoEstoqueDTO dto) {
        Integer idProduto = dto.getProduto();
        Produto produto = produtoRepository
                .findById(idProduto)
                .orElseThrow(() -> new RegraNegocioException("Código de produto inválido."));

        estoqueRepository.findById(id).map(e -> {
            e.setProduto(produto);
            e.setQuantidade(dto.getQuantidade());
            return estoqueRepository.save(e);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEstoque(@PathVariable Integer id) {
        estoqueRepository.findById(id).map(estoque -> {
            estoqueRepository.delete(estoque);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateQuantidadeProdutoEstoque(@PathVariable Integer id, @RequestBody AtualizacaoQtdProdutoEstoque dto) {
        estoqueRepository.findById(id).map(estoque -> {
            estoque.setQuantidade(dto.getQuantidadeProduto());
            return estoqueRepository.save(estoque);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado."));
    }
}
