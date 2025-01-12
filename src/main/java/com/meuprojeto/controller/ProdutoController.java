package com.meuprojeto.controller;

import com.meuprojeto.model.Produto;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository  produtoRepository;

    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/salvarProduto") /*Mapeando a url para receber JSON*/
    public ResponseEntity<Produto> salvarAcesso(@RequestBody @Valid Produto produto) throws ExcecaoMsgErro { /*Recebe o JSON e converte pra Objeto*/

        if (produto.getEmpresa() == null || produto.getEmpresa().getId() <= 0) {
            throw new ExcecaoMsgErro("Empresa responsável deve ser informada");
        }

        if (produto.getId() == null) {
            List<Produto> produtos  = produtoRepository.buscarProdutoNome(produto.getNome().toUpperCase(), produto.getEmpresa().getId());

            if (!produtos.isEmpty()) {
                throw new ExcecaoMsgErro("Já existe Produto com a descrição: " + produto.getNome());
            }
        }


        if (produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId() <= 0) {
            throw new ExcecaoMsgErro("Categoria deve ser informada");
        }


        if (produto.getMarcaProduto() == null || produto.getMarcaProduto().getId() <= 0) {
            throw new ExcecaoMsgErro("Marca deve ser informada");
        }

        Produto produtoSalvo = produtoRepository.save(produto);

        return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
    }



    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/deleteProduto") /*Mapeando a url para receber JSON*/
    public ResponseEntity<String> deleteProduto(@RequestBody Produto produto) { /*Recebe o JSON e converte pra Objeto*/

        produtoRepository.deleteById(produto.getId());

        return new ResponseEntity<String>("Produto Removido", HttpStatus.OK);
    }


    //@Secured({ "ROLE_GERENTE", "ROLE_ADMIN" })
    @ResponseBody
    @DeleteMapping(value = "**/deleteProdutoPorId/{id}")
    public ResponseEntity<String> deleteProdutoPorId(@PathVariable("id") Long id) {


        produtoRepository.deleteById(id);

        return new ResponseEntity<String>("Produto Removido", HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/obterProduto/{id}")
    public ResponseEntity<Produto> obterAcesso(@PathVariable("id") Long id) throws ExcecaoMsgErro {

        Produto produto = produtoRepository.findById(id).orElse(null);

        if (produto == null) {
            throw new ExcecaoMsgErro("Não encontrou Produto com código: " + id);
        }

        return new ResponseEntity<Produto>(produto,HttpStatus.OK);
    }



    @ResponseBody
    @GetMapping(value = "**/buscarProdNome/{desc}")
    public ResponseEntity<List<Produto>> buscarProdNome(@PathVariable("desc") String desc) {

        List<Produto> acesso = produtoRepository.buscarProdutoNome(desc.toUpperCase());

        return new ResponseEntity<List<Produto>>(acesso,HttpStatus.OK);
    }

}