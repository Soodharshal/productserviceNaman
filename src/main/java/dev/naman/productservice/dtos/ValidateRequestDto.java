package dev.naman.productservice.dtos;

import lombok.Data;

@Data
public class ValidateRequestDto {
    String token;
    String userId;
}
