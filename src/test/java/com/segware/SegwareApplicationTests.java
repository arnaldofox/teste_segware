package com.segware;

import com.segware.model.PostModel;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SegwareApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SegwareApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllPosts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/posts", HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetPostById() {
        int id = 1;
        PostModel post = restTemplate.getForObject(getRootUrl() + "/api/v1/post/" + id, PostModel.class);
        assertNotNull(post.getDescricao());
    }

    @Test
    public void testCreatePost() {
        PostModel post = new PostModel();
        post.setDescricao("Post 001");

        ResponseEntity<PostModel> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/createpost", post, PostModel.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpVotePost() {
        int id = 1;
        PostModel post = restTemplate.getForObject(getRootUrl() + "/api/v1/upVote/" + id, PostModel.class);
        assertNotNull(post.getUpVote());
    }

    @Test
    public void testDownVotePost() {
        int id = 1;
        PostModel post = restTemplate.getForObject(getRootUrl() + "/api/v1/downVote/" + id, PostModel.class);
        assertNotNull(post.getDownVote());
    }

}
