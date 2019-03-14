package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICategoryService {
    public ServerResponse addCategory(String categoryName, Integer parentId);

    public ServerResponse updateCategoryName(String categoryName, Integer categoryId);

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    public ServerResponse< List<Integer> > selectCategoryAndChildrenById(Integer categoryId);
}
