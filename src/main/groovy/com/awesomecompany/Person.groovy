package com.awesomecompany

import grails.persistence.Entity
import org.joda.time.DateTime
import org.joda.time.contrib.hibernate.PersistentDateTime

/**
 * Created with IntelliJ IDEA.
 * User: domix
 * Date: 10/05/12
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
class Person {
  String firstName
  String lastName
  Locale locale
  String referrerCode
  String referredByCode
  Long referredById
  boolean registered = false
  Set subscriptions, purchases

  String email
  String password

  boolean enabled
  boolean accountExpired
  boolean accountLocked = true
  boolean passwordExpired

  DateTime dateCreated
  DateTime lastUpdated

  static constraints = {
    def onlyNullable = [nullable: true]
    def nullableMaxSize = [nullable: true, maxSize: 30]
    firstName onlyNullable
    lastName onlyNullable
    referrerCode nullableMaxSize
    referredByCode nullableMaxSize
    referredById onlyNullable
    locale(blank: false)

    email(blank: false, unique: true, email: true)
    password onlyNullable
    lastUpdated onlyNullable
  }

  /* Mappings */
  static mapping = {
    password column: '`password`'
    dateCreated type: PersistentDateTime
    lastUpdated type: PersistentDateTime
  }

  public String toString() {
    "$firstName $lastName"
  }

}
