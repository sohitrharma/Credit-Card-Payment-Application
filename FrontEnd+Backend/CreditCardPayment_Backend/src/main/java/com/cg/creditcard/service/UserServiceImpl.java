package com.cg.creditcard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcard.exceptions.CreditCardNotFoundException;
import com.cg.creditcard.exceptions.InvalidCreditCardException;
import com.cg.creditcard.exceptions.UserAlreadyExistsException;
import com.cg.creditcard.exceptions.UserNotFoundException;
import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.repository.IUserRepository;
import com.cg.creditcard.util.CreditCardConstants;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserRepository userRepository;

	@Override
	public User signIn(User user) throws UserNotFoundException {

		if (user == null) {
			log.warn("No User Available");
			throw new UserNotFoundException("User Not Found");
		}

		Optional<User> option = userRepository.findByName(user.getName());
		if (!option.get().getName().equals(user.getName())) {
			log.warn("No User Available");
			throw new UserNotFoundException("User Not Found");
		} else {
			if (!option.get().getPassword().equals(user.getPassword())) {
				log.warn("No User Available");
				throw new UserNotFoundException("User Not Found");
			}
		}

		user = option.get();
		return user;
	}

	@Override
	public User changePassword(Long userId, User user) throws UserNotFoundException {
		User userNew = null;
		log.warn("Enter UserServiceImpl :: method=resetPassword");
		Optional<User> userObj = userRepository.findById(userId);

		if (!userObj.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		} else {
			userObj.get().setPassword(user.getPassword());
			userNew = userRepository.saveAndFlush(userObj.get());
		}
		log.warn("Exit UserServiceImpl :: method=resetPassword");
		return userNew;
	}

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {

		log.info("Service Layer - Entry - Register user");

		Optional<User> checking = userRepository.findByName(user.getName());
		if (checking.isPresent()) {
			throw new UserAlreadyExistsException("User Already Exists");
		}

		userRepository.save(user);

		log.info("Service Layer - Exit - save user");

		return user;
	}

	/*
	 * @Override public User removeUser(long userId) throws UserNotFoundException {
	 * if (userId <= 0) return null; Optional<User> checking =
	 * userRepository.findById(userId); if (!checking.isPresent()) { throw new
	 * UserNotFoundException(UserContants.USER_NOT_FOUND_BY_ID_CONST + userId); }
	 * User user = userRepository.findById(userId).get(); try {
	 * userRepository.delete(checking.get()); } catch (Exception e) { throw new
	 * UserNotFoundException(UserContants.INVALID_USER_CONST + e.getMessage()); }
	 *
	 *
	 *
	 * return user; }
	 */

	@Override
	public User updateUser(Long userId, User user) throws UserNotFoundException {
		if (userId == null)
			return null;
		else {
			Optional<User> checking = userRepository.findById(userId);
			if (!checking.isPresent()) {
				throw new UserNotFoundException("User Not found");
			} else {

				userRepository.save(user);
			}
		}
		return user;
	}

	@Override
	public User getUser(Long userId) throws UserNotFoundException {
		User userfound = null;
		log.info("Service Layer - Entry - find user");
		if (userId == null)
			return null;
		Optional<User> user = userRepository.findById(userId);

		if (user==null) {
			throw new UserNotFoundException("User Not Found");
		}
		userfound = user.get();
		log.info("Service Layer - Exit - find Customer");
		return userfound;
	}

//	@Override
//	public List<CreditCard> findAllCreditCardById(Long userId) {
//		if ( userId == null)
//			throw new InvalidCreditCardException(CreditCardConstants.INVALID_CREDITCARD_CONST
//					+ "Please valid user id.");
//		Optional<User> user = userRepository.findById(userId);
//		if (!user.isPresent()) {
//			throw new CreditCardNotFoundException(CreditCardConstants.USER_NOT_FOUND_BY_USER_ID_CONST + userId);
//		}
//		List<CreditCard> creditCards = new ArrayList<>();
//		for (CreditCard c : userRepository.findAllCreditCardsById(userId)) {
//			creditCards.add(c);
//		}
//		if (creditCards.isEmpty()) {
//			throw new CreditCardNotFoundException(CreditCardConstants.CREDITCARD_NOT_FOUND_BY_USER_ID_CONST + userId);
//		}
//		return creditCards;
//	}

	/*
	 * @Override public List<User> getAllUsers() throws UsersNotFoundException {
	 * List<User> users = new ArrayList<>(); for (User u : userRepository.findAll())
	 * { users.add(u); } if (users.isEmpty()) { throw new
	 * UsersNotFoundException("No Users present in the database"); } return users; }
	 */
}
