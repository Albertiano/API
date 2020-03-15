package br.com.eiasiscon.pdv.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigPdvRepository extends JpaRepository<ConfigPdv, Long>, ConfigPdvRepositoryCustom {
}
