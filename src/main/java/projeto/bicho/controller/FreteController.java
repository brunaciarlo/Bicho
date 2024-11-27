package projeto.bicho.controller;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class FreteController {

	@PostMapping("/calcular")
	public ResponseEntity<?> calcularFrete(@RequestBody Map<String, Object> dados){
		String url = "https://www.melhorenvio.com.br/api/v2/me/shipment/calculate";
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMzIwNmJjMGEyNGYzZTM1NzExODhjYmE3NGVmOTM2M2MxZjQzMWJjZjVjNzJmNmU0ZmU3MGE4MjZmN2ZkM2YyOGRlZmMxNGM2ZWVhZWY0"
        		+ "OTAiLCJpYXQiOjE3MzI2NTMwODYuMzcxNDg0LCJuYmYiOjE3MzI2NTMwODYuMzcxNDg2LCJleHAiOjE3NjQxODkwODYuMzU3MjksInN1YiI6IjlkOTU2NjdlLWYxMzItNDBlMi04ZjhiLTVkMmQzYjE2MDMwMCIsInNjb3BlcyI6WyJzaGl"
        		+ "wcGluZy1jYWxjdWxhdGUiXX0.ETEF8nfZc6xn5224tWMfKDauziTjXx_DHupBYwS5xt__kI_--9n52ZYTD2XakyWGcGke_OjlmYNksXvEmytSDMiaorDLhuZOh4TI01Gs-Ic_RzI8Ye4bOkcOe9bzvFAPbDuZrtzzrzFNdwgc4dCRQUbYy4"
        		+ "eZLKc_EOsw_jnllvKs50RS1CXBmb8v_3L4Mz6W-2XcGJh8ihJaCDNL9HexkTdJQEFt5QS6HgdzTN_aZ4O-OHBEAv-YhQJgs_9o3Sx9DTiX1PgtTsh5nBJ8jaqwh36n87fPQtV0OA0sDcj1keMGfjoq2zW7EhIGGTh6ohjW6RjLKXmK2ugK69"
        		+ "Dl4p6h-ZXyz3zjkxIuHtoAzBynPwm8stsIOQO6E1JCKlVKLcw4HiOzQm2YXBQwStr8xK3rdT9jI36hppsLilpNQ4si8WAcKy_SaZ-7vxDRm30vHRe5ed_P6Ce996ofeXc09anUjIVTCtp_Qme9chcrArMc_iQfi0o480gjpOsUA9jzG0q1EO"
        		+ "q13I_wRqkNy16hnhnBFuY7Bfs4FLlbXfcQ6EZUsMi9QJuVOPzLCU21a8dKmASGBevzDxdfCFem4FEFMtUam8HrpLbfObfRZCb8h80jkjEnBdfyrmLzLZyPjIo3R7pEVPYTTu7y7s4wXQT3gHhvD7rftyfPVneNUsawlQvcMv8";


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Accept", "application/json");
        headers.set("Content-Type", "application/json");
        headers.set("User-Agent", "ReadMe-API-Explorer");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(dados, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao calcular frete: " + e.getMessage());
        }
	}
}
