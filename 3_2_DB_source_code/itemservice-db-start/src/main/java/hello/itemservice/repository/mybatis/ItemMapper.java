package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {
    /**
     * [MyBatis v1 - 매퍼 인터페이스 ]
     * 조건 1. 인터페이스에 : @Mapper
     * 조건 2. 메소드 제작
     * - 메소드 이름과 xml의 id값 일치해야함
     * - 메소드의 파라미터가 두개 이상인경우 @Param이 필요로 합니다.
     * 기능 : MyBatis를 통하여 Xml의 SQL을 호출해주는 역할을 하는 Mapper Interface
     * 특징 : 인터페이스만 있고 구현체는 자동으로 만들어지기 때문에 필요 없다
     */
    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);

    List<Item> findAll(ItemSearchCond itemSearchCond);

    Optional<Item> findById(Long id);
}
