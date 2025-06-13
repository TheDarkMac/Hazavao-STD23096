package torn.ando.poja.endpoint.rest.controller;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import torn.ando.poja.service.HazavaoService;

@RestController
@RequestMapping("hazavao")
@AllArgsConstructor
public class HazavaoController {

  private final HazavaoService hazavaoService;

  @GetMapping
  public ResponseEntity<Map<String, String>> getDefinition(@RequestParam String teny) {
    if (teny == null || teny.trim().isEmpty()) {
      return ResponseEntity.badRequest().body(Map.of("error", "Tsy ampy ny teny.")); // 400
    }

    String definition = hazavaoService.getDefinition(teny);

    if (definition == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Map.of("error", "Tsy afaka namaly ny teny hoe: " + teny)); // error 500
    }

    return ResponseEntity.ok(
        Map.of(
            "mot", teny,
            "definition", definition));
  }
}
