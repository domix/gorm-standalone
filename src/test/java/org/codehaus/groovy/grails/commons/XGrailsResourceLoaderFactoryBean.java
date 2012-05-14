package org.codehaus.groovy.grails.commons;

import grails.util.Environment;
import org.codehaus.groovy.grails.commons.spring.GrailsResourceHolder;
import org.codehaus.groovy.grails.compiler.support.GrailsResourceLoader;
import org.codehaus.groovy.grails.compiler.support.GrailsResourceLoaderHolder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 13/05/12
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class XGrailsResourceLoaderFactoryBean implements FactoryBean<GrailsResourceLoader>, InitializingBean {

  private GrailsResourceHolder grailsResourceHolder;
  private GrailsResourceLoader resourceLoader;
  //private String

  @Deprecated
  public void setGrailsResourceHolder(GrailsResourceHolder grailsResourceHolder) {
    this.grailsResourceHolder = grailsResourceHolder;
  }

  public GrailsResourceLoader getObject() {
    return resourceLoader;
  }

  public Class<GrailsResourceLoader> getObjectType() {
    return GrailsResourceLoader.class;
  }

  public boolean isSingleton() {
    return true;
  }

  public void afterPropertiesSet() throws Exception {
    resourceLoader = GrailsResourceLoaderHolder.getResourceLoader();
    if (resourceLoader != null) {
      return;
    }

    if (Environment.getCurrent().isReloadEnabled()) {
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      try {
        Resource[] resources = resolver.getResources("file:" +
            Environment.getCurrent().getReloadLocation() + "/grails-app/**/*.groovy");
        resourceLoader = new GrailsResourceLoader(resources);
      }
      catch (IOException e) {
        createDefaultInternal();
      }
    }
    else {
      createDefaultInternal();
    }
    GrailsResourceLoaderHolder.setResourceLoader(resourceLoader);
  }

  private void createDefaultInternal() {
    if (grailsResourceHolder == null) grailsResourceHolder = new GrailsResourceHolder();
    resourceLoader = new GrailsResourceLoader(grailsResourceHolder.getResources());
  }
}
