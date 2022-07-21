package com.mtz.mtzfoods.domain.repository;

import com.mtz.mtzfoods.domain.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}
