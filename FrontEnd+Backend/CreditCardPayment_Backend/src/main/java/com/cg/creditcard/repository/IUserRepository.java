package com.cg.creditcard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.creditcard.exceptions.UserNotFoundException;
import com.cg.creditcard.bean.CreditCard;
import com.cg.creditcard.bean.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByName(@Param("name") String name) throws UserNotFoundException;
//	public List<CreditCard> findAllCreditCardsById(Long userId);
}
