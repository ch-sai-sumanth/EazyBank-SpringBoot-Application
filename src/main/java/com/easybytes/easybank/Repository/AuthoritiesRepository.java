package com.easybytes.easybank.Repository;

import com.easybytes.easybank.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority,Long> {
}
