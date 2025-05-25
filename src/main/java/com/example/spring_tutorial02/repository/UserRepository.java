package com.example.spring_tutorial02.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springtutorial.entity.User;

// 「JpaRepository<User, Integer>」にてリポジトリとして扱いたい、対象Entity名と主キーを指定。
public interface UserRepository extends JpaRepository<User, Integer>{
    // ユーザー名で完全一致検索
    List<User> findByUserName(String userName);
}
