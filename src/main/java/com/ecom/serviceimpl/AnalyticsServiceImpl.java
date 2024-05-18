package com.ecom.serviceimpl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.constants.OrderStatus;
import com.ecom.dto.AnalyticsResponseDto;
import com.ecom.entity.Order;
import com.ecom.repo.OrderRepo;
import com.ecom.service.AnalyticsService;
@Service
public class AnalyticsServiceImpl implements AnalyticsService {
	
	@Autowired
private	OrderRepo orderRepo;
	
	public AnalyticsResponseDto getAnalytics() {

	LocalDate currentDate = LocalDate.now();
	LocalDate previousmonthDate =currentDate.minusMonths(1);
	
	Long currentMonthOrder = getTotalOrdersForMonth(currentDate.getMonthValue(),currentDate.getYear());
	Long previousMonthOrder = getTotalOrdersForMonth(previousmonthDate.getMonthValue(),previousmonthDate.getYear());
	
	Long currentMonthEarning = getTotalEarningForMonth(currentDate.getMonthValue(),currentDate.getYear());
	Long previousMonthEarning = getTotalEarningForMonth(previousmonthDate.getMonthValue(),previousmonthDate.getYear());
	
	Long placed=orderRepo.countByOrderStatus(OrderStatus.Placed);
	
	 Long shipped=orderRepo.countByOrderStatus(OrderStatus.Shipped);
	
	 Long delivered=orderRepo.countByOrderStatus(OrderStatus.Delivered);
	 
	 return new AnalyticsResponseDto(placed,shipped,delivered,currentMonthOrder,previousMonthOrder,
			 currentMonthEarning,previousMonthEarning);
	}
	
	private Long getTotalEarningForMonth(int month, int year) {
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(calendar.YEAR, year);
		calendar.set(calendar.MONTH, month-1);
		calendar.set(calendar.DAY_OF_MONTH, 0);
		calendar.set(calendar.HOUR_OF_DAY, 0);
		calendar.set(calendar.MINUTE, 0);
		calendar.set(calendar.SECOND, 0);
		
		Date startOfMonth = calendar.getTime();
		
		calendar.set(calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		calendar.set(calendar.HOUR_OF_DAY, 23);
		calendar.set(calendar.MINUTE, 59);
		calendar.set(calendar.SECOND, 59);
		
		Date endOfMonth = calendar.getTime();
		
		List<Order> orders= orderRepo.findByDateBetweenAndOrderStatus(startOfMonth,endOfMonth,OrderStatus.Delivered);
		
		Long sum=0L;
		
		for (Order order : orders) {
			sum +=order.getAmount();
		}
		return sum;
	}

	private Long getTotalOrdersForMonth(int month, int year) {
		Calendar calendar=Calendar.getInstance();
		calendar.set(calendar.YEAR, year);
		calendar.set(calendar.MONTH, month-1);
		calendar.set(calendar.DAY_OF_MONTH, 0);
		calendar.set(calendar.HOUR_OF_DAY, 0);
		calendar.set(calendar.MINUTE, 0);
		calendar.set(calendar.SECOND, 0);
		
		Date startOfMonth = calendar.getTime();
		
		calendar.set(calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
		calendar.set(calendar.HOUR_OF_DAY, 23);
		calendar.set(calendar.MINUTE, 59);
		calendar.set(calendar.SECOND, 59);
		
		Date endOfMonth = calendar.getTime();
		
		List<Order> orders= orderRepo.findByDateBetweenAndOrderStatus(startOfMonth,endOfMonth,OrderStatus.Delivered);
		
		return (long)orders.size();
	}
}
