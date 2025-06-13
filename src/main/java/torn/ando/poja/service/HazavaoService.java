package torn.ando.poja.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HazavaoService {


  @Value("${openai_api_key}")
  private String apikey;

  @Value("${openai_api_url}")
  private String apiUrl;

  private final RestTemplate restTemplate = new RestTemplate();

  public String getDefinition(String word) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setBearerAuth(apikey);

      Map<String, Object> body = new HashMap<>();
      body.put("model", "gpt-3.5-turbo");
      body.put(
          "messages",
          List.of(Map.of("role", "user", "content", "Hazavao ny dikan'ny teny hoe: " + word)));

      HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
      ResponseEntity<Map> response =
          restTemplate.exchange(apiUrl, HttpMethod.POST, request, Map.class);

      List<Map<String, Object>> choices =
          (List<Map<String, Object>>) response.getBody().get("choices");
      Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
      return message.get("content").toString().trim();
    } catch (Exception e) {
      return null;
    }
  }
}
