package cn.yellowgg.ducksystem.service;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.yellowgg.ducksystem.constant.UtilConstants;
import cn.yellowgg.ducksystem.entity.perm.Administrator;
import cn.yellowgg.ducksystem.mapper.AdminandroleMapper;
import cn.yellowgg.ducksystem.mapper.AdministratorMapper;
import cn.yellowgg.ducksystem.utils.ShiroUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 14:30
 */
@Service
public class AdministratorService {

    @Resource
    AdministratorMapper administratorMapper;
    @Resource
    AdminandroleMapper adminandroleMapper;
    @Autowired
    AdminAndRoleService adminAndRoleService;

    public int deleteByPrimaryKey(Long id) {
        adminandroleMapper.deleteByAdminId(id);
        return administratorMapper.deleteByPrimaryKey(id);
    }

    public int insert(Administrator record) {
        return administratorMapper.insert(record);
    }

    public int insertOrUpdate(Administrator record) {
        return administratorMapper.insertOrUpdate(record);
    }

    public int insertOrUpdateSelective(Administrator admin, Collection<Long> releIds) {
        Collection<Long> releIdsSet = Optional.ofNullable(releIds).orElseGet(() -> Sets.newHashSet());
        // 密码再加密三次
        admin.setPassword(ShiroUtils.createMD5Pwd(admin.getPassword(), UtilConstants.Number.THREE));
        // 插入或更改管理员
        administratorMapper.insertOrUpdateSelective(admin);
        adminandroleMapper.deleteByAdminId(admin.getId());
        return CollectionUtils.isEmpty(releIdsSet)
                ? UtilConstants.Number.ONE
                : adminandroleMapper.batchInsert(adminAndRoleService.getListOfRoleIdsAndOneAdmin(releIdsSet, admin.getId()));
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
        if (StrUtil.isNotBlank(admin.getUserName())) {
            map.put("userName", admin.getUserName());
        }
        if (Objects.nonNull(admin.getId())) {
            map.put("id", admin.getId());
        }
        if (StrUtil.isNotBlank(admin.getPassword())) {
            map.put("password", admin.getPassword());
        }
        if (StrUtil.isNotBlank(admin.getEmail())) {
            map.put("email", admin.getEmail());
        }
        return map;
    }

    public PageInfo<Administrator> findByAllSelectivewithPage(int page, int pageSize, Administrator administrator) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(administratorMapper.findByAllSelective(administrator));
    }
}
