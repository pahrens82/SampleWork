/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.SearchTerm;
import com.sg.glbccapstone.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ArticleDaoDbImpl implements ArticleDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private enum SQL {

        //SELECT_ALL("select * from Article"),
        SELECT_ALL("select * from Article a left join Category c on a.Category_ID = c.Category_ID"),
        //SELECT_BY_ID("select * from Article where Article_ID = ?"),
        SELECT_BY_ID("select * from Article a join Category c on a.Category_ID = c.Category_ID where Article_ID = ?"),
        //SELECT_MOST_RECENT_ARTICLE("select * from Article where Approval = ? order by Article_ID DESC limit ?"),
        SELECT_MOST_RECENT_ARTICLE("select * from Article a join Category c on a.Category_ID = c.Category_ID where Approval = ? order by Article_ID DESC limit ?"),
        INSERT("insert into Article (username, Approval, ArticleName, Summary, TextBody, CreateDate, Category_ID) values (?, ?, ?, ?, ?, ?, ?)"),
        DELETE_ARTICLE_FROM_ARTICLETAG("delete from ArticleTag where Article_ID = ?"),
        DELETE("delete from Article where Article_ID = ?"),
        RELATE_ARTICLE_TO_TAG("insert into ArticleTag values (?, ?);"),
        UPDATE("update Article set username = ?, Approval = ?, ArticleName = ?, Summary = ?, TextBody = ?, CreateDate = ?, EditDate = ?, PublishDate = ?, ApproveDate = ?, Category_ID = ? where Article_ID = ?"),
        //SELECT_BY_USER_ID("select * from Article where User_ID = ?"),
        SELECT_BY_USERNAME("select * from Article a join Category c on a.Category_ID = c.Category_ID where Username = ?"),
        //SELECT_BY_CATEGORY_ID("select a.Article_ID, a.username, a.Approval, a.ArticleName, a.Summary, a.TextBody, a.CreateDate, a.EditDate, a.ApproveDate, a.PublishDate, a.Category_ID from Article a join Category c on a.Category_ID = c.Category_ID where a.Category_ID = ?"),
        SELECT_BY_CATEGORY_ID("select * from Article a join Category c on a.Category_ID = c.Category_ID where a.Category_ID = ?"),
        //SELECT_BY_TAG_NAME("select a.Article_ID, a.username, a.Approval, a.ArticleName, a.Summary, a.TextBody, a.CreateDate, a.EditDate, a.ApproveDate, a.PublishDate, a.Category_ID\n"
        //        + "from Article a\n"
        //        + "join ArticleTag artTag on a.Article_ID = artTag.Article_id\n"
        //        + "join Tag t on t.Tag_ID = artTag.Tag_ID\n"
        //        + "where t.TagName = ?"),
        SELECT_BY_TAG_NAME("select a.Article_ID, a.username, a.Approval, a.ArticleName, a.Summary, a.TextBody, a.CreateDate, a.EditDate, a.ApproveDate, a.PublishDate, a.Category_ID, c.CategoryName from Article a join Category c on a.Category_ID = c.Category_ID join ArticleTag artTag on a.Article_ID = artTag.Article_id join Tag t on t.Tag_ID = artTag.Tag_ID where t.TagName = ?"),
        //SELECT_BY_APPROVAL("select * from Article where Approval = ?");
        SELECT_BY_APPROVAL("select * from Article a join Category c on a.Category_ID = c.Category_ID where Approval = ?");

        private final String statement;

        private SQL(String statement) {
            this.statement = statement;
        }

        public String getStatement() {
            return this.statement;
        }
    }

    private Article createTagLink(Article article) {
        Pattern p = Pattern.compile("(?<!\">)#[A-Za-z0-9]*\\b");
        Matcher m = p.matcher(article.getTextBody());
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String foundItem = m.group().substring(1);

            foundItem = "<a class=\"tag\" data-tagName=" + foundItem + " href=\"#\">#" + foundItem + "</a>";

            m.appendReplacement(sb, foundItem);
        }
        m.appendTail(sb);
        article.setTextBody(sb.toString());

        return article;
    }

    //Create
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Article createArticle(Article article) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			String today = dateFormat.format(article.getCreateDate());
            
			article = createTagLink(article);
			
			jdbcTemplate.update(SQL.INSERT.getStatement(),
                //article.getUserId(),
                article.getUserName(),
                article.getApproval(),
                article.getArticleName(),
                article.getSummary(),
                article.getTextBody(),
                today,
                article.getCategoryId()
				);
		} catch (Exception pe) {
			System.out.println("Things are broken!");
		}
        

        article.setArticleId(jdbcTemplate.queryForObject("select last_insert_id()", Integer.class));

        return article;
    }

    //Retrieve
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Article> getAllArticles() {
        return jdbcTemplate.query(SQL.SELECT_ALL.statement, new ArticleMapper());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Article getArticleById(int articleId) {
        try {
            return jdbcTemplate.queryForObject(SQL.SELECT_BY_ID.getStatement(), new ArticleMapper(), articleId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Article> getMostRecentApprovedArticle(boolean approved, int limit) {
        return jdbcTemplate.query(SQL.SELECT_MOST_RECENT_ARTICLE.getStatement(), new ArticleMapper(), approved, limit);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Article> getAllArticlesByUserName(String username) {
        return jdbcTemplate.query(SQL.SELECT_BY_USERNAME.getStatement(), new ArticleMapper(), username);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Article> getAllArticlesByTagName(String tagName) {
        return jdbcTemplate.query(SQL.SELECT_BY_TAG_NAME.getStatement(), new ArticleMapper(), tagName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Article> getAllArticlesByCategoryId(int categoryId) {
        return jdbcTemplate.query(SQL.SELECT_BY_CATEGORY_ID.getStatement(), new ArticleMapper(), categoryId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Article> getAllArticlesByApproval(boolean approved) {
        List<Article> returnArticle = jdbcTemplate.query(SQL.SELECT_BY_APPROVAL.getStatement(), new ArticleMapper(), approved);
        return returnArticle;
    }

    //Alter
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateArticle(Article article) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		
		
		try {
			
			
			String createDate = dateFormat.format(article.getCreateDate());
			String editDate;
			String publishDate;
			String approveDate;
			
			try {
				editDate = dateFormat.format(article.getEditDate());
			} catch(Exception e) {
				editDate = null;
			}
			
			try {
				publishDate = dateFormat.format(article.getPublishDate());
			} catch(Exception e) {
				publishDate = null;
			}
			
			try {
				approveDate = dateFormat.format(article.getApproveDate());
			} catch(Exception e) {
				approveDate = null;
			}

			//Format TextBody Hashtags into links
			article = createTagLink(article);

			//Converting LocalDate to Date for storage in the database.
			jdbcTemplate.update(SQL.UPDATE.getStatement(),
                //article.getUserId(),
                article.getUserName(),
                article.getApproval(),
                article.getArticleName(),
                article.getSummary(),
                article.getTextBody(),
                createDate,
                editDate,
                publishDate,
                approveDate,
                article.getCategoryId(),
                article.getArticleId());
		
		} catch(Exception pe) {
			System.out.println(pe.getMessage());
		}
    }

    //Purge
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeArticle(int articleId) {
        jdbcTemplate.update(SQL.DELETE_ARTICLE_FROM_ARTICLETAG.getStatement(), articleId);
        jdbcTemplate.update(SQL.DELETE.getStatement(), articleId);
    }

    @Override
    public List<Article> searchArticles(Map<SearchTerm, String> criteria) {
        List<Article> allArticles = jdbcTemplate.query(SQL.SELECT_BY_APPROVAL.getStatement(), new ArticleMapper(), true);

        String searchCriteria = criteria.get(SearchTerm.ARTICLE).toLowerCase();

        Predicate<Article> nameMatches;
        Predicate<Article> userMatches;

        Predicate<Article> truePredicate = (article) -> {
            return true;
        };

        nameMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (article) -> article.getArticleName().toLowerCase().contains(searchCriteria);
        userMatches = (searchCriteria == null || searchCriteria.isEmpty()) ? truePredicate : (article) -> article.getUserName().toLowerCase().contains(searchCriteria);

        return allArticles.stream().filter(nameMatches.or(userMatches)).collect(Collectors.toList());
    }

    @Override
    public void addTagsToArticle(List<Tag> tagList, Article article) {

        //Insert into bridge table
        for (Tag tag : tagList) {
            jdbcTemplate.update(SQL.RELATE_ARTICLE_TO_TAG.getStatement(), article.getArticleId(), tag.getTagId());
        }
    }

    private static final class ArticleMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet rs, int i) throws SQLException {
            Article article = new Article();

            article.setArticleId(rs.getInt("Article_ID"));
            //article.setUserId(rs.getInt("User_ID"));
            article.setUserName(rs.getString("username"));
            article.setApproval(rs.getBoolean("Approval"));
            article.setArticleName(rs.getString("ArticleName"));
            article.setSummary(rs.getString("Summary"));
            article.setTextBody(rs.getString("TextBody"));
            article.setCreateDate(rs.getDate("CreateDate"));
            article.setEditDate(rs.getDate("EditDate"));
            article.setPublishDate(rs.getDate("PublishDate"));
            article.setApproveDate(rs.getDate("ApproveDate"));
            article.setCategoryId(rs.getInt("Category_ID"));
            article.setCategoryName(rs.getString("CategoryName"));

            return article;
        }

    }

}
