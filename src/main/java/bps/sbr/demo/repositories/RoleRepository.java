package bps.sbr.demo.repositories;

import bps.sbr.demo.models.ERole;
import bps.sbr.demo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

    @Modifying
    @Query(value = "DELETE FROM user_roles ur WHERE ur.user_id = :user_id", nativeQuery = true)
    void deleteUserRoleByID(@Param("user_id") Long user_id);
}
