package by.grodno.pvt.site.webappsample.service;

import by.grodno.pvt.site.webappsample.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    List<Post> getPosts();

    Post getPost(Integer id);

    void addPost(List<Post> posts);

    void savePost(Post post);

    void deletePost(Integer number);

    List<Post> findByExample(Post userSample);

    Page<Post> getPage(Integer pageNum, Integer pageSize);

    List<Post> findTitle(String ftitlee);
}
