package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.Integralrecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:59
 */
public interface IntegralrecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Integralrecord record);

    int insertOrUpdate(Integralrecord record);

    int insertOrUpdateSelective(Integralrecord record);

    int insertSelective(Integralrecord record);

    Integralrecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Integralrecord record);

    int updateByPrimaryKey(Integralrecord record);

    int updateBatch(List<Integralrecord> list);

    int updateBatchSelective(List<Integralrecord> list);

    int batchInsert(@Param("list") List<Integralrecord> list);
}