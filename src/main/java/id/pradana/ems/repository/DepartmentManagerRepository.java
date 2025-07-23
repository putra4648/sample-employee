package id.pradana.ems.repository;

import org.springframework.data.repository.CrudRepository;

import id.pradana.ems.model.DepartmentManager;
import id.pradana.ems.model.DepartmentManagerPK;

public interface DepartmentManagerRepository
        extends CrudRepository<DepartmentManager, DepartmentManagerPK> {
}
