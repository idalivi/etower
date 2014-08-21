package org.etower.core.manager;

import java.util.List;

import org.etower.core.domain.Ftp;
import org.etower.core.repository.FtpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class FtpMng {

	public List<Ftp> getList() {
		return (List<Ftp>) ftpDao.findAll();
	}

	public Page<Ftp> getPage(int pageNo, int pageSize, String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNo, pageSize,
				sortType);

		return ftpDao.findAll(pageRequest);
	}

	public Ftp findById(Long id) {
		Ftp entity = ftpDao.findOne(id);
		return entity;
	}

	@Transactional(readOnly = false)
	public Ftp save(Ftp bean) {
		ftpDao.save(bean);
		return bean;
	}

	@Transactional(readOnly = false)
	public Ftp update(Ftp bean) {
		ftpDao.save(bean);
		return bean;
	}

	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		ftpDao.deleteById(id);
	}

	@Transactional(readOnly = false)
	public void deleteByIds(Long[] ids) {
		for (int i = 0, len = ids.length; i < len; i++) {
			deleteById(ids[i]);
		}
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNo, int pagzSize,
			String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNo - 1, pagzSize, sort);
	}

	private FtpDao ftpDao;

	@Autowired
	public void setFtpDao(FtpDao ftpDao) {
		this.ftpDao = ftpDao;
	}
}
