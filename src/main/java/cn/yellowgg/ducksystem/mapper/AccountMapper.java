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
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Account record);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Account record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Account selectByPrimaryKey(Long id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int updateBatchSelective(List<Account> list);

    int batchInsert(@Param("list") List<Account> list);
}