
package com.sg.glbccapstone.dao;

import com.sg.glbccapstone.model.Category;
import java.util.List;

public interface CategoryDao {
    public Category addCategory(Category category);
    public void removeCategoryById(int categoryId);
    public void updateCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategoryById(int categoryId);
}
