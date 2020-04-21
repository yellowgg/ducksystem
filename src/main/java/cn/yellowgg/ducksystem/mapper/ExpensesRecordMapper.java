package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.ExpensesRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/22 00:43
 */
public interface ExpensesRecordMapper {


    //region 增
    int insert(ExpensesRecord record);

    int insertOrUpdate(ExpensesRecord record);

    int insertOrUpdateSelective(ExpensesRecord record);

    int insertSelective(ExpensesRecord record);

    int batchInsert(@Param("list") List<ExpensesRecord> list);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 查
    ExpensesRecord selectByPrimaryKey(Long id);

    List<ExpensesRecord> findByWalletId(@Param("walletId") Long walletId);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(ExpensesRecord record);

    int updateByPrimaryKey(ExpensesRecord record);

    int updateBatch(List<ExpensesRecord> list);

    int updateBatchSelective(List<ExpensesRecord> list);
    //endregion

}