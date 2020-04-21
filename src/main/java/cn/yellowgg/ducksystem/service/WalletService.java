package cn.yellowgg.ducksystem.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.yellowgg.ducksystem.entity.Wallet;
import java.util.List;
import cn.yellowgg.ducksystem.mapper.WalletMapper;
/**
 * @Description:  
 * @Author: yellowgg
 * @Date: Created in 2020/4/21 23:43
 */
@Service
public class WalletService{

    @Resource
    private WalletMapper walletMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return walletMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Wallet record) {
        return walletMapper.insert(record);
    }

    
    public int insertOrUpdate(Wallet record) {
        return walletMapper.insertOrUpdate(record);
    }

    
    public int insertOrUpdateSelective(Wallet record) {
        return walletMapper.insertOrUpdateSelective(record);
    }

    
    public int insertSelective(Wallet record) {
        return walletMapper.insertSelective(record);
    }

    
    public Wallet selectByPrimaryKey(Long id) {
        return walletMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Wallet record) {
        return walletMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Wallet record) {
        return walletMapper.updateByPrimaryKey(record);
    }

    
    public int updateBatch(List<Wallet> list) {
        return walletMapper.updateBatch(list);
    }

    
    public int updateBatchSelective(List<Wallet> list) {
        return walletMapper.updateBatchSelective(list);
    }

    
    public int batchInsert(List<Wallet> list) {
        return walletMapper.batchInsert(list);
    }

}
