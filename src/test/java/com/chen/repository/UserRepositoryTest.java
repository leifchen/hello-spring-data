package com.chen.repository;

import com.chen.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.Path;

/**
 * UserRepositoryTest
 *
 * @Author LeifChen
 * @Date 2018-12-07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByName() {
        System.out.println(userRepository.findByName("test1"));
    }

    @Test
    public void findByMaxId() {
        System.out.println(userRepository.findByMaxId());
    }

    @Test
    public void findByParam() {
        System.out.println(userRepository.findByParam("test", 18));
    }

    @Test
    public void findByParam2() {
        System.out.println(userRepository.findByParam2("test", 18));
    }

    @Test
    public void count() {
        System.out.println(userRepository.count());
    }

    @Test
    public void pageAndSort() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "age");
        Page<User> page = userRepository.findAll(pageable);

        System.out.println("查询的总页数：" + page.getTotalPages());
        System.out.println("查询的总记录数：" + page.getTotalElements());
        System.out.println("查询的当前第几页：" + (page.getNumber() + 1));
        System.out.println("查询的当前页面的集合：" + page.getContent());
        System.out.println("查询的当前页面的记录数：" + page.getNumberOfElements());
    }

    @Test
    public void find() {
        userRepository.findById(3).ifPresent(System.out::println);
    }

    @Test
    public void exist() {
        System.out.println("user(3)：" + userRepository.existsById(3));
        System.out.println("user(100)：" + userRepository.existsById(100));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void complexQuery() {
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.ASC, "age");
        Specification<User> specification = (Specification<User>) (root, query, criteriaBuilder) -> {
            Path path = root.get("age");
            return criteriaBuilder.gt(path, 30);
        };
        Page<User> page = userRepository.findAll(specification, pageable);

        System.out.println("查询的总页数：" + page.getTotalPages());
        System.out.println("查询的总记录数：" + page.getTotalElements());
        System.out.println("查询的当前第几页：" + (page.getNumber() + 1));
        System.out.println("查询的当前页面的集合：" + page.getContent());
        System.out.println("查询的当前页面的记录数：" + page.getNumberOfElements());
    }
}