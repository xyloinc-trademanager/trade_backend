package com.tm.app.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tm.app.entity.ItemCategory;
@Repository
public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Long> {

	Object findByCategoryName(String categoryName);

	Page<ItemCategory> findByCategoryNameLikeIgnoreCase(String search, PageRequest of);

	boolean existsByCategoryNameIgnoreCase(String categoryName);


}
