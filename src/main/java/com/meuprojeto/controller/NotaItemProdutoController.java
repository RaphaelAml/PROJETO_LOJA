package com.meuprojeto.controller;

import java.util.List;


import javax.validation.Valid;


import com.meuprojeto.model.NotaItemProduto;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.NotaItemProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NotaItemProdutoController {

    @Autowired
    private NotaItemProdutoRepository notaItemProdutoRepository;


    @ResponseBody
    @PostMapping(value = "**/salvarNotaItemProduto")
    public ResponseEntity<NotaItemProduto>
    salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto)
            throws ExcecaoMsgErro {


        if (notaItemProduto.getId() == null) {

            if (notaItemProduto.getProduto() == null || notaItemProduto.getProduto().getId() <= 0) {
                throw new ExcecaoMsgErro("O produto deve ser informado.");
            }


            if (notaItemProduto.getNotaFiscalCompra() == null || notaItemProduto.getNotaFiscalCompra().getId() <= 0) {
                throw new ExcecaoMsgErro("A nota fisca deve ser informada.");
            }


            if (notaItemProduto.getEmpresa() == null || notaItemProduto.getEmpresa().getId() <= 0) {
                throw new ExcecaoMsgErro("A empresa deve ser informada.");
            }

            List<NotaItemProduto> notaExistente = notaItemProdutoRepository.
                    buscaNotaItemPorProdutoNota(notaItemProduto.getProduto().getId(),
                            notaItemProduto.getNotaFiscalCompra().getId());

            if (!notaExistente.isEmpty()) {
                throw new ExcecaoMsgErro("Já existe este produto cadastrado para esta nota.");
            }

        }

        if (notaItemProduto.getQuantidade() <= 0) {
            throw new ExcecaoMsgErro("A quantidade do produto deve ser informada.");
        }


        NotaItemProduto notaItemSalva = notaItemProdutoRepository.save(notaItemProduto);

        notaItemSalva = notaItemProdutoRepository.findById(notaItemProduto.getId()).get();

        return new ResponseEntity<NotaItemProduto>(notaItemSalva, HttpStatus.OK);


    }

    @ResponseBody
    @DeleteMapping(value = "**/deleteNotaItemPorId/{id}")
    public ResponseEntity<?> deleteNotaItemPorId(@PathVariable("id") Long id) {


        notaItemProdutoRepository.deleteById(id);

        return new ResponseEntity("Nota Item Produto Removido",HttpStatus.OK);
    }



}