package binlist.demo.api.data;

import binlist.demo.api.data.entities.CardIssuingCountry;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@DataJpaTest
@Repository
public interface IssuingCountriesRepository extends CrudRepository<CardIssuingCountry, String> {

}
