
package com.cg.creditcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.creditcard.bean.Statement;


@Repository
public interface IStatementRepository extends JpaRepository<Statement,Long> {

	public Statement findByStatementId(Long id);
}
