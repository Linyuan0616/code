package com.LinSY.backend.controller;

import com.LinSY.backend.service.CategoryService;
import com.LinSY.backend.utils.pojo.EasyUITreeNode;
import com.LinSY.backend.utils.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService ;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
        List<EasyUITreeNode> list = categoryService.getCatList(parentId) ;
        return list ;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addCategory(Long parentId, String name) {
        Result result = categoryService.addCategory(parentId, name);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result updateCategory(Long id, String name) {
        Result result = categoryService.updateCategory(id, name);
        return result;
    }
}
