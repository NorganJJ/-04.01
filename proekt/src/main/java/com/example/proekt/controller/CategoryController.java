package com.example.proekt.controller;

import com.example.proekt.model.AviaryModel;
import com.example.proekt.model.CategoryModel;
import com.example.proekt.repo.AviaryRepo;
import com.example.proekt.repo.CategoryRepo;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CategoryController {
    private final CategoryRepo Repo;

    @Autowired
    public CategoryController(CategoryRepo Repo) {
        this.Repo = Repo;
    }

    @GetMapping()
    public String listCategory(Model model) {
        Iterable<CategoryModel> categoryModels = Repo.findAll();
        model.addAttribute("categories", categoryModels);
        return "category/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        CategoryModel categoryModel = new CategoryModel();
        //clas.setPublicationYear(0);
        model.addAttribute("category", categoryModel);
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/add";
        }
        Repo.save(categoryModel);
        return "redirect:/category";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        CategoryModel categoryModel = Repo.findById(id).orElse(null);
        if (categoryModel == null) {
            return "redirect:/category";
        }
        model.addAttribute("category", categoryModel);
        return "category/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "category/update";
        }
        categoryModel.setId(id);
        Repo.save(categoryModel);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<CategoryModel> categoryModels = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("categories", categoryModels);
        return "category/all";
    }
}
