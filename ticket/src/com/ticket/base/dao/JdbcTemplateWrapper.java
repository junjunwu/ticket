package com.ticket.base.dao;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ticket.base.page.Pagination;
import com.ticket.base.page.QueryPageObject;





/**
 * Spring jdbcTemplate 包装器类，提供基本SQL操作
 * 
 * @author flatychen
 * 
 */
@Component("jdbcTemplateWrapper")
public class JdbcTemplateWrapper {

	private static Logger log = Logger.getLogger(JdbcTemplateWrapper.class);

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final <T> List<T> query(String sql, Class<T> clazz, Object args[]) {
		List<T> l = null;
		try {
			if (log.isInfoEnabled()) {
				log.info(MessageFormat.format(
						"======>>query'{' sql:{1}, args:{3} '}'", 1, sql, 3,
						Arrays.toString(args)));
			}
			l = this.jdbcTemplate.query(sql, args,
					new BeanPropertyRowMapper<T>(clazz));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return l;

	}
	
	public void doExcute(String sql){
		jdbcTemplate.execute(sql);
	}
	
	
	/**
	 * 取得sequenceId
	 * @param seqName
	 * @return sequenceId
	 * @author YangChongdi
	 * @date 2014-4-29
	 */
	public Long getSequenceId(String seqName) {
		Assert.notNull(seqName);
		StringBuilder sb = new StringBuilder("SELECT ").append(seqName.trim()).append(".nextval FROM dual ");
		return this.queryForLong(sb.toString(), null);
	}

	/**
	 * class得到对象
	 * 
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final <T> T queryForBean(String sql, Class<T> clazz, Object args[]) {
		List<T> l = this.query(sql, clazz, args);
		if ((l != null) && (l.size() != 0)) {
			return l.get(0);
		}
		return null;
	}

	/**
	 * class得到对象list
	 * 
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final <T> List<T> queryForBeanList(String sql, Class<T> clazz,
			Object args[]) {
		return this.query(sql, clazz, args);

	}

	/**
	 * 返回 long --> count(1)
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final Long queryForLong(String sql, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>queryForLong[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		try {
			return this.jdbcTemplate.queryForLong(sql, args);
		} catch (DataAccessException e) {
			return null;
		}

	}

	/**
	 * 返回 int -->count(1)
	 * 
	 * @param sql
	 * @param args
	 * @return
	 * @author flatychen
	 * @date 2014-1-10
	 */
	public final int queryForInt(String sql, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>queryForInt[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		try {
			return this.jdbcTemplate.queryForInt(sql, args);
		} catch (DataAccessException e) {
			return 0;
		}

	}

	/**
	 * 
	 * @author flatychen
	 * @param sql
	 * @param args
	 * @return
	 * @date 2014-1-12
	 */
	public int saveORUpdate(String sql, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>saveORUpdate[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(args)));
		}
		int num = 0;
		try {
			num = jdbcTemplate.update(sql, args);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return num;
	}

	/**
	 * 批量更新
	 * 
	 * @author flatychen
	 * @param sql
	 * @param listArgs
	 * @return
	 */
	public int batchUpdate(String sql, List<Object[]> listArgs) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>batchInsert[ sql:[ {1} ] , args:[ {3} ]  ]", 1,
					sql, 3, Arrays.toString(listArgs.toArray())));
		}
		int num[] = null;
		try {
			num = jdbcTemplate.batchUpdate(sql, listArgs);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw e;
		}
		return num.length;
	}

	/**
	 *  分页方法 
	 * @author flatychen
	 * @param pageSize 页大小，当为-1时，查出所有数据
	 * @param toPage
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public <T> Pagination<T> queryForPage(QueryPageObject pageQuery, String sql,
			Class<T> clazz, Object[] args) {
		String countSql = Pagination.countSql(sql);
		String querySql = Pagination.pageSql(sql);
		int totalCount = this.queryForInt(countSql, args);
		Pagination<T> page = new Pagination<T>(pageQuery, totalCount);
		if(pageQuery.getPageSize()!=-1){
			
			Object[] newArgs = ArrayUtils.addAll(args, new Object[]{(pageQuery.getPageNo() - 1) * pageQuery.getPageSize(),pageQuery.getPageNo() * pageQuery.getPageSize()});
			args = null;
			
			List<T> list = this.queryForBeanList(querySql, clazz, newArgs);
			page.setObjLists(list);
			
		}
		else{
			List<T> list = this.queryForBeanList(sql, clazz, args);
			page.setObjLists(list);
		}
		return page;
	}
	
	/**
	 *  分页方法 
	 * @author flatychen
	 * @param pageSize 页大小，当为-1时，查出所有数据
	 * @param toPage
	 * @param sql
	 * @param clazz
	 * @param args
	 * @return
	 */
	public  Pagination<Map<String, Object>> queryForMapPage(QueryPageObject pageQuery, String sql,
			 Object[] args) {
		String countSql = Pagination.countSql(sql);
		String querySql = Pagination.pageSql(sql);
		int totalCount = this.queryForInt(countSql, args);
		Pagination<Map<String, Object>> page = new Pagination<Map<String, Object>>(pageQuery, totalCount);
		if(pageQuery.getPageSize()!=-1){
			
			Object[] newArgs = ArrayUtils.addAll(args, new Object[]{(pageQuery.getPageNo() - 1) * pageQuery.getPageSize(),pageQuery.getPageNo() * pageQuery.getPageSize()});
			args = null;
			
			page.setObjLists(this.jdbcTemplate.queryForList(querySql, newArgs));
			
		}
		else{
			page.setObjLists(this.jdbcTemplate.queryForList(querySql, args));
		}
		return page;
	}

}
