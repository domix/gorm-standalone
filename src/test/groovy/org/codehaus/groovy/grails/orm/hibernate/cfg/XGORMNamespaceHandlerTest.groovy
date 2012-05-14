package org.codehaus.groovy.grails.orm.hibernate.cfg

import com.awesomecompany.Person
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 13/05/12
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration('/schema/appCtx.xml')
@Transactional
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

  @Test
  @Ignore
  void shouldValidatePersonUsingConstraints() {
    def person = new Person()

    def validPerson = person.validate()
    def hasErrors = person.hasErrors()

    assert person.errors
    assert hasErrors
    assert !validPerson
  }
}
