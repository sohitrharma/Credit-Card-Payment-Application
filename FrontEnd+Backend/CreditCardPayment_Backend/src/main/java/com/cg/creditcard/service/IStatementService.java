package com.cg.creditcard.service;

import java.util.List;

import com.cg.creditcard.bean.Statement;
import com.cg.creditcard.exceptions.InvalidStatementException;
import com.cg.creditcard.exceptions.StatementNotFoundException;

public interface IStatementService {
	
public Statement addStatement(Long userId, Statement statement) throws InvalidStatementException;
public Statement removeStatement(Long id) throws StatementNotFoundException;
public Statement updateStatement(Long id, Statement statement)  throws StatementNotFoundException;
public Statement getStatement(Long id) throws StatementNotFoundException;
public List<Statement> getAllStatements() throws StatementNotFoundException;
public Statement getBilledStatement()  throws StatementNotFoundException;
public Statement getUnbilledStatement()  throws StatementNotFoundException;

}

	
