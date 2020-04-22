package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.IntegralRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:59
 */
public interface IntegralrecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralRecord record);

    int insertOrUpdate(IntegralRecord record);

    int insertOrUpdateSelective(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    IntegralRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    int updateBatch(List<IntegralRecord> list);

    int updateBatchSelective(List<IntegralRecord> list);

    int batchInsert(@Param("list") List<IntegralRecord> list);
}