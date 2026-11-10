package ru.demo.oauth2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2ApiController {
    @GetMapping("/api/auth/login-url")
    public LoginUrlResponse loginUrl() {
        return new LoginUrlResponse("/oauth2/authorization/github");
    }

    @GetMapping("/api/me")
    public GitHubProfile profile(@AuthenticationPrincipal OAuth2User user) {
        return new GitHubProfile(
                user.getAttribute("login"),
                user.getAttribute("name"),
                user.getAttribute("avatar_url"),
                user.getAttribute("html_url"));
    }

    public record LoginUrlResponse(String authorizationUrl) { }
    public record GitHubProfile(String login, String name, String avatarUrl, String profileUrl) { }
}
