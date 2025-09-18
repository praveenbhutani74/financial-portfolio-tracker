package com.financialtracker.financial_portfolio_tracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.financialtracker.financial_portfolio_tracker.model.AssestsRequest;
import com.financialtracker.financial_portfolio_tracker.model.AssestsResponse;

@Service
public interface AssestsService {
	
	AssestsResponse createAssests(AssestsRequest assestsRequest);
	
	AssestsResponse updateAssets(AssestsRequest assestsRequest , Integer id);
	
	List<AssestsResponse> fetchAllAssests();
	
	String deleteAssests(Integer id);

}
;
