package org.codehaus.groovy.grails.orm.hibernate.cfg

import com.awesomecompany.Person
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional
import org.slf4j.LoggerFactory
import org.slf4j.Logger

import static com.awesomecompany.TestUtil.*

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 13/05/12
 * Time: 20:06
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration('/schema/appCtx.xml')
//@ContextConfiguration('/appCtxDefaultGorm.xml')
@Transactional
class XGORMNamespaceHandlerTest {
  private static final Logger log = LoggerFactory.getLogger(XGORMNamespaceHandlerTest)

  @Test
  void shouldSavePerson() {
    def person = newDemoPerson()

    person.save()

    def people = Person.list()
    assert people
    log.debug(people.dump())

    def personInDB = Person.findByEmail(EMAIL)
    assert personInDB
    assert personInDB.firstName == FIRST_NAME

  }

  @Test
  void shouldQueryUsingCriteriaWithOneField() {
    def person = newDemoPerson()

    assert person.save()


    def c = Person.withCriteria(uniqueResult: true) {
      eq 'email', EMAIL
    }

    log.debug(c.dump())

    assert c
    assert FIRST_NAME == c.firstName
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
