package com.jeanlima.springrestapiapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoClientePedidoDTO {
    private Integer id;
    private String nome;
    private String cpf;
    private List<InformacoesPedidoReducedDTO> pedidos;
}
