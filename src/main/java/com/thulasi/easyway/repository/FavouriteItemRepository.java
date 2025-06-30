package com.thulasi.easyway.repository;

import com.thulasi.easyway.model.FavouriteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteItemRepository extends JpaRepository<FavouriteItem, Long> {

    List<FavouriteItem> findByUserId(Long userId);

    Optional<FavouriteItem> findByUserIdAndProductId(Long userId, Long productId);

    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
