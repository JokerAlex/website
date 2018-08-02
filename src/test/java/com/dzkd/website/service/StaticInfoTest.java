package com.dzkd.website.service;

import com.dzkd.website.pojo.Article;
import com.dzkd.website.service.Impl.DepartmentIntroductionServiceImpl;
import com.dzkd.website.service.Impl.SchoolIntroductionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaticInfoTest {

    @Autowired
    private SchoolIntroductionServiceImpl schoolService;
    @Autowired
    private DepartmentIntroductionServiceImpl departmentService;

    @Test
    public void updateSchoolIntroduction() {
        Article school = new Article(1,1,0,new Date().toString(),"dzkd","dzkd非常好haohaoahhaoa好好好好好好好好");

        System.out.println(schoolService.updateArticle(school).toString());
    }

    @Test
    public void showSchoolIntroduction() {
        Article article = new Article();
        article.setArticleId(1);
        System.out.println(schoolService.searchArticle(article).toString());
    }

    @Test
    public void addDepartment() {
        Article article = new Article();
        article.setAdminId(1);
        article.setPageViews(0);
        article.setUpdateTime(new Date().toString());
        article.setArticleTitle("信息与软件工程学院");
        article.setArticleContent("信息与软件工程学院，非常好，超级好，好极了");

        System.out.println(departmentService.addArticle(article).toString());
    }

    @Test
    public void updateDepartment() {
        Article article = new Article(3,1,0,null,"信息与软件工程学院",
                "信息与软件工程学院，非常好，超级好，好极了，棒极了，好极了，6到飞起");

        System.out.println(departmentService.updateArticle(article).toString());
    }

    @Test
    public void searchDepartment() {
        Article article = new Article();
        article.setArticleId(3);
        System.out.println(departmentService.searchArticle(article).toString());
    }

    @Test
    public void delDepartment() {
        Article article = new Article();
        article.setArticleId(3);
        System.out.println(departmentService.delArticle(article).toString());
    }

    @Test
    public void showAllDepartment(){
        System.out.println(departmentService.showAll(1,5));
    }
}
