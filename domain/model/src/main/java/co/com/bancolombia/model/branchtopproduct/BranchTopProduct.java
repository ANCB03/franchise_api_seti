package co.com.bancolombia.model.branchtopproduct;
import co.com.bancolombia.model.exceptions.DomainErrorCode;
import co.com.bancolombia.model.exceptions.DomainValidationException;
import co.com.bancolombia.model.product.Product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder(toBuilder = true)
public class BranchTopProduct {
    private String branchName;
    private Product product;

    public BranchTopProduct(String branchName, Product product) {
        this.branchName = branchName;
        this.product = product;
    }

    public static BranchTopProduct create(String branchName, Product product) {
        validateBranchName(branchName);
        validateProduct(product);
        return new BranchTopProduct(branchName, product);
    }

    private static void validateBranchName(String branchName) {
        if (branchName == null || branchName.trim().isEmpty()) {
            throw new DomainValidationException(DomainErrorCode.INVALID_PRODUCT, "Branch name cannot be null");
        }
    }

    private static void validateProduct(Product product) {
        if (product == null) {
            throw new DomainValidationException(DomainErrorCode.INVALID_PRODUCT, "Product name cannot be null");
        }
    }

    public static BranchTopProductBuilder builder() {
        return new BranchTopProductBuilder();
    }

    public static class BranchTopProductBuilder {
        private String branchName;
        private Product product;

        public BranchTopProductBuilder branchName(String branchName) {
            this.branchName = branchName;
            return this;
        }

        public BranchTopProductBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public BranchTopProduct build() {
            return BranchTopProduct.create(branchName, product);
        }
    }
}
