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


    //region 增
    int insert(IntegralRecord record);

    int insertOrUpdate(IntegralRecord record);

    int insertOrUpdateSelective(IntegralRecord record);

    int insertSelective(IntegralRecord record);

    int batchInsert(@Param("list") List<IntegralRecord> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(IntegralRecord record);

    int updateByPrimaryKey(IntegralRecord record);

    int updateBatch(List<IntegralRecord> list);

    int updateBatchSelective(List<IntegralRecord> list);
    //endregion

    //region 查
    IntegralRecord selectByPrimaryKey(Long id);

    List<IntegralRecord> findByWalletIdOrderByIdDesc(@Param("walletId") Long walletId);
    //endregion


}