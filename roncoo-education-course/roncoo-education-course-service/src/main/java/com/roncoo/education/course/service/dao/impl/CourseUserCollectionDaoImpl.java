
package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionCountBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserCollectionDelBO;
import com.roncoo.education.course.service.dao.CourseUserCollectionDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseUserCollectionMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollection;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserCollectionExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  服务实现类
 *
 * @author ${author}
 * @since 2020-01-04
 */
@Repository
public class CourseUserCollectionDaoImpl implements CourseUserCollectionDao {

    @Autowired
    private CourseUserCollectionMapper courseUserCollectionMapper;

    @Override
    public int save(CourseUserCollection record) {
    record.setId(IdWorker.getId());
    return this.courseUserCollectionMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
    return this.courseUserCollectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CourseUserCollection record) {
    return this.courseUserCollectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CourseUserCollection getById(Long id) {
    return this.courseUserCollectionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CourseUserCollection> listForPage(int pageCurrent, int pageSize, CourseUserCollectionExample example) {
        int count = this.courseUserCollectionMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseUserCollection>(count, totalPage, pageCurrent, pageSize, this.courseUserCollectionMapper.selectByExample(example));
    }

    @Override
    public CourseUserCollection getByUserNoAndCourseId(Long userNo, Long courseId) {
        CourseUserCollectionExample example = new CourseUserCollectionExample();
        CourseUserCollectionExample.Criteria c =example.createCriteria();
        c.andUserNoEqualTo(userNo).andCourseIdEqualTo(courseId);
        List<CourseUserCollection> list = this.courseUserCollectionMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<CourseUserCollection> getCollectionListByUserNo(Long userNo) {
        CourseUserCollectionExample example = new CourseUserCollectionExample();
        CourseUserCollectionExample.Criteria c =example.createCriteria();
        c.andUserNoEqualTo(userNo);
        List<CourseUserCollection> list = this.courseUserCollectionMapper.selectByExample(example);
        return list;
    }

    @Override
    public int deleteByCourseIdAndUserNo(AuthCourseUserCollectionDelBO authCourseUserCollectionDelBO) {
        CourseUserCollectionExample example = new CourseUserCollectionExample();
        CourseUserCollectionExample.Criteria c =example.createCriteria();
        c.andCourseIdEqualTo(authCourseUserCollectionDelBO.getCourseId()).andUserNoEqualTo(authCourseUserCollectionDelBO.getUserNo());
        return this.courseUserCollectionMapper.deleteByExample(example);
    }

    @Override
    public int countCollectionByUserNo(AuthCourseUserCollectionCountBO authCourseUserCollectionCountBO) {
        CourseUserCollectionExample example = new CourseUserCollectionExample();
        CourseUserCollectionExample.Criteria c =example.createCriteria();
        c.andUserNoEqualTo(authCourseUserCollectionCountBO.getUserNo());
        return this.courseUserCollectionMapper.countByExample(example);
    }
}
