package com.example.demo.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gqp
 * @version 1.0
 * @className DatasourceProperty
 * @descriptin TODO
 * @date 2021/3/7 21:23
 **/
@ConfigurationProperties(
    prefix = "demo.datasources"
)
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatasourceProperties {

  private DatasourceProperty master;
  private List<DatasourceProperty> slaves;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  public static class DatasourceProperty{
    private String driver;
    private String url;
    private String username;
    private String password;
  }
}
