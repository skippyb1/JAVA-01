package com.example.demo.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.List;
import java.util.Random;

/**
 * @author gqp
 * @version 1.0
 * @className DynamicDataSource
 * @descriptin TODO
 * @date 2021/3/7 20:50
 **/
@Getter
@Setter
@AllArgsConstructor
public class DynamicDataSource extends AbstractRoutingDataSource {

  private String master;
  private List<String> slaves;

  @Override
  protected Object determineCurrentLookupKey() {

    String datasourceType = DatasourceContextHolder.getDatasourceType();

    if(datasourceType.equals("master")){
      return master;
    }
    return getSlave();
  }

  private String getSlave(){
    return slaves.get(new Random().nextInt(slaves.size()));
  }


}
