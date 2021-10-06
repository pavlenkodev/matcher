package com.example.service;

import com.example.exception.CatNotFoundException;
import com.example.model.Cat;
import com.example.repository.CatRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Data
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    Map<Cat, Cat> cats = new HashMap<>();

    @Override
    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Cat findById(long id) {
        Cat cat = new Cat();
        Optional<Cat> foundCat = catRepository.findById(id);
        if (foundCat.isPresent()) {
            cat = foundCat.get();
        } else {
            throw new CatNotFoundException("Cat is not found");
        }
        return cat;
    }

    @Override
    public void likeCat(Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new CatNotFoundException("Cat is not found"));
        cat.setSelected(cat.getSelected() + 1);
        catRepository.save(cat);
    }

    @Override
    public List<Cat> getTop10() {
        return catRepository.findTop10ByOrderBySelectedDesc();
    }

    @Override
    public void removeById(long id) {
        if (findById(id) != null) {
            catRepository.removedById(id);
        } else {
            throw new CatNotFoundException("Cat is not found");
        }
    }

    @Override
    public void resetById(Long id) {
        if (findById(id) != null) {
            catRepository.removedById(id);
        } else {
            throw new CatNotFoundException("Cat is not found");
        }
    }

    @Override
    public List<Cat> findAllByIsDeletedTrue() {
        return catRepository.findAllByIsDeletedTrue();
    }

    @Override
    public List<Cat> findAllByIsDeletedFalse() {
        return catRepository.findAllByIsDeletedFalse();
    }


    @Override
    public List<Cat> findAll() {
        List<Cat> emptyList = new ArrayList<>();

        List<Cat> all = catRepository.findAll();
        Collections.shuffle(all);
        List<Cat> result = new ArrayList<>();
        result.add(all.get(0));
        result.add(all.get(1));

        if (checkUniquePair(result)) {
            return result;
        }

        return emptyList;
    }

    public boolean checkUniquePair(List<Cat> catsPair) {
        Cat catKey = catsPair.get(0);
        Cat catValue = catsPair.get(1);

        if (cats.isEmpty()) {
            cats.put(catKey, catValue);
            return true;
        }

        if (cats.containsKey(catKey) && cats.get(catKey).getId().equals(catValue.getId()) ||
                cats.containsKey(catValue) && cats.get(catValue).getId().equals(catKey.getId())) {
            return false;
        }
        cats.put(catKey, catValue);

        return true;
    }
}
