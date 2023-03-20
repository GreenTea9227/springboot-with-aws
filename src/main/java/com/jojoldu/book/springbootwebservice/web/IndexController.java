package com.jojoldu.book.springbootwebservice.web;

import com.jojoldu.book.springbootwebservice.config.auth.LoginUser;
import com.jojoldu.book.springbootwebservice.config.dto.SessionUser;
import com.jojoldu.book.springbootwebservice.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(@LoginUser SessionUser user,
                        Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
