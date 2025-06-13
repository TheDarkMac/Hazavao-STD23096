package torn.ando.poja.conf;

import org.springframework.test.context.DynamicPropertyRegistry;
import torn.ando.poja.PojaGenerated;

@PojaGenerated
public class BucketConf {

  void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("aws.s3.bucket", () -> "dummy-bucket");
  }
}
