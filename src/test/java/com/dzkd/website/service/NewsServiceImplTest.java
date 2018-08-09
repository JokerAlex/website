package com.dzkd.website.service;

import com.dzkd.website.dao.NewsMapper;
import com.dzkd.website.pojo.News;
import com.dzkd.website.pojo.R;
import com.dzkd.website.service.Impl.NewsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    private NewsMapper newsMapper;

    @Test
    public void showNews() {
        R r = newsService.showAll(1,5,1);

        System.out.println(r.toString());
    }

    @Test
    public void sortedList() {
        List<News> newsList = newsMapper.selectAll(null);
        newsList.sort(Comparator.comparing(News::getNewsTime).reversed());
        newsList.forEach(item ->
                System.out.println(item.getNewsTypeName()+ item.getNewsTitle() + item.getNewsTime()));
    }
}
