package cn.yellowgg.ducksystem.mapper;

import cn.yellowgg.ducksystem.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: yellowgg
 * @Date: Created in 2020/4/14 15:36
 */
public interface AccountMapper {

    //region 增
    int insert(Account record);

    int batchInsert(@Param("list") List<Account> list);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    int insertSelective(Account record);
    //endregion

    //region 删
    int deleteByPrimaryKey(Long id);
    //endregion

    //region 改
    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int updateBatchSelective(List<Account> list);
    //endregion

    //region 查
    Account selectByPrimaryKey(Long id);

    Account findByOpenId(@Param("openId") String openId);

    List<Account> findByNickNameNotIsAdmin(@Param("nickName") String nickName);

    //endregion

}