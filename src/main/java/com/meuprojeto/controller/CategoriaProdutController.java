package com.meuprojeto.controller;

import com.meuprojeto.dto.CategoriaProdutoDTO;
import com.meuprojeto.model.Acesso;
import com.meuprojeto.model.CategoriaProduto;
import com.meuprojeto.projetoloja.ExcecaoMsgErro;
import com.meuprojeto.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaProdutController {

    @Autowired
    private CategoriaProdutoRepository categoriaProdutoRepository;

    @ResponseBody
    @GetMapping(value = "**/buscarPorDescCatgoria/{desc}")
    public ResponseEntity<List<CategoriaProduto>> buscarPorDesc(@PathVariable("desc") String desc) {

        List<CategoriaProduto> acesso = categoriaProdutoRepository.buscarCategoriaDes(desc.toUpperCase());

        return new ResponseEntity<List<CategoriaProduto>>(acesso,HttpStatus.OK);
    }



    @ResponseBody /*Poder dar um retorno da API*/
    @PostMapping(value = "**/deleteCategoria") /*Mapeando a url para receber JSON*/
    public ResponseEntity<?> deleteAcesso(@RequestBody CategoriaProduto categoriaProduto) { /*Recebe o JSON e converte pra Objeto*/

        if (categoriaProdutoRepository.findById(categoriaProduto.getId()).isPresent() == false) {
            return new ResponseEntity("Categoria já foi removida",HttpStatus.OK);
        }

        categoriaProdutoRepository.deleteById(categoriaProduto.getId());

        return new ResponseEntity("Categoria Removida",HttpStatus.OK);
    }


    @ResponseBody
    @PostMapping(value = "**/salvarCategoria")
    public ResponseEntity<CategoriaProdutoDTO> salvarCategoria(@RequestBody CategoriaProduto categoriaProduto) throws ExcecaoMsgErro{

        if (categoriaProduto.getEmpresa() == null || (categoriaProduto.getEmpresa().getId() == null)){
            throw new ExcecaoMsgErro("A empresa deve ser informada");
        }

        if (categoriaProduto.getId() == null && categoriaProdutoRepository.existeCategoria(categoriaProduto.getNomeDesc())){
            throw new ExcecaoMsgErro("Não pode cadastrar categoria com o mesmo nome.");
        }

        CategoriaProduto categoriaSalva = categoriaProdutoRepository.save(categoriaProduto);

        CategoriaProdutoDTO categoriaProdutoDTO = new CategoriaProdutoDTO();
        categoriaProdutoDTO.setId(categoriaSalva.getId());
        categoriaProdutoDTO.setNomeDesc(categoriaSalva.getNomeDesc());
        categoriaProdutoDTO.setEmpresa(categoriaSalva.getEmpresa().getId().toString());

        return new ResponseEntity<CategoriaProdutoDTO>(categoriaProdutoDTO, HttpStatus.OK);
    }
}
