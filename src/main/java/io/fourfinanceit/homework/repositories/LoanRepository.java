package io.fourfinanceit.homework.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.fourfinanceit.homework.models.Loan;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
	@Query("SELECT COUNT(*) FROM Loan l WHERE :date = l.requested AND l.ip LIKE :ip AND l.client.id = :clientId")
    public Integer findAppCountByIp(@Param("date")Date date, @Param("ip") String ip, @Param("clientId") Long clientId);
}
