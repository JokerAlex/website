package com.dzkd.website.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.dzkd.website.dao.ProfessionalIntroductionMapper;
import com.dzkd.website.pojo.ProfessionalIntroduction;
import com.dzkd.website.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

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
     * @param professionalIntroduction
     * @return
     */
    @Override
    public JSONObject addArticle(ProfessionalIntroduction professionalIntroduction) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (professionalIntroduction == null) {
            resultCode = 0;
            msg = "添加专业介绍失败";
        } else {
            //重置信息
            professionalIntroduction.setProfessionalId(null);
            professionalIntroduction.setProfessionalUpdateTime(new Date().toString());
            professionalIntroduction.setProfessionalAcessNumber(0);

            int insert = professionalIntroductionMapper.insertSelective(professionalIntroduction);
            logger.info("ProfessionalIntroductionServiceImpl->addProfessional->insert" + insert);

            if (insert == 1) {
                resultCode = 1;
                msg = "添加专业介绍成功";
            } else {
                resultCode = 0;
                msg = "添加专业介绍失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 更新专业介绍
     * @param professionalIntroduction
     * @return
     */
    @Override
    public JSONObject updateArticle(ProfessionalIntroduction professionalIntroduction) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (professionalIntroduction == null) {
            resultCode = 0;
            msg = "更新专业简介失败";
        } else {
            //重置信息
            professionalIntroduction.setProfessionalUpdateTime(new Date().toString());
            professionalIntroduction.setProfessionalAcessNumber(null);//访问量不在此处更新

            int update = professionalIntroductionMapper.updateByPrimaryKeySelective(professionalIntroduction);
            logger.info("ProfessionalIntroductionServiceImpl->addProfessional->update" + update);

            if (update == 1) {
                resultCode = 1;
                msg = "更新专业介绍成功";
            } else {
                resultCode = 0;
                msg = "更新专业介绍失败";
            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 删除专业介绍
     * @param professionalIntroduction
     * @return
     */
    @Override
    public JSONObject delArticle(ProfessionalIntroduction professionalIntroduction) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;

        if (professionalIntroduction == null || professionalIntroduction.getProfessionalId() == null) {
            resultCode = 0;
            msg = "删除专业简介失败";
        } else {
            int del = professionalIntroductionMapper.deleteByPrimaryKey(professionalIntroduction.getProfessionalId());
            logger.info("ProfessionalIntroductionServiceImpl->addProfessional->del" + del);

            if (del == 1) {
                resultCode = 1;
                msg = "删除专业介绍成功";
            } else {
                resultCode = 0;
                msg = "删除专业介绍失败";
            }

        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);

        return result;
    }

    /**
     * 查看专业介绍
     * @param professionalIntroduction
     * @return
     */
    @Override
    public JSONObject searchArticle(ProfessionalIntroduction professionalIntroduction) {
        JSONObject result = new JSONObject();
        int resultCode;
        String msg;
        JSONObject data = new JSONObject();

        if (professionalIntroduction == null || professionalIntroduction.getProfessionalId() == null) {
            resultCode = 0;
            msg = "获取专业简介失败";
        } else {
            ProfessionalIntroduction professional = professionalIntroductionMapper.selectByPrimaryKey(professionalIntroduction.getProfessionalId());
            if (professional == null) {
                resultCode = 0;
                msg = "获取专业简介失败";
            } else {
                //更新访问量
                professional.setProfessionalAcessNumber(professional.getProfessionalAcessNumber() + 1);
                int updatePageViews = professionalIntroductionMapper.updateByPrimaryKeySelective(professional);
                logger.info("ProfessionalIntroductionServiceImpl->addProfessional->updatePageViews" + updatePageViews);

                resultCode = 1;
                msg = "获取专业简介成功";
                data.put("article", professional);

            }
        }

        result.put("resultCode", resultCode);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }
}
