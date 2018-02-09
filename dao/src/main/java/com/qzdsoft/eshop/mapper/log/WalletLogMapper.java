package com.qzdsoft.eshop.mapper.log;

import java.util.List;

import com.qzdsoft.eshop.domain.log.WalletLog;
import com.qzdsoft.eshop.util.MyMapper;
import com.qzdsoft.eshop.vo.log.WalletLogInfo;

public interface WalletLogMapper extends MyMapper<WalletLog> {
	
	List<WalletLogInfo> findWallet(Integer userId);
}