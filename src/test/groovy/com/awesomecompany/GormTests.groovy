package com.awesomecompany

import org.junit.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/05/12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
class GormTests {
  @Test
  void shouldBuildApplicationContext() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext('/appCtxGorm.xml');

    assert applicationContext

    def p = Person.get(1)

    assert !p

    p = new Person()

    assert !p.validate()
  }
}
