package com.meuprojeto.controller;


import com.meuprojeto.dto.ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO;
import  com.meuprojeto.model.NotaFiscalCompra;
import com.meuprojeto.model.NotaFiscalVenda;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.NotaFiscalCompraRepository;
import com.meuprojeto.repository.NotaFiscalVendaRepository;
import com.meuprojeto.service.NotaFiscalCompraService;
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

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;


@RestController
public class NotaFiscalCompraController {

    @Autowired
    private NotaFiscalCompraRepository notaFiscalCompraRepository;
    @Autowired
    private NotaFiscalVendaRepository notaFiscalVendaRepository;

    @Autowired
    private NotaFiscalCompraService notaFiscalCompraService;

    @ResponseBody
    @PostMapping(value = "**/relatorioProdutoCompradoNotaFiscal")
    public ResponseEntity<List<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO>>
    relatorioProdutoCompradoNotaFiscal(@Valid @RequestBody ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO
                                                                        objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO){

        List<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO> retorno = new
                ArrayList<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO>();


        retorno = notaFiscalCompraService.gerarRelatorioProdutoCompraNota(objetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO);


        return new ResponseEntity<List<ObjetoRequisicaoRelatorioProdutoCompraNotaFiscalDTO>>(retorno, HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping(value = "**/salvarNotaFiscalCompra")
    public ResponseEntity<NotaFiscalCompra> salvarNotaFiscalCompra(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws ExcecaoMsgErro { /*Recebe o JSON e converte pra Objeto*/

        if (notaFiscalCompra.getId() == null) {

            if (notaFiscalCompra.getDescricaoObs() != null) {
                boolean existe = notaFiscalCompraRepository.existeNotaComDescricao(notaFiscalCompra.getDescricaoObs().toUpperCase().trim());

                if(existe) {
                    throw new ExcecaoMsgErro("Já existe Nota de compra com essa mesma descrição : " + notaFiscalCompra.getDescricaoObs());
                }
            }


        }

        if (notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() <= 0) {
            throw new ExcecaoMsgErro("A Pessoa Juridica da nota fiscal deve ser informada.");
        }


        if (notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() <= 0) {
            throw new ExcecaoMsgErro("A empresa responsável deve ser informada.");
        }


        if (notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() <= 0) {
            throw new ExcecaoMsgErro("A conta a pagar da nota deve ser informada.");
        }


        NotaFiscalCompra notaFiscalCompraSalva = notaFiscalCompraRepository.save(notaFiscalCompra);

        return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompraSalva, HttpStatus.OK);
    }



    @ResponseBody
    @DeleteMapping(value = "**/deleteNotaFiscalCompraPorId/{id}")
    public ResponseEntity<?> deleteNotaFiscalCompraPorId(@PathVariable("id") Long id) {


        notaFiscalCompraRepository.deleteItemNotaFiscalCompra(id);/*Delete os filhos*/
        notaFiscalCompraRepository.deleteById(id); /*Deleta o pai*/

        return new ResponseEntity("Nota Fiscal Compra Removida",HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/obterNotaFiscalCompra/{id}")
    public ResponseEntity<NotaFiscalCompra> obterNotaFiscalCompra(@PathVariable("id") Long id) throws ExcecaoMsgErro {

        NotaFiscalCompra notaFiscalCompra = notaFiscalCompraRepository.findById(id).orElse(null);

        if (notaFiscalCompra == null) {
            throw new ExcecaoMsgErro("Não encontrou Nota Fiscal com código: " + id);
        }

        return new ResponseEntity<NotaFiscalCompra>(notaFiscalCompra, HttpStatus.OK);
    }


    @ResponseBody
    @GetMapping(value = "**/obterNotaFiscalCompraDaVenda/{idvenda}")
    public ResponseEntity<List<NotaFiscalVenda>> obterNotaFiscalCompraDaVenda(@PathVariable("idvenda") Long idvenda)
            throws ExcecaoMsgErro {

        List<NotaFiscalVenda> notaFiscalCompra = notaFiscalVendaRepository.buscarNotaPorVenda(idvenda);

        if (notaFiscalCompra == null) {
            throw new ExcecaoMsgErro("Não encontrou Nota Fiscal de venda com código da venda: " + idvenda);
        }

        return new ResponseEntity<List<NotaFiscalVenda>>(notaFiscalCompra, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "**/obterNotaFiscalCompraDaVendaUnico/{idvenda}")
    public ResponseEntity<NotaFiscalVenda> obterNotaFiscalCompraDaVendaUnico(@PathVariable("idvenda") Long idvenda)
            throws ExcecaoMsgErro {

        NotaFiscalVenda notaFiscalCompra = notaFiscalVendaRepository.buscarNotaPorVendaUnica(idvenda);

        if (notaFiscalCompra == null) {
            throw new ExcecaoMsgErro("Não encontrou Nota Fiscal de venda com código da venda: " + idvenda);
        }

        return new ResponseEntity<NotaFiscalVenda>(notaFiscalCompra, HttpStatus.OK);
    }




    @ResponseBody
    @GetMapping(value = "**/buscarNotaFiscalPorDesc/{desc}")
    public ResponseEntity<List<NotaFiscalCompra>> buscarNotaFiscalPorDesc(@PathVariable("desc") String desc) {

        List<NotaFiscalCompra>  notaFiscalCompras = notaFiscalCompraRepository.buscaNotaDesc(desc.toUpperCase().trim());

        return new ResponseEntity<List<NotaFiscalCompra>>(notaFiscalCompras,HttpStatus.OK);
    }




}