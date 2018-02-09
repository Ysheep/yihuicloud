/**
 * 
 */
package com.qzdsoft.eshop.vo.goodsclass;

import com.qzdsoft.eshop.domain.goodsclass.GoodsClass;
import com.qzdsoft.vo.GoodsClassStatus;
import com.qzdsoft.vo.ShowIndexStatus;

/**
 * simple introduction
 *
 * <p>detailed comment
 * @author pc-20170422 2017年11月14日
 * @see
 * @since 1.0
 */
public class GoodsClassInfo extends GoodsClass{
	
	
	private String pidName;
	
	private String status;
	
	private String imageUrl;
	
	private String iconUrl;
	
	private String isShowIndex;
	
	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	public String getStatus() {
		if(super.getDeleteStatus() == Integer.parseInt(GoodsClassStatus.USE_CODE)) {
			status =GoodsClassStatus.USE.getValue();
		}else{
			status =GoodsClassStatus.DELET.getValue();
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIsShowIndex() {
		if(super.getShowIndex() == Integer.parseInt(ShowIndexStatus.IS_SHOW_CODE)) {
			isShowIndex =ShowIndexStatus.IS_SHOW.getValue();
		}else{
			isShowIndex =ShowIndexStatus.NOT_SHOW.getValue();
		}
		return isShowIndex;
	}

	public void setIsShowIndex(String isShowIndex) {
		this.isShowIndex = isShowIndex;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	
	
}
