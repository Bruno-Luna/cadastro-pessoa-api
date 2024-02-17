package com.spring.cadastropessoa.dao;

import com.spring.cadastropessoa.model.ContatoModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContatoDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void inserirContatoPessoa(ContatoModel contato, Long idPessoa) {

        Query query = em.createNativeQuery("INSERT INTO Contato (nome, telefone, email, pessoa_id) " +
                " VALUES (:nome, :telefone, :email, :idPessoa)");

        query.setParameter("nome", contato.getNome());
        query.setParameter("telefone", contato.getTelefone());
        query.setParameter("email", contato.getEmail());
        query.setParameter("idPessoa", idPessoa);

        query.executeUpdate();
    }

    public List<ContatoModel> listarContatosPorIdPessoa(Long idPessoa) {
        return em.createQuery("SELECT c FROM ContatoModel c WHERE c.pessoa.id = " + idPessoa).getResultList();
    }

    public Object buscarContatoPorId(Long idContato) {
        return em.createQuery("SELECT c FROM ContatoModel c " +
                "WHERE id = " + idContato).getSingleResult();
    }

    @Transactional
    public void atualizarContatoPessoa(ContatoModel contato) {

        Query query = em.createNativeQuery("UPDATE Contato SET nome = :nome, email = :email, " +
                "telefone = :telefone WHERE id = :id");

        query.setParameter("id", contato.getId());
        query.setParameter("nome", contato.getNome());
        query.setParameter("telefone", contato.getTelefone());
        query.setParameter("email", contato.getEmail());

        query.executeUpdate();
    }

    @Transactional
    public void excluirContatoPessoa(Long idContato) {
        Query query = em.createQuery("DELETE FROM ContatoModel WHERE id = :idContato");

        query.setParameter("idContato", idContato);

        query.executeUpdate();
    }
}
