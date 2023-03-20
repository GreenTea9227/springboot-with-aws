package com.jojoldu.book.springbootwebservice.config.auth;

import com.jojoldu.book.springbootwebservice.domain.user.Role;
import com.jojoldu.book.springbootwebservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributesKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributesKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributesKey = nameAttributesKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributes, Map<String, Object> attributes) {
        if (registrationId.equals("naver")) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributes, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributes, Map<String, Object> attributes) {
        Map<String, Object> map = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) map.get("name"))
                .picture((String) map.get("profile_image"))
                .email((String) map.get("email"))
                .attributes(map)
                .nameAttributesKey(userNameAttributes)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributes, Map<String, Object> attributes) {

        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributesKey(userNameAttributes)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
