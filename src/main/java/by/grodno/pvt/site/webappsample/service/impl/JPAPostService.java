package by.grodno.pvt.site.webappsample.service.impl;

import by.grodno.pvt.site.webappsample.domain.Post;
import by.grodno.pvt.site.webappsample.repo.PostRepo;
import by.grodno.pvt.site.webappsample.service.PostService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class JPAPostService implements PostService, InitializingBean {
    @Autowired
    private PostRepo repo;

    @Override
    public List<Post> getPosts() {
        return repo.findAll();
    }

    @Override
    public Post getPost(Integer id) {
        return repo.getOne(id);
    }

    @Override
    public void addPost(List<Post> posts) {
        repo.saveAll(posts);

    }

    @Override
    public void savePost(Post post) {
        repo.save(post);

    }

    @Override
    public void deletePost(Integer number) {
        repo.deleteById(number);

    }

    @Override
    public List<Post> findByExample(Post postSample) {
        Example<Post> exp = Example.of(postSample,
                ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repo.findAll(exp);
    }

    @Override
    public Page<Post> getPage(Integer pageNum, Integer pageSize) {
        return repo.findAll(PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "title"));
    }

    @Override
    public List<Post> findTitle(String title) {
        return repo.findByTitle(title);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Post post = new Post("hello","world");
        repo.save(post);

    }
}
