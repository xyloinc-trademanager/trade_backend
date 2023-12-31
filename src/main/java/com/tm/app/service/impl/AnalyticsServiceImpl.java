package com.tm.app.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tm.app.dto.DataFilter;
import com.tm.app.entity.Brand;
import com.tm.app.entity.Customer;
import com.tm.app.entity.CustomerPaymentSummary;
import com.tm.app.entity.CustomerSalesAndOrderAndPaymentSummary;
import com.tm.app.entity.CustomerSalesAndOrderSummary;
import com.tm.app.entity.DailyLeadGeneration;
import com.tm.app.entity.DailyPaymentSummary;
import com.tm.app.entity.DailyProductionCount;
import com.tm.app.entity.DailySalesAndOrdersAndPaymentCount;
import com.tm.app.entity.ItemMaster;
import com.tm.app.entity.LeadCountAndCreatedBy;
import com.tm.app.entity.LeadCountWithStatus;
import com.tm.app.entity.MonthlyPaymentSummary;
import com.tm.app.entity.MonthlyProductionCount;
import com.tm.app.entity.MonthlySalesAndOrdersAndPaymentCount;
import com.tm.app.entity.OrdersPerCustomer;
import com.tm.app.entity.ProductionAndUomByBrand;
import com.tm.app.entity.ProductionAndUomByItem;
import com.tm.app.entity.ProductionCount;
import com.tm.app.entity.ProductionCountByBrand;
import com.tm.app.entity.ProductionCountByItem;
import com.tm.app.entity.SalesAndOrderCount;
import com.tm.app.entity.SalesPerCustomer;
import com.tm.app.entity.TotalPaymentSummary;
import com.tm.app.entity.User;
import com.tm.app.entity.WeeklyLeadGeneration;
import com.tm.app.entity.WeeklyProductionCount;
import com.tm.app.entity.WeeklySalesAndOrdersAndPaymentCount;
import com.tm.app.entity.YearlyLeadGeneration;
import com.tm.app.repo.BrandRepo;
import com.tm.app.repo.CustomerPaymentSummaryRepo;
import com.tm.app.repo.CustomerRepo;
import com.tm.app.repo.CustomerSalesAndOrderAndPaymentRepo;
import com.tm.app.repo.CustomerSalesAndOrderSummaryRepo;
import com.tm.app.repo.DailyLeadGenerationRepo;
import com.tm.app.repo.DailyPaymentSummaryRepo;
import com.tm.app.repo.DailyProductionCountRepo;
import com.tm.app.repo.DailySalesAndOrdersAndPaymentRepo;
import com.tm.app.repo.ItemMasterRepo;
import com.tm.app.repo.LeadCountAndCreatedByRepo;
import com.tm.app.repo.LeadCountWithStatusRepo;
import com.tm.app.repo.MonthlyPaymentSummaryRepo;
import com.tm.app.repo.MonthlyProductionCountRepo;
import com.tm.app.repo.MonthlySalesAndOrdersAndPaymentRepo;
import com.tm.app.repo.OrderPerCustomerRepo;
import com.tm.app.repo.ProductionAndUomByBrandRepo;
import com.tm.app.repo.ProductionAndUomByitemRepo;
import com.tm.app.repo.ProductionCountAndUomRepo;
import com.tm.app.repo.ProductionCountByBrandRepo;
import com.tm.app.repo.ProductionCountRepo;
import com.tm.app.repo.SalesAndOrderCountRepo;
import com.tm.app.repo.SalesPerCustomerRepo;
import com.tm.app.repo.TotalPaymentSummaryRepo;
import com.tm.app.repo.UserRepository;
import com.tm.app.repo.WeeklyLeadGenerationRepo;
import com.tm.app.repo.WeeklyProductionCountRepo;
import com.tm.app.repo.WeeklySalesAndOrdersAndPaymentRepo;
import com.tm.app.repo.YearlyLeadGenerationRepo;
import com.tm.app.service.AnalyticsService;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	private DailyPaymentSummaryRepo dailyPaymentSummaryRepo;

	@Autowired
	private MonthlyPaymentSummaryRepo monthlyPaymentSummaryRepo;

	@Autowired
	private CustomerPaymentSummaryRepo customerPaymentSummaryRepo;

	@Autowired
	private SalesAndOrderCountRepo salesAndOrderCountRepo;

	@Autowired
	private CustomerSalesAndOrderSummaryRepo customerSalesAndOrderSummaryRepo;

	@Autowired
	private TotalPaymentSummaryRepo totalPaymentSummaryRepo;

	@Autowired
	private SalesPerCustomerRepo salesPerCustomerRepo;

	@Autowired
	private OrderPerCustomerRepo orderPerCustomerRepo;

	@Autowired
	private DailyLeadGenerationRepo dailyLeadGenerationRepo;

	@Autowired
	private WeeklyLeadGenerationRepo weeklyLeadGenerationRepo;

	@Autowired
	private YearlyLeadGenerationRepo yearlyLeadGenerationRepo;

	@Autowired
	private LeadCountWithStatusRepo leadwithStatusRepo;

	@Autowired
	private LeadCountAndCreatedByRepo leadCountAndCreatedByRepo;

	@Autowired
	private WeeklySalesAndOrdersAndPaymentRepo weeklyOrdersCountRepo;

	@Autowired
	private MonthlySalesAndOrdersAndPaymentRepo monthlyOrdersCountRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CustomerSalesAndOrderAndPaymentRepo customerSalesAndOrderAndPaymentRepo;

	@Autowired
	private DailySalesAndOrdersAndPaymentRepo dailySalesAndOrdersCountRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductionCountRepo productionCountRepo;

	@Autowired
	private BrandRepo brandRepo;

	@Autowired
	private MonthlyProductionCountRepo monthlyProductionCountRepo;

	@Autowired
	private WeeklyProductionCountRepo weeklyProductionCountRepo;

	@Autowired
	private ProductionCountByBrandRepo productionCountByBrandRepo;

	@Autowired
	private DailyProductionCountRepo dailyProductionCountRepo;

	@Autowired
	private ProductionCountAndUomRepo productionCountAndUomRepo;

	@Autowired
	private ItemMasterRepo itemMasterRepo;

	@Autowired
	private ProductionAndUomByBrandRepo productionAndUomByBrandRepo;

	@Autowired
	private ProductionAndUomByitemRepo productionAndUomByitemRepo;

	@Override
	public List<DailyPaymentSummary> getDailyPaymentSummary() {
		return dailyPaymentSummaryRepo.getDailyPaymentSummary();
	}

	@Override
	public List<MonthlyPaymentSummary> getMonthlyPaymentSummary() {
		return monthlyPaymentSummaryRepo.getMonthlyPaymentSummary();
	}

	@Override
	public List<CustomerPaymentSummary> getCustomerPaymentSummary() {
		return customerPaymentSummaryRepo.getCustomerPaymentSummary();
	}

	@Override
	public List<SalesAndOrderCount> getSalesAndOrdersCount() {
		return salesAndOrderCountRepo.getSalesAndOrdersCount();
	}

	@Override
	public Page<CustomerSalesAndOrderSummary> getCustomerSalesAndOdersCount(DataFilter dataFilter) {
		return customerSalesAndOrderSummaryRepo.getCustomerSalesAndOrderSummary(
				PageRequest.of(dataFilter.getPage(), dataFilter.getSize()), dataFilter.getSearch());
	}

	@Override
	public List<TotalPaymentSummary> getTotalPaymentSummary() {
		return totalPaymentSummaryRepo.getTotalPaymentSummary();
	}

	@Override
	public List<SalesPerCustomer> getSalesPerCustomer(Integer year) {
		return salesPerCustomerRepo.getSalesPerCustomer(year);
	}

	@Override
	public List<OrdersPerCustomer> getOrdersPerCustomer(Integer year) {
		return orderPerCustomerRepo.getOrdersPerCustomer(year);
	}

	@Override
	public List<DailyLeadGeneration> getDailyLeadGeneration() {
		return dailyLeadGenerationRepo.getDailyLeadGeneration();
	}

	@Override
	public List<WeeklyLeadGeneration> getWeeklyLeadGeneration() {
		return weeklyLeadGenerationRepo.getWeeklyLeadGeneration();
	}

	@Override
	public List<YearlyLeadGeneration> getYearlyLeadGeneration() {
		return yearlyLeadGenerationRepo.getYearlyLeadGeneration();
	}

	@Override
	public List<LeadCountAndCreatedBy> getLeadGenerationAndCreatedBy() {
		return leadCountAndCreatedByRepo.getLeadGenerationAndCreatedBy();
	}

	@Override
	public List<LeadCountWithStatus> getLeadGenerationStatusCount() {
		return leadwithStatusRepo.getLeadGenerationStatusCount();
	}

	@Override
	public List<WeeklySalesAndOrdersAndPaymentCount> getWeeklySalesAndOrdersAndPaymentCount() {
		return weeklyOrdersCountRepo.getWeeklySalesAndOrdersAndPaymentCount();
	}

	@Override
	public List<MonthlySalesAndOrdersAndPaymentCount> getMonthlySalesAndOrdersAndPaymentCount() {
		return monthlyOrdersCountRepo.getMonthlySalesAndOrdersAndPaymentCount();
	}

	@Override
	public List<CustomerSalesAndOrderAndPaymentSummary> getCustomerSalesAndOrderAndPayment(Long id) {
		if (Objects.nonNull(id)) {
			Customer customer = customerRepo.findById(id).orElseThrow();
			return customerSalesAndOrderAndPaymentRepo.getCustomerSalesAndOrderAndPaymentByCustomer(customer.getId());
		} else {
			return customerSalesAndOrderAndPaymentRepo.getCustomerSalesAndOrderAndPayment();
		}
	}

	@Override
	public List<CustomerPaymentSummary> getCustomerPaymentSummary(Long id) {
		if (Objects.nonNull(id)) {
			Customer customer = customerRepo.findById(id).orElseThrow();
			return customerPaymentSummaryRepo.getCustomerPaymentSummaryByCustomer(customer.getId());
		} else {
			return customerPaymentSummaryRepo.getCustomerPaymentSummary();
		}
	}

	@Override
	public List<DailySalesAndOrdersAndPaymentCount> getDailySalesAndOrdersCount() {
		return dailySalesAndOrdersCountRepo.getDailySalesAndOrdersCount();
	}

	@Override
	public CustomerSalesAndOrderAndPaymentSummary getCustomerSalesAndOrderAndPayments(Long id) {
		User user = userRepository.findById(id).orElseThrow();
		Customer customer = customerRepo.findById(user.getUserId()).orElseThrow();
		return customerSalesAndOrderAndPaymentRepo.getCustomerSalesAndOrderAndPayments(customer.getId());
	}

	@Override
	public List<ProductionCount> getProductionCount(Long id) {
		if (Objects.nonNull(id)) {
			Brand brand = brandRepo.findById(id).orElseThrow();
			return productionCountRepo.getProductionCountByBrand(brand.getId());
		} else {
			return productionCountRepo.getProductionCount(id);
		}
	}

	@Override
	public List<MonthlyProductionCount> getMonthlyProductionCount() {
		return monthlyProductionCountRepo.getMonthlyProductionCount();
	}

	@Override
	public List<WeeklyProductionCount> getWeeklyProductionCount() {
		return weeklyProductionCountRepo.getWeeklyProductionCount();
	}

	@Override
	public List<ProductionCountByBrand> getProductionCountByBrand(Long id) {
		if (Objects.nonNull(id)) {
			Brand brand = brandRepo.findById(id).orElseThrow();
			return productionCountByBrandRepo.getProductionCountByBrand(brand.getId());
		} else {
			return productionCountByBrandRepo.getProductionCountByBrand();
		}
	}

	@Override
	public List<DailyProductionCount> getDailyProductionCount() {
		return dailyProductionCountRepo.getDailyProductionCount();
	}

	@Override
	public List<ProductionCountByItem> getProductionCountByItem(Long id) {
		if (Objects.nonNull(id)) {
			ItemMaster itemMaster = itemMasterRepo.findById(id).orElseThrow();
			return productionCountAndUomRepo.getProductionCountByItem(itemMaster.getId());
		}
		return productionCountAndUomRepo.getProductionCounts();
	}

	@Override
	public List<ProductionAndUomByBrand> getProductionAndUomByBrand(Long id) {
		if (Objects.nonNull(id)) {
			Brand brand = brandRepo.findById(id).orElseThrow();
			return productionAndUomByBrandRepo.getProductionAndUomByBrand(brand.getId());
		} else {
			return productionAndUomByBrandRepo.getAllProductionAndUomByBrand();
		}
	}

	@Override
	public List<ProductionAndUomByItem> getProductionAndUomByItem(Long id) {
		if (Objects.nonNull(id)) {
			ItemMaster itemMaster = itemMasterRepo.findById(id).orElseThrow();
			return productionAndUomByitemRepo.getProductionAndUomByItem(itemMaster.getId());
		} else {
			return productionAndUomByitemRepo.getAllProductionAndUomByItem();
		}
	}

}
