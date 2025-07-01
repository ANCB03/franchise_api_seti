package co.com.bancolombia.mongo.helper.mapper;

import co.com.bancolombia.model.branch.Branch;
import co.com.bancolombia.model.franchise.Franchise;
import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.mongo.helper.document.BranchDocument;
import co.com.bancolombia.mongo.helper.document.FranchiseDocument;
import co.com.bancolombia.mongo.helper.document.ProductDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FranchiseDocumentMapperTest {

    private FranchiseDocumentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new FranchiseDocumentMapper();
    }

    @Test
    void shouldMapFranchiseToDocument() {
        Product product = Product.builder()
                .name("Product A")
                .stock(10)
                .build();

        Branch branch = Branch.builder()
                .name("Branch X")
                .products(List.of(product))
                .build();

        Franchise franchise = Franchise.builder()
                .id("f-001")
                .name("Franchise Test")
                .branches(List.of(branch))
                .build();

        FranchiseDocument doc = mapper.toDocument(franchise);

        assertNotNull(doc);
        assertEquals("f-001", doc.getId());
        assertEquals("Franchise Test", doc.getName());
        assertEquals(1, doc.getBranches().size());

        BranchDocument branchDoc = doc.getBranches().get(0);
        assertEquals("Branch X", branchDoc.getName());
        assertEquals(1, branchDoc.getProducts().size());

        ProductDocument productDoc = branchDoc.getProducts().get(0);
        assertEquals("Product A", productDoc.getName());
        assertEquals(10, productDoc.getStock());
    }

    @Test
    void shouldMapFranchiseDocumentToEntity() {
        ProductDocument productDoc = ProductDocument.builder()
                .name("Product B")
                .stock(5)
                .build();

        BranchDocument branchDoc = BranchDocument.builder()
                .name("Branch Y")
                .products(List.of(productDoc))
                .build();

        FranchiseDocument doc = FranchiseDocument.builder()
                .id("f-002")
                .name("Franchise Mongo")
                .branches(List.of(branchDoc))
                .build();

        Franchise franchise = mapper.toEntity(doc);

        assertNotNull(franchise);
        assertEquals("f-002", franchise.getId());
        assertEquals("Franchise Mongo", franchise.getName());
        assertEquals(1, franchise.getBranches().size());

        Branch branch = franchise.getBranches().get(0);
        assertEquals("Branch Y", branch.getName());
        assertEquals(1, branch.getProducts().size());

        Product product = branch.getProducts().get(0);
        assertEquals("Product B", product.getName());
        assertEquals(5, product.getStock());
    }

    @Test
    void shouldMapProductDocumentToProduct() {
        ProductDocument doc = ProductDocument.builder()
                .name("Isolated Product")
                .stock(20)
                .build();

        Product product = mapper.toProduct(doc);

        assertNotNull(product);
        assertEquals("Isolated Product", product.getName());
        assertEquals(20, product.getStock());
    }

    @Test
    void shouldReturnNullWhenProductDocumentIsNull() {
        assertNull(mapper.toProduct(null));
    }

    @Test
    void shouldHandleNullBranchesOrProductsGracefully() {
        Franchise franchise = Franchise.builder()
                .id("f-003")
                .name("Franchise Null Branches")
                .branches(null)
                .build();

        FranchiseDocument doc = mapper.toDocument(franchise);
        assertNotNull(doc);
        assertTrue(doc.getBranches().isEmpty());

        FranchiseDocument docNull = FranchiseDocument.builder()
                .id("f-004")
                .name("Franchise Null Branch List")
                .branches(null)
                .build();

        Franchise entity = mapper.toEntity(docNull);
        assertNotNull(entity);
        assertTrue(entity.getBranches().isEmpty());
    }
}