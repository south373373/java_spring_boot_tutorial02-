package com.example.spring_tutorial02.controller;

import java.util.List;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_tutorial02.entity.User;
import com.example.spring_tutorial02.service.UserService;
import com.example.spring_tutorial02.form.UserRegisterForm;


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

        // 既にインスタンスが存在する場合は行わない。
        if(!model.containsAttribute("userRegisterForm")){
            model.addAttribute("userRegisterForm", new UserRegisterForm());
        }

        return "adminUserView";
    }

    @PostMapping("/register")
    public String registerUser(RedirectAttributes redirectAttributes,
        @Validated UserRegisterForm form, BindingResult result){
        // UserRegisterForm form){

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterForm", form);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + Conventions.getVariableName(form), result);

            return "redirect:/adminuser";
        }

        try{
            userService.createUser(form.getUserName(), form.getPassword(), form.getRoleId());
            // userService.createUser(userName, password, roleId);

            redirectAttributes.addFlashAttribute("successMessage", "ユーザー登録が完了しました");
        } catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());

            redirectAttributes.addFlashAttribute("userRegisterForm", form);
            // redirectAttributes.addFlashAttribute("userName", userName);
            // redirectAttributes.addFlashAttribute("roleId", roleId);
        }

        return "redirect:/adminuser";
    }
}
