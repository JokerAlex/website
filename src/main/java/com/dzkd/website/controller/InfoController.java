package com.dzkd.website.controller;

import com.dzkd.website.pojo.*;
import com.dzkd.website.service.ArticleService;
import com.dzkd.website.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfoController {

    private ArticleService<Article> admissionService;
    private ArticleService<Article> departmentService;
    private ArticleService<Article> employInfoService;
    private ArticleService<News> newsService;
    private ArticleService<NewsType> newsTypeService;
    private ArticleService<Article> noticeService;
    private ArticleService<ProfessionalIntroduction> professionalService;
    private ArticleService<Article> schoolService;

    @Autowired
    public InfoController( AdmissionInfoServiceImpl admissionService,
                           DepartmentIntroductionServiceImpl departmentService,
                           EmployInfoServiceImpl employInfoService,
                           NewsServiceImpl newsService,
                           NewsTypeServiceImpl newsTypeService,
                           NoticeServiceImpl noticeService,
                           ProfessionalServiceImpl professionalService,
                           SchoolIntroductionServiceImpl schoolService) {
        this.admissionService = admissionService;
        this.departmentService = departmentService;
        this.employInfoService = employInfoService;
        this.newsService = newsService;
        this.newsTypeService = newsTypeService;
        this.noticeService = noticeService;
        this.professionalService = professionalService;
        this.schoolService = schoolService;
    }

    /**
     * =================================================================================================================
     *                                                    招生信息
     * =================================================================================================================
     */

    @RequestMapping(value = "/admission/{articleId}", method = RequestMethod.GET)
    public R searchAdmissionInfo(@PathVariable(name = "articleId") int articleId) {
        return admissionService.searchArticle(articleId);
    }

    @RequestMapping(value = "/admission", method = RequestMethod.GET)
    public R showAllAdmissionInfo(Integer pageNum, Integer pageSize) {
        return admissionService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                    院系简介
     * =================================================================================================================
     */

    @RequestMapping(value = "/department/{articleId}", method = RequestMethod.GET)
    public R searchDepartment(@PathVariable(name = "articleId") int articleId) {
        return departmentService.searchArticle(articleId);
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public R showAllDepartment(Integer pageNum, Integer pageSize) {
        return departmentService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                     就业信息
     * =================================================================================================================
     */

    @RequestMapping(value = "/employ/{articleId}", method = RequestMethod.GET)
    public R searchEmployInfo(@PathVariable(name = "articleId") int articleId) {
        return employInfoService.searchArticle(articleId);
    }

    @RequestMapping(value = "/employ", method = RequestMethod.GET)
    public R showAllEmployInfo(Integer pageNum, Integer pageSize) {
        return employInfoService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                         新闻
     * =================================================================================================================
     */

    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.GET)
    public R searchNews(@PathVariable(name = "newsId") int newsId) {
        return newsService.searchArticle(newsId);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public R showAllNews(Integer pageNum, Integer pageSize) {
        return newsService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                       新闻分类
     * =================================================================================================================
     */

    @RequestMapping(value = "/news/type/{newsTypeId}", method = RequestMethod.GET)
    public R searchNewsType(@PathVariable(name = "newsTypeId") int newsTypeId) {
        return newsTypeService.searchArticle(newsTypeId);
    }

    @RequestMapping(value = "/news/type", method = RequestMethod.GET)
    public R showAllNewsType(Integer pageNum, Integer pageSize) {
        return newsTypeService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                       公告
     * =================================================================================================================
     */

    @RequestMapping(value = "/notice/{noticeId}", method = RequestMethod.GET)
    public R searchNotice(@PathVariable(name = "noticeId") int articleId) {
        return noticeService.searchArticle(articleId);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public R showAllNotice(Integer pageNum, Integer pageSize) {
        return noticeService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                    专业介绍
     * =================================================================================================================
     */

    @RequestMapping(value = "/professional/{professionId}", method = RequestMethod.GET)
    public R searchProfession(@PathVariable(name = "professionId") int professionalIntroductionId) {
        return professionalService.searchArticle(professionalIntroductionId);
    }

    @RequestMapping(value = "/professional", method = RequestMethod.GET)
    public R showAllProfession(Integer pageNum, Integer pageSize) {
        return professionalService.showAll(pageNum, pageSize, null);
    }

    /**
     * =================================================================================================================
     *                                                   学校简介
     * =================================================================================================================
     */

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public R searchSchool() {
        return schoolService.searchArticle(1);
    }

}
