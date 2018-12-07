package com.chen.repository;

import com.chen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * UserRepository
 *
 * @Author LeifChen
 * @Date 2018-12-07
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * 根据名称查询用户（使用 JPA 命名规则）
     *
     * @param name 名称
     * @return
     */
    User findByName(String name);

    /**
     * 查询 id 为最大值的用户
     *
     * @return
     */
    @Query("select u from #{#entityName} u where id=(select max(id) from User t1)")
    User findByMaxId();

    /**
     * 根据名称、年龄查询用户（使用 @Query 注解及占位符 ? 绑定参数）
     *
     * @param name 名称
     * @param age  年龄
     * @return
     */
    @Query("select u from #{#entityName} u where u.name like ?1% and u.age >= ?2")
    List<User> findByParam(String name, Integer age);

    /**
     * 根据名称、年龄查询用户（使用 @Query 注解及 @Param 绑定参数）
     *
     * @param name 名称
     * @param age  年龄
     * @return
     */
    @Query("select u from #{#entityName} u where u.name like :name% and u.age >= :age")
    List<User> findByParam2(@Param("name") String name, @Param("age") Integer age);

    /**
     * 根据 id 更新用户年龄
     *
     * @param id
     * @param age 年龄
     */
    @Modifying
    @Query("update User u set u.age = :age where u.id = :id")
    void updateById(@Param("id") Integer id, @Param("age") Integer age);
}
