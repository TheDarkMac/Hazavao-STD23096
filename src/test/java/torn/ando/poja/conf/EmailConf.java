package torn.ando.poja.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import torn.ando.poja.PojaGenerated;

@PojaGenerated
public class EmailConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.ses.source", () -> "dummy-ses-source");
  }
}
