package com.segware.bo;

import com.segware.exception.ResourceNotFoundException;
import com.segware.model.PostModel;
import com.segware.model.TipoUpvotes;
import com.segware.model.UpDownVotes;
import com.segware.repository.PostModelRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.segware.repository.UpDownVotesRepository;
import org.springframework.util.StringUtils;

/**
 * Classe de regra de negócio para manipular o Post
 *
 * @author Arnaldo
 */
@Component
public class PostModelBO {

    @Autowired
    private PostModelRepository postRepository;
    @Autowired
    private UpDownVotesRepository upDownVotesRepository;

    public void setUpDownVote(PostModel post) {
        post.setUpVote(upDownVotesRepository.countUpDownVote(post, TipoUpvotes.UP));
        post.setDownVote(upDownVotesRepository.countUpDownVote(post, TipoUpvotes.DOWN));
    }

    public List<PostModel> getAllPosts() {

        List<PostModel> posts = postRepository.findAll();
        posts.forEach((post) -> {
            setUpDownVote(post);
        });

        return posts;
    }

    public PostModel getPostById(Long postId) throws ResourceNotFoundException {

        if (postId == null) {
            throw new ResourceNotFoundException("Parâmetro id é obrigatório");
        }
        PostModel post = null;
        post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new ResourceNotFoundException("Post não encontrado para o id: " + postId);
        }
        setUpDownVote(post);

        return post;
    }

    public PostModel save(PostModel post) throws ResourceNotFoundException {

        if (StringUtils.isEmpty(post.getDescricao())) {
            throw new ResourceNotFoundException("Descrição do Post é obrigatório");
        }

//        if (post.getDescricao().length() > 1200) {
//            throw new ResourceNotFoundException("Post permitido com tamanho máximo de 1200 caracteres");
//        }

        post.setDataHora(new Date());
        
        return postRepository.save(post);
    }

    public PostModel upVote(Long postId, String ip) throws ResourceNotFoundException {

        return upDownVote(postId, ip, TipoUpvotes.UP);
    }

    public PostModel downVote(Long postId, String ip) throws ResourceNotFoundException {

        return upDownVote(postId, ip, TipoUpvotes.DOWN);
    }

    public PostModel upDownVote(Long postId, String ip, TipoUpvotes tipoUpvotes) throws ResourceNotFoundException {

        if (postId == null) {
            throw new ResourceNotFoundException("Parâmetro id é obrigatório");
        }

        PostModel post = null;
        post = postRepository.findById(postId).orElse(null);
        if (post == null) {
            throw new ResourceNotFoundException("Post não encontrado para o id: " + postId);
        }

        UpDownVotes upDownVote = new UpDownVotes(new Date(), ip, post, tipoUpvotes);
        upDownVotesRepository.save(upDownVote);

        setUpDownVote(post);

        return post;
    }

}
