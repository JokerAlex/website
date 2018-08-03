package com.dzkd.website.controller;

import com.dzkd.website.pojo.*;
import com.dzkd.website.service.ArticleService;
import com.dzkd.website.service.Impl.*;
import com.dzkd.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private ArticleService<Article> admissionService;
    private ArticleService<Article> departmentService;
    private ArticleService<Article> employInfoService;
    private ArticleService<News> newsService;
    private ArticleService<NewsType> newsTypeService;
    private ArticleService<Article> noticeService;
    private ArticleService<ProfessionalIntroduction> professionalService;
    private ArticleService<Article> schoolService;

    @Autowired
    public AdminController(UserService userService,
                           AdmissionInfoServiceImpl admissionService,
                           DepartmentIntroductionServiceImpl departmentService,
                           EmployInfoServiceImpl employInfoService,
                           NewsServiceImpl newsService,
                           NewsTypeServiceImpl newsTypeService,
                           NoticeServiceImpl noticeService,
                           ProfessionalServiceImpl professionalService,
                           SchoolIntroductionServiceImpl schoolService) {
        this.userService = userService;
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
     *                                                   学生信息管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/stu", method = RequestMethod.GET)
    public R showStudents(int pageNum, int pageSize) {
        return userService.getAllStudent(pageNum, pageSize);
    }

    @RequestMapping(value = "/stu", method = RequestMethod.POST)
    public R addStudent(@RequestBody Student student) {
        return userService.addStudent(student);
    }

    @RequestMapping(value = "/stu", method = RequestMethod.PUT)
    public R updateStudent(@RequestBody Student student) {
        return userService.updateStudent(student);
    }

    /**
     * =================================================================================================================
     *                                                   管理员信息管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public R showAdmins(int pageNum, int pageSize) {
        return userService.getAllAdminInfo(pageNum, pageSize);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public R addAdmin(@RequestBody AdminInfo adminInfo) {
        return userService.addAdminInfo(adminInfo);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.PUT)
    public R updateAdmin(@RequestBody AdminInfo adminInfo) {
        return userService.updateAdminInfo(adminInfo);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.DELETE)
    public R delAdmin(@RequestBody AdminInfo adminInfo) {
        return userService.delAdminInfo(adminInfo);
    }

    /**
     * =================================================================================================================
     *                                                    招生信息管理
     * =================================================================================================================
     */
    @RequestMapping(value = "/admission", method = RequestMethod.POST)
    public R addAdmissionInfo(@RequestBody Article article) {
        return admissionService.addArticle(article);
    }

    @RequestMapping(value = "/admission", method = RequestMethod.PUT)
    public R updateAdmissionInfo(@RequestBody Article article) {
        return admissionService.updateArticle(article);
    }

    @RequestMapping(value = "/admission", method = RequestMethod.DELETE)
    public R delAdmissionInfo(@RequestBody Article article) {
        return admissionService.delArticle(article);
    }

    @RequestMapping(value = "/admission/{articleId}", method = RequestMethod.GET)
    public R searchAdmissionInfo(@PathVariable(name = "articleId") int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        return admissionService.searchArticle(article);
    }

    @RequestMapping(value = "/admission", method = RequestMethod.GET)
    public R showAllAdmissionInfo(Integer pageNum, Integer pageSize) {
        return admissionService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                     院系简介管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public R addDepartment(@RequestBody Article article) {
        return departmentService.addArticle(article);
    }

    @RequestMapping(value = "/department", method = RequestMethod.PUT)
    public R updateDepartment(@RequestBody Article article) {
        return departmentService.updateArticle(article);
    }

    @RequestMapping(value = "/department", method = RequestMethod.DELETE)
    public R delDepartment(@RequestBody Article article) {
        return departmentService.delArticle(article);
    }

    @RequestMapping(value = "/department/{articleId}", method = RequestMethod.GET)
    public R searchDepartment(@PathVariable(name = "articleId") int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        return departmentService.searchArticle(article);
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public R showAllDepartment(Integer pageNum, Integer pageSize) {
        return departmentService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                     就业信息管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/employ", method = RequestMethod.POST)
    public R addEmployInfo(@RequestBody Article article) {
        return employInfoService.addArticle(article);
    }

    @RequestMapping(value = "/employ", method = RequestMethod.PUT)
    public R updateEmployInfo(@RequestBody Article article) {
        return employInfoService.updateArticle(article);
    }

    @RequestMapping(value = "/employ", method = RequestMethod.DELETE)
    public R delEmployInfo(@RequestBody Article article) {
        return employInfoService.delArticle(article);
    }

    @RequestMapping(value = "/employ/{articleId}", method = RequestMethod.GET)
    public R searchEmployInfo(@PathVariable(name = "articleId") int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        return employInfoService.searchArticle(article);
    }

    @RequestMapping(value = "/employ", method = RequestMethod.GET)
    public R showAllEmployInfo(Integer pageNum, Integer pageSize) {
        return employInfoService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                         新闻管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public R addNews(@RequestBody News news) {
        return newsService.addArticle(news);
    }

    @RequestMapping(value = "/news", method = RequestMethod.PUT)
    public R updateNews(@RequestBody News news) {
        return newsService.updateArticle(news);
    }

    @RequestMapping(value = "/news", method = RequestMethod.DELETE)
    public R delNews(@RequestBody News news) {
        return newsService.delArticle(news);
    }

    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.GET)
    public R searchNews(@PathVariable(name = "newsId") int newsId) {
        News news = new News();
        news.setNewsId(newsId);
        return newsService.searchArticle(news);
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public R showAllNews(Integer pageNum, Integer pageSize) {
        return newsService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                       新闻分类管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/news/type", method = RequestMethod.POST)
    public R addNewsType(@RequestBody NewsType newsType) {
        return newsTypeService.addArticle(newsType);
    }

    @RequestMapping(value = "/news/type", method = RequestMethod.PUT)
    public R updateNewsType(@RequestBody NewsType newsType) {
        return newsTypeService.updateArticle(newsType);
    }

    @RequestMapping(value = "/news/type", method = RequestMethod.DELETE)
    public R delNewsType(@RequestBody NewsType newsType) {
        return newsTypeService.delArticle(newsType);
    }

    @RequestMapping(value = "/news/type/{newsTypeId}", method = RequestMethod.GET)
    public R searchNewsType(@PathVariable(name = "newsTypeId") int newsTypeId) {
        NewsType newsType = new NewsType();
        newsType.setTypeId(newsTypeId);
        return newsTypeService.searchArticle(newsType);
    }

    @RequestMapping(value = "/news/type", method = RequestMethod.GET)
    public R showAllNewsType(Integer pageNum, Integer pageSize) {
        return newsTypeService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                         公告管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public R addNotice(@RequestBody Article article) {
        return noticeService.addArticle(article);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.PUT)
    public R updateNotice(@RequestBody Article article) {
        return noticeService.updateArticle(article);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.DELETE)
    public R delNotice(@RequestBody Article article) {
        return noticeService.delArticle(article);
    }

    @RequestMapping(value = "/notice/{noticeId}", method = RequestMethod.GET)
    public R searchNotice(@PathVariable(name = "noticeId") int articleId) {
        Article article = new Article();
        article.setArticleId(articleId);
        return noticeService.searchArticle(article);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public R showAllNotice(Integer pageNum, Integer pageSize) {
        return noticeService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                  专业介绍管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/professional", method = RequestMethod.POST)
    public R addProfession(@RequestBody ProfessionalIntroduction professionalIntroduction) {
        return professionalService.addArticle(professionalIntroduction);
    }

    @RequestMapping(value = "/professional", method = RequestMethod.PUT)
    public R updateProfession(@RequestBody ProfessionalIntroduction professionalIntroduction) {
        return professionalService.updateArticle(professionalIntroduction);
    }

    @RequestMapping(value = "/professional", method = RequestMethod.DELETE)
    public R delProfession(@RequestBody ProfessionalIntroduction professionalIntroduction) {
        return professionalService.delArticle(professionalIntroduction);
    }

    @RequestMapping(value = "/professional/{professionId}", method = RequestMethod.GET)
    public R searchProfession(@PathVariable(name = "professionId") int professionalIntroductionId) {
        ProfessionalIntroduction professionalIntroduction = new ProfessionalIntroduction();
        professionalIntroduction.setProfessionalId(professionalIntroductionId);
        return professionalService.searchArticle(professionalIntroduction);
    }

    @RequestMapping(value = "/professional", method = RequestMethod.GET)
    public R showAllProfession(Integer pageNum, Integer pageSize) {
        return professionalService.showAll(pageNum, pageSize);
    }

    /**
     * =================================================================================================================
     *                                                  学校简介管理
     * =================================================================================================================
     */

    @RequestMapping(value = "/school", method = RequestMethod.PUT)
    public R updateSchool(@RequestBody Article article) {
        return schoolService.updateArticle(article);
    }

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public R searchSchool() {
        Article article = new Article();
        article.setArticleId(1);
        return schoolService.searchArticle(article);
    }


}
