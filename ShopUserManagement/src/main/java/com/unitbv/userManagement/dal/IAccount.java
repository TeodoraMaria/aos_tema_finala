package com.unitbv.userManagement.dal;

import com.unitbv.userManagement.dao.Person;

public interface IAccount {

    boolean insert(Person person);

    Person login(Person person);

}
