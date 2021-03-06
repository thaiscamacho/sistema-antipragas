package com.antipragas.repositories;

import com.antipragas.models.Proposta;
import com.antipragas.models.Servico;
import com.antipragas.models.Usuario;
import com.antipragas.models.enums.StatusServico;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ricardo Henrique Brunetto & Rafael
 *         14 de Outubro de 2017
 */

@Repository
public interface ServicoRepository extends BaseServicoPrototypeRepository<Servico> {
    List<Servico> findAllByStatus(StatusServico statusServico);
    //Iterable<Servico> findByClienteAndStatus(Usuario cliente, StatusServico statusServico);
    List<Servico> findByClienteAndStatus(Usuario cliente, StatusServico statusServico);
    List<Servico> findByCliente(Usuario cliente);
    List<Servico> findByProposta(Proposta proposta);
    Servico findById(Long id);
}
