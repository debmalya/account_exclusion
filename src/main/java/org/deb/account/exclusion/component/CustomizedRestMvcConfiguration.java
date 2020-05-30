package org.deb.account.exclusion.component;

import org.deb.account.exclusion.entity.ExclusionAccounts;
import org.deb.account.exclusion.entity.SubmittedRequest;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {
  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.setBasePath("/api");
    config.exposeIdsFor(ExclusionAccounts.class, SubmittedRequest.class);
  }



}
