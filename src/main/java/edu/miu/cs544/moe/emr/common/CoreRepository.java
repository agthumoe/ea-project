package edu.miu.cs544.moe.emr.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CoreRepository<T extends Model> extends JpaRepository<T, Long> {
}
