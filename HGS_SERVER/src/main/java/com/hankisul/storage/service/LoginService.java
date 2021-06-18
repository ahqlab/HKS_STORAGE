package com.hankisul.storage.service;

import com.hankisul.storage.domain.User;
import com.hankisul.storage.exceptions.InvalidPasswordException;
import com.hankisul.storage.exceptions.NotFoundUserException;

public abstract interface LoginService
{
  public abstract User login(User paramUser)
    throws NotFoundUserException, InvalidPasswordException;
}