package org.codehaus.groovy.grails.orm.hibernate.cfg

import com.awesomecompany.Person
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 13/05/12
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration('/schema/appCtx.xml')
class XGORMNamespaceHandlerTest {
  @Test
  void shouldSavePerson() {
    def person = new Person(
        firstName: 'Domingo',
        lastName: 'Suarez',
        email: 'domingo.suarez@gmail.com'
    )

    assert person.save()
    def personInDB = Person.findByEmail('domingo.suarez@gmail.com')
    assert personInDB.firstName == 'Domingo'
  }
}
