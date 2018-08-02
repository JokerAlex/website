package com.dzkd.website.controller;

import com.dzkd.website.pojo.*;
import com.dzkd.website.service.ArticleService;
import com.dzkd.website.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/admission", method = RequestMethod.GET)
    public R searchAdmissionInfo(@RequestBody Article article) {
        return admissionService.searchArticle(article);
    }

    @RequestMapping(value = "/admission", method = RequestMethod.GET)
    public R showAllAdmissionInfo(int pageNum, int pageSize) {
        return admissionService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                    院系简介
     * =================================================================================================================
     */

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public R searchDepartment(@RequestBody Article article) {
        return departmentService.searchArticle(article);
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public R showAllDepartment(int pageNum, int pageSize) {
        return departmentService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                     就业信息
     * =================================================================================================================
     */

    @RequestMapping(value = "/employ", method = RequestMethod.GET)
    public R searchEmployInfo(@RequestBody Article article) {
        return employInfoService.searchArticle(article);
    }

    @RequestMapping(value = "/employ", method = RequestMethod.GET)
    public R showAllEmployInfo(int pageNum, int pageSize) {
        return employInfoService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                         新闻
     * =================================================================================================================
     */

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public R searchNews(@RequestBody News news) {
        return newsService.searchArticle(news);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public R showAllNews(int pageNum, int pageSize) {
        return newsService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                       新闻分类
     * =================================================================================================================
     */

    @RequestMapping(value = "/news/type", method = RequestMethod.GET)
    public R searchNewsType(@RequestBody NewsType newsType) {
        return newsTypeService.searchArticle(newsType);
    }

    @RequestMapping(value = "/news/type", method = RequestMethod.GET)
    public R showAllNewsType(int pageNum, int pageSize) {
        return newsTypeService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                       公告
     * =================================================================================================================
     */

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public R searchNotice(@RequestBody Article article) {
        return noticeService.searchArticle(article);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public R showAllNotice(int pageNum, int pageSize) {
        return noticeService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                    专业介绍
     * =================================================================================================================
     */

    @RequestMapping(value = "/professional", method = RequestMethod.GET)
    public R searchProfession(@RequestBody ProfessionalIntroduction professionalIntroduction) {
        return professionalService.searchArticle(professionalIntroduction);
    }

    @RequestMapping(value = "/professional", method = RequestMethod.GET)
    public R showAllProfession(int pageNum, int pageSize) {
        return professionalService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                   学校简介
     * =================================================================================================================
     */

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public R searchSchool(@RequestBody Article article) {
        return schoolService.searchArticle(article);
    }

}
