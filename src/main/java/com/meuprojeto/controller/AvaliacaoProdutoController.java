package com.meuprojeto.controller;

import com.meuprojeto.model.AvaliacaoProduto;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.AvaliacaoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
public class AvaliacaoProdutoController {

    @Autowired
    private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

    @ResponseBody
    @PostMapping(value = "**/salvaAvaliacaoProduto")
    public ResponseEntity<AvaliacaoProduto> salvaAvaliacaoProduto(@RequestBody @Valid AvaliacaoProduto avaliacaoProduto) throws ExcecaoMsgErro{

        if (avaliacaoProduto.getEmpresa() == null || (avaliacaoProduto.getEmpresa() != null && avaliacaoProduto.getEmpresa().getId() <= 0)) {
            throw new ExcecaoMsgErro("Informa a empresa dona do registro");
        }


        if(avaliacaoProduto.getProduto() == null || (avaliacaoProduto.getProduto() != null && avaliacaoProduto.getProduto().getId() <= 0)) {
            throw new ExcecaoMsgErro("A avaliação deve conter o produto associado.");
        }


        if(avaliacaoProduto.getPessoa() == null || (avaliacaoProduto.getPessoa() != null && avaliacaoProduto.getPessoa().getId() <= 0)) {
            throw new ExcecaoMsgErro("A avaliação deve conter a pessoa ou cliente associado.");
        }

        avaliacaoProduto = avaliacaoProdutoRepository.saveAndFlush(avaliacaoProduto);

        return new ResponseEntity<AvaliacaoProduto>(avaliacaoProduto, HttpStatus.OK);

    }



    @ResponseBody
    @DeleteMapping(value = "**/deleteAvalicaoPessoa/{idAvaliacao}")
    public ResponseEntity<?> deleteAvalicaoPessoa(@PathVariable("idAvaliacao") Long idAvaliacao) {

        avaliacaoProdutoRepository.deleteById(idAvaliacao);

        return new ResponseEntity<String>("Avaliacao Removida", HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/avaliacaoProduto/{idProduto}")
    public ResponseEntity<List<AvaliacaoProduto>> avaliacaoProduto(@PathVariable("idProduto") Long idProduto) {

        List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProduto(idProduto);

        return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/avaliacaoPessoa/{idPessoa}")
    public ResponseEntity<List<AvaliacaoProduto>> avaliacaoPessoa(@PathVariable("idPessoa") Long idPessoa) {

        List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoPessoa(idPessoa);

        return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/avaliacaoProdutoPessoa/{idProduto}/{idPessoa}")
    public ResponseEntity<List<AvaliacaoProduto>>
    avaliacaoProdutoPessoa(@PathVariable("idProduto") Long idProduto, @PathVariable("idPessoa") Long idPessoa) {

        List<AvaliacaoProduto> avaliacaoProdutos = avaliacaoProdutoRepository.avaliacaoProdutoPessoa(idProduto, idPessoa);

        return new ResponseEntity<List<AvaliacaoProduto>>(avaliacaoProdutos, HttpStatus.OK);
    }


}
