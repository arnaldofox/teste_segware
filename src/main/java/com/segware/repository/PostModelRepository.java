package com.segware.repository;

import com.segware.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade PostModel
 *
 * @author Arnaldo
 */
@Repository
public interface PostModelRepository extends JpaRepository<PostModel, Long> {

}
