package com.qzdsoft.eshop.vo.deliverytemplate;

import java.util.ArrayList;
import java.util.List;

import com.qzdsoft.eshop.domain.deliverytemplate.DeliveryTemplate;

public class DeliveryTemplateEditInfo extends DeliveryTemplate {
	
	private List<CarryWayEditInfo> carryModel = new ArrayList<CarryWayEditInfo>();

	public List<CarryWayEditInfo> getCarryModel() {
		return carryModel;
	}

	public void setCarryModel(List<CarryWayEditInfo> carryModel) {
		this.carryModel = carryModel;
	}
	
	
}
