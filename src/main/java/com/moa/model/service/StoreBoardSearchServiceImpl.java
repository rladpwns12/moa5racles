package com.moa.model.service;

import com.moa.model.dao.StoreBoardDAO;
import com.moa.model.vo.DetailOptionVO;
import com.moa.model.vo.EntrustSearchVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Log4j
public class StoreBoardSearchServiceImpl implements StoreBoardSearchService {
	@Autowired
	private StoreBoardDAO storeBoardDAO;
	private static final String DONT_CARE="무관";
	private static final String LONG_DATE="장기(1달~1년)";
	private static final String SHORT_DATE="단기(1일~1달)";

	@Override
	public  List<EntrustSearchVO> search(DetailOptionVO detail){
		List<EntrustSearchVO> entrustAry;
		entrustAry=storeBoardDAO.searchEntrust(detail);

		for(EntrustSearchVO en : entrustAry) 
		{
			String distanceResult = (Double.toString(distance(detail.getLatitude(),detail.getLongitude(),
					en.getLatitude(),en.getLongitude(),"kilometer"))).substring(0,3);
			en.setDistanceResult(distanceResult);
			switch (en.getStoragePeriodTypeId()){
				case "3":en.setStoragePeriodTypeId(DONT_CARE);break;
				case "2":en.setStoragePeriodTypeId(LONG_DATE);break;
				case "1":en.setStoragePeriodTypeId(SHORT_DATE);break;
			}
		}
		return entrustAry;
	}
	  private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
	         
	        double theta = lon1 - lon2;
	        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	         
	        dist = Math.acos(dist);
	        dist = rad2deg(dist);
	        dist = dist * 60 * 1.1515;
	         
	        if (unit.equals("kilometer")) {
	            dist = dist * 1.609344;
	        } else if(unit.equals("meter")){
	            dist = dist * 1609.344;
	        }
	 
	        return (dist);
	    }
	    private double deg2rad(double deg) {
	        return (deg * Math.PI / 180.0);
	    }
	    private double rad2deg(double rad) {
	        return (rad * 180 / Math.PI);
	    }
}
