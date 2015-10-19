
package com.cms.service;

import com.cms.entity.Config;
import org.springframework.stereotype.Service;

/**
 * 网站配置
 * 
 * @author Zhangjiale
 * 
 */
@Service
public interface ConfigService {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加配置
	 * 
	 * @param key
	 * @param value
	 * @return Config
	 */
	public Config addConfig(String key, String value) ;

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除配置
	 * 
	 * @param key
	 * @return Integer
	 */
	public int deleteConfigByKey(String key);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 更新配置
	 * 
	 * @param key
	 * @param value
	 */
	public Config updagteConfigByKey(String key, String value);

	/**
	 * @param key
	 * @return
	 */
	public String getStringByKey(String key) ;

	/**
	 * @param key
	 * @return
	 */
	public int getIntKey(String key) ;
}
