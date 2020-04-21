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
    int deleteByPrimaryKey(Long id);

    int insert(Wallet record);

    int insertOrUpdate(Wallet record);

    int insertOrUpdateSelective(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

    int updateBatch(List<Wallet> list);

    int updateBatchSelective(List<Wallet> list);

    int batchInsert(@Param("list") List<Wallet> list);
}