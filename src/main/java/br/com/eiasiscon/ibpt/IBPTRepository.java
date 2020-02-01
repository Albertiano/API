package br.com.eiasiscon.ibpt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBPTRepository extends JpaRepository<IBPT, Long> {
}
