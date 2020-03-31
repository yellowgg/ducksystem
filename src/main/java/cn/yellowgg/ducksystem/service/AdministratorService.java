package cn.yellowgg.ducksystem.service;

import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.mapper.AdministratorMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 14:30
 */
@Service
public class AdministratorService {

    @Resource
    private AdministratorMapper administratorMapper;


    public int deleteByPrimaryKey(Long id) {
        return administratorMapper.deleteByPrimaryKey(id);
    }


    public int insert(Administrator record) {
        return administratorMapper.insert(record);
    }


    public int insertOrUpdate(Administrator record) {
        return administratorMapper.insertOrUpdate(record);
    }


    public int insertOrUpdateSelective(Administrator record) {
        return administratorMapper.insertOrUpdateSelective(record);
    }


    public int insertSelective(Administrator record) {
        return administratorMapper.insertSelective(record);
    }


    public Administrator selectByPrimaryKey(Long id) {
        return administratorMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(Administrator record) {
        return administratorMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Administrator record) {
        return administratorMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<Administrator> list) {
        return administratorMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<Administrator> list) {
        return administratorMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<Administrator> list) {
        return administratorMapper.batchInsert(list);
    }

    public Administrator findByUserName(String userName) {
        return administratorMapper.findByUserName(userName);
    }

    public int updateLastLoginTime(Administrator administrator) {
        return administratorMapper.updateLastLoginTimeByIdAndUserName(
                LocalDateTime.now().withNano(UtilConstants.Number.ZERO),
                administrator.getId(), administrator.getUserName());
    }


}
