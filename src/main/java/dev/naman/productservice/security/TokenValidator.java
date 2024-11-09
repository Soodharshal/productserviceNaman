package dev.naman.productservice.security;

import dev.naman.productservice.dtos.ValidateRequestDto;
import dev.naman.productservice.enums.SessionStatus;
import dev.naman.productservice.thirdpartyclients.productsservice.fakestore.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    /**
     * call user service validate the token
     * if token is not vald,optionial is valid
     * @param token
     * @return
     */
    private RestTemplateBuilder restTemplateBuilder;
    public TokenValidator(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public Optional<JwtObject> validateToken(String token, String userId) {

        RestTemplate restTemplate = restTemplateBuilder.build();


        ValidateRequestDto validateRequestDto= new ValidateRequestDto();
        validateRequestDto.setToken(token);
        validateRequestDto.setUserId(userId);
        ResponseEntity<SessionStatus> response =
                restTemplate.postForEntity("https://localhost:8081/auth/validate",validateRequestDto,SessionStatus.class);

        return Optional.empty();
    }
}
