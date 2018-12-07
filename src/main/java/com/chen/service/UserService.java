package com.chen.service;

import com.chen.model.User;
import com.chen.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * UserService
 *
 * @Author LeifChen
 * @Date 2018-12-07
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

    @Resource
    private UserRepository userRepository;

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }

    public void updateById(Integer id, Integer age) {
        userRepository.updateById(id, age);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
