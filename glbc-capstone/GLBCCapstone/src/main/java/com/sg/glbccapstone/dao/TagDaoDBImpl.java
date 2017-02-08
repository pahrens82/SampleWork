package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.Tag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TagDaoDBImpl implements TagDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private enum SQL {
        SELECT_ALL("select * from Tag"),
        SELECT_BY_ID("select * from Tag where Tag_ID = ?"),
        INSERT("insert into Tag (TagName) values (?)"),
        DELETE("delete from Tag where Tag_ID = ?"),
        UPDATE("update Tag set TagName = ? where Tag_ID = ?"),
        SELECT_BY_NAME("select * from Tag where TagName = ?");

        private final String statement;

        private SQL(String statement) {
            this.statement = statement;
        }

        public String getStatement() {
            return this.statement;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Tag addTag(Tag tag) {
        jdbcTemplate.update(SQL.INSERT.getStatement(),
                tag.getTagName()
        );

        tag.setTagId(jdbcTemplate.queryForObject("select last_insert_id()", Integer.class));

        return tag;
    }

    @Override
    public void removeTagById(int tagId) {
        jdbcTemplate.update(SQL.DELETE.getStatement(), tagId);
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query(SQL.SELECT_ALL.getStatement(), new TagMapper());
    }

    @Override
    public Tag getTagById(int tagId) {
        try {
            return jdbcTemplate.queryForObject(SQL.SELECT_BY_ID.getStatement(), 
                    new TagMapper(), tagId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public Tag getTagByTagName(String tagName) {
        try {
            return jdbcTemplate.queryForObject(SQL.SELECT_BY_NAME.getStatement(), 
                    new TagMapper(), tagName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    //Takes in a string, stripts tags from textBody,
    //  creates any 'new' tags, return to sender
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Tag> manageTags(String newTextBody){
        //Get array of raw tagnames
        List<String> rawTagList = parseForRawTagNames(newTextBody);
        
        //Send in raw string list, get tag list back (creates new tag when needed)
        List<Tag> tagsToAdd = buildTagList(rawTagList);
        return tagsToAdd;
    }
    
    //Takes in a string, stripts tags from textBody,
    //  creates any 'new' tags, return to sender
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Tag> manageTags(String newTextBody, String oldTextBody){
        //Get array of raw tagnames (for the 'new' text body)
        List<String> rawTagSetNew = parseForRawTagNames(newTextBody);
        //Get array of raw tagnames (for the 'old' text body)
        List<String> rawTagSetOld = parseForRawTagNames(oldTextBody);
        
        //Only unique entries between old and new
        List<String> rawTagList = new ArrayList<>();
        for(String rawTagNew : rawTagSetNew){
            if(!rawTagSetOld.contains(rawTagNew)){
                rawTagList.add(rawTagNew);
            }
        }
        
        //Send in raw string list, get tag list back (creates new tag when needed)
        List<Tag> tagsToAdd = buildTagList(rawTagList);
        return tagsToAdd;
    }
    
    private List<Tag> buildTagList(List<String> rawTagList){
        ArrayList<Tag> tagsToAdd = new ArrayList<>();
        //If tag exists, add to list
        //  if tag does not exists, create it and add it
        for(String rawTag : rawTagList){
            Tag tag = new Tag();
            tag = getTagByTagName(rawTag);
            
            if(tag == null){
                tag = new Tag();
                tag.setTagName(rawTag);
                tag = addTag(tag);
            }
            tagsToAdd.add(tag);
        }
        
        return tagsToAdd;
    }
    
    
    private List<String> parseForRawTagNames(String textBody){
        Set<String> rawTagSet = new HashSet<>();
        
        Pattern p = Pattern.compile("#[A-Za-z0-9]*\\b");
        Matcher m = p.matcher(textBody);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String foundItem = m.group().substring(1);
            rawTagSet.add(foundItem);
        }
        
        return new ArrayList<String>(rawTagSet);
        
    }
    
    private static final class TagMapper implements RowMapper<Tag> {

        @Override
        public Tag mapRow(ResultSet rs, int i) throws SQLException {
            Tag tag = new Tag();

            tag.setTagId(rs.getInt("Tag_ID"));
            tag.setTagName(rs.getString("TagName"));

            return tag;
        }

    }

}
