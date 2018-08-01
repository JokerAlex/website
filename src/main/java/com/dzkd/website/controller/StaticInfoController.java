package com.dzkd.website.controller;

import com.dzkd.website.pojo.Article;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.ArticleService;
import com.dzkd.website.service.Impl.DepartmentIntroductionServiceImpl;
import com.dzkd.website.service.Impl.SchoolIntroductionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticInfoController {

    private ArticleService school;
    private ArticleService department;
    @Autowired
    public StaticInfoController(SchoolIntroductionServiceImpl schoolIntroductionService,
                                DepartmentIntroductionServiceImpl departmentIntroductionService) {
        this.school = schoolIntroductionService;
        this.department = departmentIntroductionService;
    }

    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public R showSchoolIntroduction() {
        return school.searchArticle(null);
    }

    @RequestMapping(value = "/articleAdmin/school", method = RequestMethod.PUT)
    public R updateSchoolIntroduction(@RequestBody Article article) {
        return school.updateArticle(article);
    }


    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public R showDepartmentIntroduction(@RequestBody Article article) {
        return department.searchArticle(article);
    }

    @RequestMapping(value = "/articleAdmin/department", method = RequestMethod.POST)
    public R addDepartmentIntroduction(@RequestBody Article article) {
        return department.addArticle(article);
    }

    @RequestMapping(value = "/articleAdmin/department", method = RequestMethod.PUT)
    public R updateDepartmentIntroduction(@RequestBody Article article) {
        return department.updateArticle(article);
    }


    @RequestMapping(value = "/articleAdmin/department", method = RequestMethod.DELETE)
    public R delDepartmentIntroduction(@RequestBody Article article) {
        return department.delArticle(article);
    }
}
