package com.renan.estoquepreco.controller;

import com.renan.estoquepreco.conections.RabbitMQConection;
import com.renan.estoquepreco.constantes.RabbitMQConstantes;
import com.renan.estoquepreco.dto.EstoqueDTO;
import com.renan.estoquepreco.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {
    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alteraEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
