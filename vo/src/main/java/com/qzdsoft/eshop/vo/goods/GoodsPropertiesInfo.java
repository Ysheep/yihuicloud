/**
 * 
 */
package com.qzdsoft.eshop.vo.goods;

import java.util.List;

import com.qzdsoft.eshop.vo.goods.pc.GoodsSpecInfo;
import com.qzdsoft.vo.TypeInfo;

/**
 * 商品属性信息
 *
 * <p>商品属性信息 （前台展示用）
 * @author pc-20170420 2017年11月17日
 * @see
 * @since 1.0
 */
public class GoodsPropertiesInfo
{
    private TypeInfo prop;
    private List<GoodsSpecInfo> specs;//属性值
    
    /**
     * @return the prop
     */
    public TypeInfo getProp()
    {
        return prop;
    }
    /**
     * @param prop the prop to set
     */
    public void setProp(TypeInfo prop)
    {
        this.prop = prop;
    }
   
    /**
     * @return the specs
     */
    public List<GoodsSpecInfo> getSpecs()
    {
        return specs;
    }
    /**
     * @param specs the specs to set
     */
    public void setSpecs(List<GoodsSpecInfo> specs)
    {
        this.specs = specs;
    }
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "GoodsPropertiesInfo [prop=" + prop + ", specs=" + specs + "]";
    }
  
}
