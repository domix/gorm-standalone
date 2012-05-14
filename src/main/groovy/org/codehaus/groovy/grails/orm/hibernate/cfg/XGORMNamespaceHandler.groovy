package org.codehaus.groovy.grails.orm.hibernate.cfg

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.xml.NamespaceHandlerSupport

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 13/05/12
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
class XGORMNamespaceHandler extends NamespaceHandlerSupport {
  private static final Logger log = new LoggerFactory().getLogger(XGORMNamespaceHandler)

  void init() {
    log.debug('init')
    ExpandoMetaClass.enableGlobally();
    registerBeanDefinitionParser('sessionFactory', new XGORMSessionFactoryDefinitionParser());
  }
}
