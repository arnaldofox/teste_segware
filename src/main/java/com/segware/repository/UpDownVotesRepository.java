package com.segware.repository;

import com.segware.model.PostModel;
import com.segware.model.TipoUpvotes;
import com.segware.model.UpDownVotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade UpDownVotes
 *
 * @author Arnaldo
 */
@Repository
public interface UpDownVotesRepository extends JpaRepository<UpDownVotes, Long> {

    @Query("select count(*) from UpDownVotes where post = :post and tipoUpvotes = :tipo ")
    public Long countUpDownVote(@Param("post") PostModel postModel, @Param("tipo") TipoUpvotes tipoUpvotes);

}
