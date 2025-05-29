package com.example.spring_tutorial02.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_tutorial02.entity.User;
import com.example.spring_tutorial02.service.UserService;

@Controller
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminuser")
    public String adminUser(Model model) {
        // 最新のユーザーリストを取得
        List<User> users = userService.getAllUsers();

        // ビューにユーザーリストを渡す
        // 上記で指定した「users」をviewsの「users」に渡すと指定。
        model.addAttribute("users", users);

        return "adminUserView";
    }

    @PostMapping("/register")
    public String registerUser(RedirectAttributes redirectAttributes,
        @RequestParam("user_name") String userName,
        @RequestParam("password") String password,
        @RequestParam("role_id") int roleId) {

        try{
            userService.createUser(userName, password, roleId);

            redirectAttributes.addFlashAttribute("successMessage", "ユーザー登録が完了しました");
        } catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());

            redirectAttributes.addFlashAttribute("userName", userName);
            redirectAttributes.addFlashAttribute("roleId", roleId);
        }

        return "redirect:/adminuser";

    }
}
