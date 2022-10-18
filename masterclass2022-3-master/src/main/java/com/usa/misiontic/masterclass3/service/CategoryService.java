package com.usa.misiontic.masterclass3.service;

import com.usa.misiontic.masterclass3.entities.Admin;
import com.usa.misiontic.masterclass3.entities.Category;
import com.usa.misiontic.masterclass3.repository.AdminRepository;
import com.usa.misiontic.masterclass3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }
    public Category save(Category category){
        if(category.getId()==null) {
            return categoryRepository.save(category);
        }else{
            Optional<Category>categoryEncontrado=categoryRepository.getCategory(category.getId());
            if(categoryEncontrado.isEmpty()){
                return categoryRepository.save(category);
            }else{
                return category;
            }
        }
    }
    public Category update(Category category){
        if (category.getId()!=null){
            Optional<Category> categoryEncontrado=categoryRepository.getCategory(category.getId());
            if(!categoryEncontrado.isEmpty()){
                if(category.getDescription()!=null){
                    categoryEncontrado.get().setDescription(category.getDescription());
                }
                if (category.getName()!=null){
                    categoryEncontrado.get().setName(category.getName());
                }
                return categoryRepository.save(categoryEncontrado.get());

            }
        }
        return category;
    }
    public boolean deleteCategory(int id){
        Boolean respuesta= getCategory(id).map(category-> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return respuesta;
    }
}