package com.awesomecompany

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/05/12
 * Time: 17:27
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration('/appCtxDefaultGorm.xml')
class GormTests {
  @Autowired
  ApplicationContext applicationContext

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
  void shouldValidatePersonUsingConstraints() {
    def person = new Person()
    def validPerson = person.validate()
    def hasErrors = person.hasErrors()

    assert person.errors
    assert hasErrors
    assert !validPerson
  }
}
