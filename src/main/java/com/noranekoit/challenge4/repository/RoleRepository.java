package com.noranekoit.challenge4.repository;

import com.noranekoit.challenge4.models.ERole;
import com.noranekoit.challenge4.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
