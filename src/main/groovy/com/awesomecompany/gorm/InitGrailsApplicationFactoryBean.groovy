package com.awesomecompany.gorm

import org.codehaus.groovy.grails.commons.GrailsApplicationFactoryBean

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 11/05/12
 * Time: 10:13
 * To change this template use File | Settings | File Templates.
 */
class InitGrailsApplicationFactoryBean extends GrailsApplicationFactoryBean {

  /**
   * You need to do this, because is necessary to explicitely call the initialise on GrailsApplication
   */
  @Override
  void afterPropertiesSet() throws Exception {
    super.afterPropertiesSet()
    super.object.initialise()
  }
}
