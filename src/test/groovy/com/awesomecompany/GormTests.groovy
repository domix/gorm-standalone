package com.awesomecompany

import org.codehaus.groovy.grails.orm.hibernate.validation.HibernateDomainClassValidator
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

import static com.awesomecompany.TestUtil.*

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/05/12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration('/schema/appCtx.xml')
@Transactional
class GormTests {
  private static final Logger log = LoggerFactory.getLogger(GormTests)
  @Autowired
  ApplicationContext applicationContext



  @Test
  void shouldSavePerson() {
    def person = newDemoPerson()

    assert person.save()
    def personInDB = Person.findByEmail(EMAIL)
    assert FIRST_NAME == personInDB.firstName
  }

  @Test
  void shouldValidateUsingTheDomainClassValidator() {
    HibernateDomainClassValidator personValidator = applicationContext.getBean('com.awesomecompany.PersonValidator')

    Person.withTransaction {
      def person = new Person()
      personValidator.validate(person, person.errors)

      assert person.errors
    }
  }

  @Test
  void shouldValidatePersonUsingConstraints() {
    def person = new Person()

    def validPerson = person.validate()
    def hasErrors = person.hasErrors()

    assert person.errors
    assert hasErrors
    assert !validPerson
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

}
