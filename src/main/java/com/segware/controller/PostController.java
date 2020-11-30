package com.segware.controller;

import com.segware.bo.PostModelBO;
import com.segware.exception.ResourceNotFoundException;
import com.segware.model.PostModel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.segware.utils.WebUtils;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador dos Recursos da API
 *
 * @author Arnaldo
 */
@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PostModelBO postModelBO;

    @GetMapping("/posts")
    @ApiOperation(value = "Lista todos os posts")
    public List<PostModel> getAllPosts() {

        return postModelBO.getAllPosts();
    }

    @GetMapping("/post/{id}")
    @ApiOperation(value = "Buscar um post pelo Id")
    public ResponseEntity<PostModel> getPostById(@PathVariable(value = "id") Long postId) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(postModelBO.getPostById(postId));
    }

    @PostMapping("/createpost")
    @ApiOperation(value = "Adicionar um novo post")
    public PostModel createPost(@RequestBody PostModel post) throws ResourceNotFoundException {

        post.setIp(WebUtils.getIP(request));

        return postModelBO.save(post);
    }

    @PutMapping("/upVote/{id}")
    @ApiOperation(value = "Adicionar um UP voto")
    public ResponseEntity<PostModel> upVote(@PathVariable(value = "id") Long postId) throws ResourceNotFoundException {

        return ResponseEntity.ok(postModelBO.upVote(postId, WebUtils.getIP(request)));
    }

    @PutMapping("/downVote/{id}")
    @ApiOperation(value = "Adicionar um DOWN voto")
    public ResponseEntity<PostModel> downVote(@PathVariable(value = "id") Long postId) throws ResourceNotFoundException {

        return ResponseEntity.ok(postModelBO.downVote(postId, WebUtils.getIP(request)));
    }

}
