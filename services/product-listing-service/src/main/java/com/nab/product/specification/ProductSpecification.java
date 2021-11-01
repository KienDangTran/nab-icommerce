package com.nab.product.specification;

import com.nab.product.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecification {
  public static Specification<Product> createProductSpecification(
      String name,
      String branch,
      String colour,
      BigDecimal priceGte,
      BigDecimal priceLte
  ) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      cb.conjunction();

      if (!StringUtils.isEmpty(name)) {
        predicates.add(cb.like(
            cb.lower(root.get("name")),
            "%" + StringUtils.trimTrailingWhitespace(name).toLowerCase() + "%"
        ));
      }

      if (!StringUtils.isEmpty(branch)) {
        predicates.add(cb.like(
            cb.lower(root.get("branch")),
            "%" + StringUtils.trimTrailingWhitespace(branch).toLowerCase() + "%"
        ));
      }

      if (!StringUtils.isEmpty(colour)) {
        predicates.add(cb.like(
            cb.lower(root.get("colour")),
            "%" + StringUtils.trimTrailingWhitespace(colour).toLowerCase() + "%"
        ));
      }

      if (priceGte != null) {
        predicates.add(cb.greaterThanOrEqualTo(
            root.get("price"),
            priceGte
        ));
      }

      if (priceLte != null) {
        predicates.add(cb.lessThanOrEqualTo(
            root.get("price"),
            priceLte
        ));
      }

      return cb.and((predicates.toArray(Predicate[]::new)));
    };
  }
}
