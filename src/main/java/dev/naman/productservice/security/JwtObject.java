package dev.naman.productservice.security;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class JwtObject {
    private String email;
    private String userId;
    private Date createdAt;
    private Date expiryAt;
    private List<Role> roles = new ArrayList<>();
}
