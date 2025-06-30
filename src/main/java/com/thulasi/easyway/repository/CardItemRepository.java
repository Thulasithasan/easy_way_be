package com.thulasi.easyway.repository;

import com.thulasi.easyway.model.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardItemRepository extends JpaRepository<CardItem, Long> {

    Optional<CardItem> findByUserIdAndProductId(Long userId, Long productId);

    List<CardItem> findByUserId(Long userId);

    List<CardItem> findByIdInAndUserId(List<Long> ids, Long userId);


}
