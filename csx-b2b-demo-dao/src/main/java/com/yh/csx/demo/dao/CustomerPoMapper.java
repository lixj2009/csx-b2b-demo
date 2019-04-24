package com.yh.csx.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yh.csx.demo.po.CustomerPo;
import com.yh.csx.demo.po.CustomerPoExample;
import com.yh.csx.demo.request.CustomerQo;
import com.yh.csx.demo.response.CustomerInfoVo;

public interface CustomerPoMapper {
    long countByExample(CustomerPoExample example);

    int deleteByExample(CustomerPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerPo record);

    int insertSelective(CustomerPo record);

    List<CustomerPo> selectByExampleWithBLOBs(CustomerPoExample example);

    List<CustomerPo> selectByExample(CustomerPoExample example);

    CustomerPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerPo record, @Param("example") CustomerPoExample example);

    int updateByExampleWithBLOBs(@Param("record") CustomerPo record, @Param("example") CustomerPoExample example);

    int updateByExample(@Param("record") CustomerPo record, @Param("example") CustomerPoExample example);

    int updateByPrimaryKeySelective(CustomerPo record);

    int updateByPrimaryKeyWithBLOBs(CustomerPo record);

    int updateByPrimaryKey(CustomerPo record);
    
    int selectCountByCustomerName(@Param("customerName") String customerName, @Param("customerId") Long customerId);

    /**
     * 查询客户信息列表
     *
     * @param qo
     * @return
     */
    List<CustomerInfoVo> selectCustomerInfoList(CustomerQo qo);
}