package com.qzdsoft.eshop.vo.goodsclass;

import com.qzdsoft.vo.GoodsStatus;

public class GoodsClassProInfo {

		private Integer propertyId;

	    private String name;
	    
	    private Integer deleteStatus;
	    
	    private String className;

		public Integer getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(Integer propertyId) {
			this.propertyId = propertyId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDeleteStatus() {
			if(GoodsStatus.USE_CODE.equals(this.deleteStatus.toString())) {
				return GoodsStatus.USE.getValue();
			}
			return GoodsStatus.DELET.getValue();
		}

		public void setDeleteStatus(Integer deleteStatus) {
			this.deleteStatus = deleteStatus;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}
	    
	    
}
