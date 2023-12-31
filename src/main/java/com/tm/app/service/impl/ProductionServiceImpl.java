package com.tm.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tm.app.dto.DataFilter;
import com.tm.app.dto.ProductionDto;
import com.tm.app.entity.ItemMaster;
import com.tm.app.entity.Production;
import com.tm.app.entity.ProductionDetails;
import com.tm.app.entity.Stock;
import com.tm.app.entity.UnitOfMeasure;
import com.tm.app.enums.ProductionStatus;
import com.tm.app.repo.ProductionRepository;
import com.tm.app.repo.StockRepo;
import com.tm.app.service.ProductionService;
import com.tm.app.utils.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProductionServiceImpl implements ProductionService {

	@Autowired
	private ProductionRepository productionRepository;

	@Autowired
	private StockRepo stockRepo;

	@Autowired
	private TokenService tokenService;

	@Override
	public Production saveProduction(ProductionDto productionDto, HttpServletRequest request)
			throws JsonProcessingException {
		Production production = new Production();
		List<Stock> stockList = new ArrayList<Stock>();
		String userName = tokenService.getUserName(request);
		production.setUpdatedBy(userName);
		BeanUtils.copyProperties(productionDto, production);
		production = productionRepository.save(production);

		// Insert into Stock Table
		saveStock(productionDto, production, stockList);
		return production;
	}

	@Override
	public Production getProductionById(Long id) {
		return productionRepository.findById(id).orElseThrow();
	}

	@Override
	public Production updateProductionById(Long id, ProductionDto productionDto, HttpServletRequest request)
			throws JsonProcessingException {
		Production production = new Production();
		List<Stock> stockList = new ArrayList<Stock>();

		production = productionRepository.findById(id).orElseThrow();
		BeanUtils.copyProperties(productionDto, production);
		production = productionRepository.save(production);

		saveStock(productionDto, production, stockList);
		return production;
	}

	@Override
	public void deleteProductionById(Long id) {
		try {
			// Retrieve the Production and related Stock records
			Optional<Production> productionOptional = productionRepository.findById(id);

			if (productionOptional.isPresent()) {
				Production production = productionOptional.get();

				production.getProductionDetails().stream().forEach(r -> {
					Stock existingStock = stockRepo.findByItemMasterAndUnitOfMeasure(production.getItemMaster(),
							r.getUnitOfMeasure());
					existingStock.setInStock(existingStock.getInStock() - r.getQuantity());
				});

				ItemMaster itemMaster = production.getItemMaster();

				if (itemMaster != null) {

					productionRepository.deleteById(id);
				}
			} else {
				log.error("[PRODUCTION] Deleting production failed: Production not found");
				throw new RuntimeException("Deleting production failed: Production not found");
			}
		} catch (Exception e) {
			log.error("[PRODUCTION] Deleting production failed", e);
			throw new RuntimeException("Deleting production failed");
		}
	}

	@Override
	public Page<Production> getProductionList(DataFilter dataFilter) {
		return productionRepository.findByItemName(dataFilter.getSearch(), PageRequest.of(dataFilter.getPage(),
				dataFilter.getSize(), Sort.by(dataFilter.getSortBy(), dataFilter.getSortByField())));
	}

	@Override
	public List<Production> getAllProduction() {
		return productionRepository.findAll();
	}

	/**
	 * Save Stock
	 * 
	 * @param productionDto
	 * @param production
	 * @param stockList
	 */
	private void saveStock(ProductionDto productionDto, Production production, List<Stock> stockList) {
		if (Objects.nonNull(production)) {
			if (production.getStatus().equals(ProductionStatus.FINISHED)) {
				production.getProductionDetails().forEach(r -> {

					UnitOfMeasure uom = r.getUnitOfMeasure();
					Stock existingStock = stockRepo.findByItemMasterAndUnitOfMeasure(productionDto.getItemMaster(),
							uom);

					if (existingStock != null) {
						existingStock.setInStock(existingStock.getInStock() + r.getQuantity());
						existingStock.setUpdatedBy(productionDto.getUpdatedBy());
						stockRepo.save(existingStock);
					} else {
						Stock stock = new Stock();
						stock.setInStock(r.getQuantity());
						stock.setUnitOfMeasure(uom);
						stock.setItemMaster(productionDto.getItemMaster());
						stock.setUpdatedBy(productionDto.getUpdatedBy());
						stockList.add(stock);
					}
				});
				if (!stockList.isEmpty()) {
					stockRepo.saveAll(stockList);
				}
			}
		} else {
		}
	}

}