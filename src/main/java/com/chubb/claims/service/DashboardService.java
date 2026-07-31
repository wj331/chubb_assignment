package com.chubb.claims.service;

import com.chubb.claims.dto.response.DashboardResponse;
import com.chubb.claims.enums.Market;

public interface DashboardService {

    DashboardResponse getDashboard(
            Market market);

}