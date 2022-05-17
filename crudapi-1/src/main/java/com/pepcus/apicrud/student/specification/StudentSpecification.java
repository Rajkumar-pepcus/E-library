package com.pepcus.apicrud.student.specification;





import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.pepcus.apicrud.model.Address;
import com.pepcus.apicrud.model.Student;

@Component
public class StudentSpecification {

	/*
	 * Specification by name
	 */
	public static Specification<Student> hasName(String name) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("name"), name);
		});
	}

	/*
	 * Specification by department
	 */
	public static Specification<Student> hasDepartment(String department) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("department"), department);
		});

	}

	/*
	 * Specification by email
	 */
	public static Specification<Student> hasEmail(String email) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("email"), email);
		});
	}

	public static Specification<Student> hasCity(String city) {
		return ((root, criteriaQuery, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("city"), city);
		});

	}
	
	public static <Student> Specification<Student> bySearchFilter(final Class<Student> clazz) {
	    return (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
	        query.multiselect(root.get("city")).distinct(true);

	        List<String> predicates = List.of("city");

	        predicates.addAll((Collection<? extends String>) builder.equal(root.get("active"), "Y"));

	        return builder.and(predicates.toArray(new Predicate[0]));

	    };
	}
}