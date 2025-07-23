package id.pradana.ems.filter;

import java.text.SimpleDateFormat;

import org.springframework.data.jpa.domain.Specification;

import id.pradana.ems.model.Employee;
import jakarta.persistence.criteria.Predicate;

public class EmployeeSpecFilter {

        private EmployeeSpecFilter() {
                // private constructor to prevent instantiation
        }

        public static Specification<Employee> filterAll(EmployeeFilterDTO filter) {
                return (root, cr, cb) -> {
                        String birthdate = filter.getBirthdate() == null
                                        ? ""
                                        : new SimpleDateFormat("dd-MM-yyyy")
                                                        .format(filter.getBirthdate());
                        String hiredate = filter.getHiredate() == null
                                        ? ""
                                        : new SimpleDateFormat("dd-MM-yyyy")
                                                        .format(filter.getHiredate());

                        Predicate predicate = cb.and(
                                        cb.like(root.get("id").as(String.class),
                                                        "%" + filter.getId() + "%"),
                                        cb.like(root.get("fullname"), "%" + filter.getFullname() + "%"),
                                        cb.like(cb.function("DATE_FORMAT", String.class,
                                                        root.get("birthdate"), cb.literal("%d-%m-%Y")),
                                                        "%" + birthdate + "%"),
                                        cb.like(cb.function("DATE_FORMAT", String.class,
                                                        root.get("hiredate"), cb.literal("%d-%m-%Y")),
                                                        "%" + hiredate + "%"));
                        return cb.and(predicate);
                };
        }
}
