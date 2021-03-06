package com.antipragas.services;

import com.antipragas.models.Usuario;

import java.util.List;

/**
 * @author Thais Camacho
 */

public interface UsuarioService {
    Usuario findByEmail(String email);
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario create(Usuario usuario);
    Usuario edit(Usuario usuario);
    void deleteById(Long id);
}
