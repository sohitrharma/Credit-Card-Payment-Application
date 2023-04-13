package com.cg.creditcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.creditcard.bean.Statement;
import com.cg.creditcard.bean.User;
import com.cg.creditcard.exceptions.InvalidStatementException;
import com.cg.creditcard.exceptions.StatementNotFoundException;
import com.cg.creditcard.repository.*;
import com.cg.creditcard.util.StatementConstants;

@Service
public class StatementServiceImpl implements IStatementService {
	
	@Autowired
	private IStatementRepository statementRepository;
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public Statement addStatement(Long userId, Statement statement) throws InvalidStatementException {
		if (statement == null || userId==null)
			throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST + "Please enter all the statement details with the user id.");
		User user=userRepository.findById(userId).get();
		
		Optional<Statement> checking = statementRepository.findById(statement.getStatementId());
		if (checking.isPresent())
			throw new InvalidStatementException("Statement Already Exists");

		try {
			statement.setUser(user);
			statementRepository.save(statement);
		} catch (Exception e) {
			throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST + e.getMessage());
		}
		return statement;
	}

	@Override
	public Statement removeStatement(Long id) throws StatementNotFoundException {
		if (id == null)
			throw new StatementNotFoundException(StatementConstants.INVALID_STATEMENT_CONST
					+ "Statement ID cannot be null.Please enter Statement ID");
		Optional<Statement> checking = statementRepository.findById(id);
		if (!checking.isPresent()) {
			throw new StatementNotFoundException(StatementConstants.STATEMENT_NOT_FOUND_BY_ID_CONST + id);
		}
		Statement statement = statementRepository.findById(id).get();
		try {
			statementRepository.deleteById(id);
		} catch (Exception e) {
			throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST + e.getMessage());
		}
		
		return statement;
	}

	@Override
	public Statement updateStatement(Long id, Statement statement) throws StatementNotFoundException {
		if (id == null)
			throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST
					+ "Statement ID cannot be null.Please enter Statement ID");
		else {
			Optional<Statement> checking = statementRepository.findById(id);
			if (!checking.isPresent()) {
				throw new StatementNotFoundException(StatementConstants.STATEMENT_NOT_FOUND_BY_ID_CONST + id);
			} else {
				Statement statement1 = statementRepository.findById(id).get();
				statement1 = (statement);
				try {
					statementRepository.save(statement1);
				} catch (Exception e) {
					throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST + e.getMessage());
				}
			}
		}
		return statement;
	}

	@Override
	public Statement getStatement(Long id) throws StatementNotFoundException {
		if (id == null)
			throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST
					+ "Statement ID cannot be null.Please enter Statement ID");
		Optional<Statement> checking = statementRepository.findById(id);
		if (!checking.isPresent()) {
			throw new StatementNotFoundException(StatementConstants.STATEMENT_NOT_FOUND_BY_ID_CONST + id);
		}
		Statement statement;
		try {
			statement = statementRepository.findById(id).get();
		} catch (Exception e) {
			throw new InvalidStatementException(StatementConstants.INVALID_STATEMENT_CONST + e.getMessage());
		}

		return statement;
	}

	@Override
	public List<Statement> getAllStatements() throws StatementNotFoundException {
		List<Statement> statements = new ArrayList<>();
		for (Statement s : statementRepository.findAll()) {
			statements.add(s);
		}
		if (statements.isEmpty()) {
			throw new StatementNotFoundException("No Statements present in the database");
		}
		return statements;
	}

	@Override
	public Statement getBilledStatement() throws StatementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement getUnbilledStatement() throws StatementNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	
}















