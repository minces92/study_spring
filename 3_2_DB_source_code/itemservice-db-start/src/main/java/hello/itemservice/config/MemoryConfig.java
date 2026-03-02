package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jdbctemplate.JdbcTemplateItemRepositoryV1;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MemoryConfig {

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    private final DataSource dataSource;
    public MemoryConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ItemRepository itemRepository() {
//        return new MemoryItemRepository();
        return new JdbcTemplateItemRepositoryV1(dataSource);
    }

}
