package org.example.shareconfig2.repository;

import jakarta.transaction.Transactional;
import org.example.shareconfig2.models.ConfigModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<ConfigModel,Long> {
        /** - "Id":       This tells Spring to look at the 'id' property of the 'user' field.
        *
             * @param userId The ID of the user whose items you want to retrieve.
            * @return A List of Item entities. Returns an empty list if no items are found.
        */

        List<ConfigModel> findByUserId(Long userId);

        @Transactional
        long deleteByUserId(Long userId);
}
