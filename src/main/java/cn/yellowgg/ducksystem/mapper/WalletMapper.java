package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.Wallet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/21 23:43
 */
public interface WalletMapper {

    //region 查
    Wallet selectByPrimaryKey(Long id);

    Wallet findByAccountId(@Param("accountId") Long accountId);

    Long findIdByAccountId(@Param("accountId") Long accountId);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

    int updateBatch(List<Wallet> list);

    int updateBatchSelective(List<Wallet> list);
    //endregion

    //region 增
    int insert(Wallet record);

    int insertOrUpdate(Wallet record);

    int insertOrUpdateSelective(Wallet record);

    int insertSelective(Wallet record);

    int batchInsert(@Param("list") List<Wallet> list);
    //endregion

}