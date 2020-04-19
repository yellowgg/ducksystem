package cn.yellowgg.ducksystem.service;


import cn.hutool.core.map.MapUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.mapper.AdministratorMapper;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 14:30
 */
@Service
public class AdministratorService {

    @Resource
    AdministratorMapper administratorMapper;

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
        return administratorMapper.findBySeleceive(MapUtil.of("userName", userName));
    }

    public int updateLastLoginTime(Administrator administrator) {
        return administratorMapper.updateLastLoginTimeByIdAndUserName(
                LocalDateTime.now().withNano(UtilConstants.Number.ZERO),
                administrator.getId(), administrator.getUserName());
    }


    public int updateRealNameAndEmailById(Administrator admin) {
        return administratorMapper.updateRealNameAndEmailById(admin.getRealName(), admin.getEmail(), admin.getId());
    }

    public Administrator findByIdAndPasswordAndUserName(Administrator admin) {
        return administratorMapper.findBySeleceive(getAdminNonNullMap(admin));
    }

    public int updatePasswordById(Administrator admin) {
        return administratorMapper.updatePasswordByIdOrUserName(getAdminNonNullMap(admin));
    }

    private HashMap<String, Object> getAdminNonNullMap(Administrator admin) {
        HashMap<String, Object> map = Maps.newHashMap();
        if (Objects.nonNull(admin.getUserName())) {
            map.put("userName", admin.getUserName());
        }
        if (Objects.nonNull(admin.getId())) {
            map.put("id", admin.getId());
        }
        if (Objects.nonNull(admin.getPassword())) {
            map.put("password", admin.getPassword());
        }
        return map;
    }
}
