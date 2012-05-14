package com.awesomecompany

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 14/05/12
 * Time: 00:56
 * To change this template use File | Settings | File Templates.
 */
class TestUtil {
  static final String EMAIL = 'domingo.suarez@gmail.com'
  static final String FIRST_NAME = 'Domingo'
  static final String LAST_NAME = 'Suarez'
  static Person newDemoPerson() {
    new Person(
        firstName: FIRST_NAME,
        lastName: LAST_NAME,
        email: EMAIL
    )
  }

}
