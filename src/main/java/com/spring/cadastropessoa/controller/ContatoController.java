package com.spring.cadastropessoa.controller;

import com.spring.cadastropessoa.dao.ContatoDao;
import com.spring.cadastropessoa.model.ContatoModel;
import com.spring.cadastropessoa.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contato")
public class ContatoController {
    @Autowired
    private ContatoDao contatoDao;

    @Autowired
    private ContatoService contatoService;

    @GetMapping("/{idPessoa}")
    public List<ContatoModel> listarContatoPessoa(@PathVariable(value = "idPessoa") Long idPessoa){
      return contatoDao.listarContatosPorIdPessoa(idPessoa);
    }

    @GetMapping("buscar/{idContato}")
    public Object buscarContatoId(@PathVariable(value = "idContato") Long idContato){
        return contatoDao.buscarContatoPorId(idContato);
    }
    @PostMapping("/{idPessoa}")
    public void cadastrarContatoPessoa(@PathVariable(value = "idPessoa") Long idPessoa, @Valid @RequestBody ContatoModel contato) {
       Boolean emailValido = contatoService.verificarEmail(contato.getEmail());

       if(!emailValido){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido.");
       } else {
           contatoDao.inserirContatoPessoa(contato, idPessoa);
       }
    }

    @PutMapping()
    public void atualizarContatoPessoa(@Valid @RequestBody ContatoModel contato) {
        Boolean emailValido = contatoService.verificarEmail(contato.getEmail());

        if(!emailValido){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido.");
        } else {
            contatoDao.atualizarContatoPessoa(contato);
        }
    }
    @DeleteMapping("/{id}")
    public void deletarContatoPessoa(@PathVariable(value = "id") Long id){
        contatoDao.excluirContatoPessoa(id);
    }
}
