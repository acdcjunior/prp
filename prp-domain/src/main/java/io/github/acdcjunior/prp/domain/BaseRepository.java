package io.github.acdcjunior.prp.domain;

import java.util.List;

public interface BaseRepository<T extends BaseEntity> {
	
	void save(T t);

	void remove(T t);
	
	T findById(Integer id);
    
    List<T> findAll();

}