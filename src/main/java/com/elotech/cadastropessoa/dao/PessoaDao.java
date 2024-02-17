package com.elotech.cadastropessoa.dao;

import com.elotech.cadastropessoa.model.PessoaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaDao {

    @PersistenceContext
    private EntityManager em;

    public List<PessoaModel> listarTodasPessoas() {
        return em.createQuery("SELECT p FROM PessoaModel p ORDER BY nome ASC").getResultList();
    }

    public Object buscarPessoaPorId(Long idPessoa) {
        return em.createQuery("SELECT p FROM PessoaModel p " +
                "WHERE id = " + idPessoa).getSingleResult();
    }

    @Transactional
    public void inserirPessoa(PessoaModel pessoa) {
        Query query = em.createNativeQuery("INSERT INTO Pessoa (nome, cpf, datanascimento, email, senha) " +
                " VALUES (:nome, :cpf, :datanascimento, :email, :senha)");

        query.setParameter("nome", pessoa.getNome());
        query.setParameter("cpf", pessoa.getCpf());
        query.setParameter("datanascimento", pessoa.getDatanascimento());
        query.setParameter("email", pessoa.getEmail());
        query.setParameter("senha", pessoa.getSenha());

        query.executeUpdate();
    }

    @Transactional
    public void atualizarPessoa(PessoaModel pessoa) {
        Query query = em.createNativeQuery("UPDATE Pessoa SET nome = :nome, cpf = :cpf, " +
                "datanascimento= :datanascimento WHERE id = :id");

        query.setParameter("id", pessoa.getId());
        query.setParameter("nome", pessoa.getNome());
        query.setParameter("cpf", pessoa.getCpf());
        query.setParameter("datanascimento", pessoa.getDatanascimento());

        query.executeUpdate();
    }

    @Transactional
    public void excluirPessoa(Long idPessoa) {
        Query query = em.createQuery("DELETE FROM PessoaModel WHERE id = :idPessoa");

        query.setParameter("idPessoa", idPessoa);

        query.executeUpdate();
    }
}
