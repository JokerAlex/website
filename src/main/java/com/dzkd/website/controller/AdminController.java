package com.dzkd.website.controller;

import com.dzkd.website.pojo.*;
import com.dzkd.website.service.ArticleService;
import com.dzkd.website.service.Impl.*;
import com.dzkd.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @RequestMapping(value = "/stu/{stuId}", method = RequestMethod.GET)
    public R getStudent(@PathVariable(name = "stuId") Integer stuId) {
        return userService.getStudent(stuId);
    }

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

    @RequestMapping(value = "/admin/{adminId}", method = RequestMethod.GET)
    public R getAdmin(@PathVariable(name = "adminId") Integer adminId) {
        return userService.getAdminInfo(adminId);
    }

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

    @RequestMapping(value = "/admission/{articleId}", method = RequestMethod.DELETE)
    public R delAdmissionInfo(@PathVariable(name = "articleId") Integer articleId) {
        return admissionService.delArticle(articleId);
    }

    @RequestMapping(value = "/admission", method = RequestMethod.DELETE)
    public R delBatchAdmissionInfo(@RequestBody List<Article> articleList) {
        return admissionService.delBatch(articleList);
    }

    @RequestMapping(value = "/admission/{articleId}", method = RequestMethod.GET)
    public R searchAdmissionInfo(@PathVariable(name = "articleId") Integer articleId) {
        return admissionService.searchArticle(articleId);
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

    @RequestMapping(value = "/department/{articleId}", method = RequestMethod.DELETE)
    public R delDepartment(@PathVariable(name = "articleId") Integer articleId) {
        return departmentService.delArticle(articleId);
    }

    @RequestMapping(value = "/department/", method = RequestMethod.DELETE)
    public R delBatchDepartment(@RequestBody List<Article> articleList) {
        return departmentService.delBatch(articleList);
    }

    @RequestMapping(value = "/department/{articleId}", method = RequestMethod.GET)
    public R searchDepartment(@PathVariable(name = "articleId") Integer articleId) {
        return departmentService.searchArticle(articleId);
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

    @RequestMapping(value = "/employ/{articleId}", method = RequestMethod.DELETE)
    public R delEmployInfo(@PathVariable(name = "articleId") Integer articleId) {
        return employInfoService.delArticle(articleId);
    }

    @RequestMapping(value = "/employ", method = RequestMethod.DELETE)
    public R delBatchEmployInfo(@RequestBody List<Article> articleList) {
        return employInfoService.delBatch(articleList);
    }

    @RequestMapping(value = "/employ/{articleId}", method = RequestMethod.GET)
    public R searchEmployInfo(@PathVariable(name = "articleId") Integer articleId) {
        return employInfoService.searchArticle(articleId);
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

    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.DELETE)
    public R delNews(@PathVariable(name = "newsId") Integer newsId) {
        return newsService.delArticle(newsId);
    }

    @RequestMapping(value = "/news", method = RequestMethod.DELETE)
    public R delBatchNews(@RequestBody List<News> newsList) {
        return newsService.delBatch(newsList);
    }

    @RequestMapping(value = "/news/{newsId}", method = RequestMethod.GET)
    public R searchNews(@PathVariable(name = "newsId") Integer newsId) {
        return newsService.searchArticle(newsId);
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

    @RequestMapping(value = "/news/type/{newsTypeId}", method = RequestMethod.DELETE)
    public R delNewsType(@PathVariable(name = "newsTypeId") Integer newsTypeId) {
        return newsTypeService.delArticle(newsTypeId);
    }

    @RequestMapping(value = "/news/type", method = RequestMethod.DELETE)
    public R delBatchNewsType(@RequestBody List<NewsType> newsTypeList) {
        return newsTypeService.delBatch(newsTypeList);
    }

    @RequestMapping(value = "/news/type/{newsTypeId}", method = RequestMethod.GET)
    public R searchNewsType(@PathVariable(name = "newsTypeId") Integer newsTypeId) {
        return newsTypeService.searchArticle(newsTypeId);
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

    @RequestMapping(value = "/notice/{noticeId}", method = RequestMethod.DELETE)
    public R delNotice(@PathVariable(name = "noticeId") Integer articleId) {
        return noticeService.delArticle(articleId);
    }

    @RequestMapping(value = "/notice", method = RequestMethod.DELETE)
    public R delBatchNotice(@RequestBody List<Article> articleList) {
        return noticeService.delBatch(articleList);
    }

    @RequestMapping(value = "/notice/{noticeId}", method = RequestMethod.GET)
    public R searchNotice(@PathVariable(name = "noticeId") Integer articleId) {
        return noticeService.searchArticle(articleId);
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

    @RequestMapping(value = "/professional/{professionId}", method = RequestMethod.DELETE)
    public R delProfession(@PathVariable(name = "professionId") Integer professionalIntroductionId) {
        return professionalService.delArticle(professionalIntroductionId);
    }

    @RequestMapping(value = "/professional/{professionId}", method = RequestMethod.DELETE)
    public R delBatchProfession(@RequestBody List<ProfessionalIntroduction> professionalIntroductionList) {
        return professionalService.delBatch(professionalIntroductionList);
    }

    @RequestMapping(value = "/professional/{professionId}", method = RequestMethod.GET)
    public R searchProfession(@PathVariable(name = "professionId") Integer professionalIntroductionId) {
        return professionalService.searchArticle(professionalIntroductionId);
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
        return schoolService.searchArticle(1);
    }


}
