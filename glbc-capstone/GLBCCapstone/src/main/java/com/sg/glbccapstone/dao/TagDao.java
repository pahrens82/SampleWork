
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Article;
import com.sg.glbccapstone.model.Tag;
import java.util.List;

public interface TagDao {
    public Tag addTag(Tag tag);
    public void removeTagById(int tagId);
    public List<Tag> getAllTags();
    public Tag getTagById(int tagId);
    public Tag getTagByTagName(String tagName);
    public List<Tag> manageTags(String newTextBody);
    public List<Tag> manageTags(String newTextBody, String oldTextBody);
}
