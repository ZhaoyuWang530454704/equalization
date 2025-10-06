package com.ktz.equalization.system.task;

import com.ktz.equalization.domain.Policy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 曾泉明
 * @date 2021/1/16 下午8:44
 */
@Component
public class PolicyTask {

    private String sourceUrl;

    private void initUrl() {
        sourceUrl = "http://sousuo.gov.cn/s.htm?q=";
    }

    public List<Policy> parse2policy(String key) {
        List<Policy> policies = new ArrayList<>();
        initUrl();

        try {
            for (int i = 0; i < 10; i++) {
                Document document = Jsoup.connect(sourceUrl + key + "&n=10&p=" + i).get();
                Element element = document.getElementsByClass("result").get(0);
                Elements elements = element.getElementsByTag("li");
                for (Element elem : elements) {
                    String policyTitle = elem.getElementsByTag("h3").eq(0).text();
                    if ("相关图片".equals(policyTitle) || "相关公文".equals(policyTitle)) {
                        continue;
                    }
                    Elements policyUrlElems = elem.getElementsByTag("a");
                    String policyUrl = "";
                    if (policyUrlElems.size() > 1) {
                        policyUrl = policyUrlElems.eq(1).attr("href");
                    }else {
                        policyUrl = elem.getElementsByTag("a").eq(0).attr("href");
                    }
                    String publishTime = elem.getElementsByClass("res-other").eq(0).text().substring(5);
                    String author = "";
                    Document detailDoc = Jsoup.connect(policyUrl).get();
                    if (policyUrl.substring(18).startsWith("xinwen")
                            || policyUrl.substring(18).startsWith("zhengce")
                            || policyUrl.substring(18).startsWith("2014lh")
                            || policyUrl.substring(18).startsWith("zhuanti")
                            || policyUrl.substring(18).startsWith("fuwu")) {
                        Elements dateElem = detailDoc.getElementsByClass("pages-date");
                        if (dateElem.size() > 0) {
                            Elements spanElem = dateElem.get(0).getElementsByTag("span");
                            if (spanElem.size() > 2) {
                                author = spanElem.eq(0).text().substring(4);
                            } else {
                                author = spanElem.eq(1).text().substring(4);
                            }
                        }else {
                            Element tableElem = detailDoc.getElementsByClass("wrap").get(0).getElementsByTag("table").get(0);
                            Element table = tableElem.getElementsByClass("bd1").get(0);
                            author = table.getElementsByTag("tr").get(1).getElementsByTag("td").get(1).text();
                        }
                    }else if (policyUrl.substring(18).startsWith("jrzg")
                            || policyUrl.substring(18).startsWith("gzdt")
                            || policyUrl.substring(18).startsWith("2012lh")
                            || policyUrl.substring(18).startsWith("ztzl")
                            || policyUrl.substring(18).startsWith("zwgk")) {
                        String authorStr = detailDoc.getElementsByAttributeValue("bgcolor", "#E7E7E7").eq(0).text();
                        author = authorStr.substring(authorStr.indexOf("来源") + 3);
                    }
                    if ("".equals(author)) {
                        continue;
                    }
                    Policy policy = new Policy();
                    policy.setTitle(policyTitle);
                    policy.setAuthor(author);
                    policy.setUrl(policyUrl);
                    policy.setPublishTime(publishTime);
                    policy.setCreateTime(LocalDateTime.now());
                    policy.setUpdateTime(LocalDateTime.now());
                    policies.add(policy);
                }
                initUrl();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return policies;
    }

}
