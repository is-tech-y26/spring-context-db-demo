import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.makarevich.EntityManagerFactoryConfig;
import ru.makarevich.UserService;

public class UserTests {
    private UserService sut;

    @BeforeEach
    public void init() {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(EntityManagerFactoryConfig.class);
        ctx.register(UserService.class);
        ctx.refresh(); // !

        sut = ctx.getBean(UserService.class);
    }

    @Test
    public void ensureUserSaved() {
        var expectedUserId = 1;
        var expectedUserName = "Test user";

        sut.save(expectedUserName);
        var result = sut.getAll().stream().findFirst().orElseThrow();

        Assertions.assertEquals(expectedUserId, result.getId());
    }
}
