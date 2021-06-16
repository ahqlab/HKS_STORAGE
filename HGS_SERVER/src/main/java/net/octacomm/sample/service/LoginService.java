package net.octacomm.sample.service;

import net.octacomm.sample.domain.User;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;

public abstract interface LoginService
{
  public abstract User login(User paramUser)
    throws NotFoundUserException, InvalidPasswordException;
}