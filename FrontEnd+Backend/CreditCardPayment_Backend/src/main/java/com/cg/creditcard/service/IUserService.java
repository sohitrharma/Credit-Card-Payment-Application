package com.cg.creditcard.service;

import java.util.List;

import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.exceptions.UserAlreadyExistsException;
import com.cg.creditcard.exceptions.UserNotFoundException;
import com.cg.creditcard.exceptions.UsersNotFoundException;

public interface IUserService {
	public User signIn(User user)throws UserNotFoundException;
    public User changePassword(Long userId, User user)throws UserNotFoundException;
    public User addUser(User user)throws UserAlreadyExistsException;
    public User updateUser(Long userId,  User user)throws UserNotFoundException;
    public User getUser(Long userId)throws UserNotFoundException;
    //public List<User> getAllUsers()throws UsersNotFoundException; 
    //public User removeUser(long userId)throws UserNotFoundException;
//    public List<CreditCard> findAllCreditCardById(Long userId);
}
