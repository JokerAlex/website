package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.ProfessionalIntroductionMapper;
import com.dzkd.website.pojo.ProfessionalIntroduction;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

@Service
public class ProfessionalServiceImpl implements ArticleService<ProfessionalIntroduction> {

    private ProfessionalIntroductionMapper professionalIntroductionMapper;

    private static final Logger logger = LogManager.getLogger(ProfessionalServiceImpl.class);

    @Autowired
    public ProfessionalServiceImpl(ProfessionalIntroductionMapper professionalIntroductionMapper) {
        this.professionalIntroductionMapper = professionalIntroductionMapper;
    }

    /**
     * 添加专业介绍
     *
     * @param professionalIntroduction
     * @return
     */
    @Override
    public R addArticle(ProfessionalIntroduction professionalIntroduction) {
        if (professionalIntroduction == null) {
            return R.isFail(new Exception("添加专业介绍失败"));
        }

        try {
            //重置信息
            professionalIntroduction.setProfessionalId(null);
            professionalIntroduction.setProfessionalUpdateTime(new Date().toString());
            professionalIntroduction.setProfessionalAcessNumber(0);

            int insert = professionalIntroductionMapper.insertSelective(professionalIntroduction);
            logger.info("ProfessionalIntroductionServiceImpl->insert" + insert);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("添加专业介绍失败"));
        }
    }

    /**
     * 更新专业介绍
     *
     * @param professionalIntroduction
     * @return
     */
    @Override
    public R updateArticle(ProfessionalIntroduction professionalIntroduction) {
        if (professionalIntroduction == null) {
            return R.isFail(new Exception("更新专业简介失败"));
        }

        try {
            //重置信息
            professionalIntroduction.setProfessionalUpdateTime(new Date().toString());
            professionalIntroduction.setProfessionalAcessNumber(null);//访问量不在此处更新

            int update = professionalIntroductionMapper.updateByPrimaryKeySelective(professionalIntroduction);
            logger.info("ProfessionalIntroductionServiceImpl->update" + update);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("更新专业介绍失败"));
        }
    }

    /**
     * 删除专业介绍
     *
     * @param professionalIntroductionId
     * @return
     */
    @Override
    public R delArticle(Integer professionalIntroductionId) {
        if (professionalIntroductionId == null) {
            return R.isFail(new Exception("删除专业简介失败"));
        }

        try {
            int del = professionalIntroductionMapper.deleteByPrimaryKey(professionalIntroductionId);
            logger.info("ProfessionalIntroductionServiceImpl->del" + del);

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除专业介绍失败"));
        }
    }

    /**
     * 批量删除
     *
     * @param professionalIntroductions
     * @return
     */
    @Override
    public R delBatch(List<ProfessionalIntroduction> professionalIntroductions) {
        if (professionalIntroductions.size() == 0) {
            return R.isFail(new Exception("删除专业介绍失败"));
        }

        try {
            int delBatch = professionalIntroductionMapper.deleteBatch(professionalIntroductions);
            logger.info("ProfessionalIntroductionServiceImpl->delBatch:" + (delBatch == professionalIntroductions.size()));

            return R.isOk();
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("删除专业介绍失败"));
        }
    }

    /**
     * 查看专业介绍
     *
     * @param professionalIntroductionId
     * @return
     */
    @Override
    public R searchArticle(Integer professionalIntroductionId) {
        if (professionalIntroductionId == null) {
            return R.isFail(new Exception("获取专业简介失败"));
        }

        try {
            ProfessionalIntroduction professional = professionalIntroductionMapper.selectByPrimaryKey(professionalIntroductionId);
            if (professional == null) {
                return R.isFail(new Exception("获取专业简介失败"));
            } else {
                //更新访问量
                professional.setProfessionalAcessNumber(professional.getProfessionalAcessNumber() + 1);
                int updatePageViews = professionalIntroductionMapper.updateByPrimaryKeySelective(professional);
                logger.info("ProfessionalIntroductionServiceImpl->addProfessional->updatePageViews" + updatePageViews);

                return R.isOk().data(professional);
            }
        } catch (Exception e) {
            logger.catching(e);
            return R.isFail(new Exception("获取专业介绍失败"));
        }
    }

    /**
     * 返回列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public R showAll(Integer pageNum, Integer pageSize, Object departmentId) {
        if (pageNum == null || pageSize == null) {
            return R.isFail(new Exception("参数错误"));
        }

        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<ProfessionalIntroduction> professionalIntroductionList = professionalIntroductionMapper.selectAll((Integer) departmentId);
        PageInfo<ProfessionalIntroduction> pageInfo = new PageInfo<>(professionalIntroductionList);

        JSONObject data = new JSONObject();
        data.put("data", professionalIntroductionList);
        data.put("pageInfo", pageInfo);

        return R.isOk().data(data);
    }
}
